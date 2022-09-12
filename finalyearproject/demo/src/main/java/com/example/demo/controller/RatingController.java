package com.example.demo.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.RatingRepository;
import com.example.demo.dao.UserdetailRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.dto.productDto;
import com.example.demo.dto.ratingDTO;
import com.example.demo.enittiy.Cart;
import com.example.demo.enittiy.Rating;
import com.example.demo.enittiy.product;
import com.example.demo.services.ProductService;
import com.example.demo.services.RatingService;
import com.example.demo.services.categoryservices;

@Controller
public class RatingController {

	@Autowired
	categoryservices servicescategory;
	@Autowired
	ProductService productservicee;
	
	@Autowired
	UserdetailRepository userdetailrepo;
	
	
	@Autowired 
	RatingService ratingService;
	@Autowired
	ProductService productservice;
	
	@Autowired
	productRepository productrepo;
	
	@Autowired
	RatingRepository raterepo;
	
	
	
	private static final DecimalFormat df = new DecimalFormat("0.00");


	@GetMapping("/rate/{id}")
	public String rateProduct(Model model,@PathVariable int id){
		
		model.addAttribute("product", productservice.getProductById(id));
		model.addAttribute("id", id);
		
	//	System.out.println("id before rating"+id);
		
		
		return "rating";
	}
	
	
	@GetMapping("/rating")

	public String saveinto_rating(@RequestParam(name="description") String description, @RequestParam(name="product_id") int p_id,@RequestParam(name="rate") int rate,HttpServletRequest request) {
	  
		int customerid;
		try {
			customerid=(int) request.getSession().getAttribute("user_id");
			
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		Rating rating_new=new Rating();
		Optional<Rating> rating=raterepo.findByCustomer_UserdetailidAndProductt_Productid(customerid, p_id);
		if(rating.isEmpty()) {
			
	rating_new.setProduct(productrepo.findById(p_id).get());
			rating_new.setDescription(description);
			rating_new.setRate(rate);
			rating_new.setCustomer(userdetailrepo.findById(customerid).get());
			
		}
		else {
			rating_new=rating.get();
			rating_new.setRate(rate);
			rating_new.setDescription(description);
			
		}
		
		String aa=	ratingService.addRating(rating_new);
		
	
	
		
	
		
	
		
		
		//average of rate
		
		int a=0;
		float sum=0;
		float ratee;
		List<Rating>ratelist=raterepo.findByProductt_Productid(p_id);
		for (Rating r: ratelist) {
			a=a+1;
		    sum=(float) (sum+r.getRate()) ;
		    }
		
		
		ratee=sum/a;
		
		df.format(ratee);
		if(ratee>5)
			ratee=(float) 5.0;
		System.out.println("\n\n rate with id"+p_id+":"+ratee);
		product pp=productrepo.findById(p_id).get();
		pp.setRate(ratee);
		
		productrepo.save(pp);
	
	
		
		
		System.out.println("in rating saving\n\n"+p_id+"\n\n"+description+"\n\n"+rate);
		
		return "redirect:/shop";
	}
	

	
		
		
		

	

	@GetMapping("/viewRating")
	
	public String viewRating(Model model) {
	
				model.addAttribute("rating", ratingService.getAllRating());
return "showrate";
	}
	
	
	
	
	


}

