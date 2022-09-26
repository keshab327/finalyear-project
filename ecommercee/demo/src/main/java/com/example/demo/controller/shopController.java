package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.CategoryrequestRepository;
import com.example.demo.dao.UserdetailRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.enittiy.Category;
import com.example.demo.enittiy.Categoryrequest;
import com.example.demo.enittiy.Userdetail;
import com.example.demo.enittiy.product;
import com.example.demo.globalAccess.GlobalData;
import com.example.demo.services.ProductService;
import com.example.demo.services.categoryservices;

@Controller
public class shopController {

	@Autowired
	categoryservices categoryservice;
	
	@Autowired
	ProductService productservice;
	@Autowired
	productRepository productrepo;
	@Autowired
	CategoryRepository categoryrepo;
	@Autowired
	UserdetailRepository userdetailrepo;
 
	
	@GetMapping("/shop")
	public String shop(Model model)
	{
		
	    model.addAttribute("products", productservice.getAllProduct());
		model.addAttribute("cartCount",GlobalData.cart.size());
		

			return "redirect:/showallproduct/paging/";	
	}
	
	
	
	

	
	
	
	
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model,@PathVariable int id) {
		model.addAttribute("product", productservice.getProductById(id).get());
		model.addAttribute("cartCount",GlobalData.cart.size());

			
		return "viewProduct";
	}
	
	
	
	
	
	
	
	@GetMapping("/showallproduct/paging/")
	public  String showpagess( Model model,HttpServletRequest request) {
	
		
		int number_of_pages=0;
		try {
			
			number_of_pages = (int) request.getSession().getAttribute("pages");
			
		}catch(Exception e) {
			number_of_pages=3;
			
		}
		
		int user_id;
		try {
			 user_id = (int) request.getSession().getAttribute("userid");
		
			 Integer pageno=0;
				Pageable pageable=	PageRequest.of(pageno,number_of_pages);
				
				model.addAttribute("categories", categoryservice.getAllCategory());
				Page<product> productpages=productrepo.findAllByAvailable("YES",pageable);
				model.addAttribute("productpages", productpages);
				model.addAttribute("currentpageno", pageno);
				model.addAttribute("totalpages",productpages.getTotalPages());
				model.addAttribute("search", "a");
				model.addAttribute("categoryid", -1);	 
			 
			 
				Userdetail user=userdetailrepo.findById(user_id).get();
				
				model.addAttribute("username", user.getFirstname());
					 
			 
			 
			 
				
				 }catch(Exception e) {
						Integer pageno=0;
						Pageable pageable=	PageRequest.of(pageno,number_of_pages);
						
						model.addAttribute("categories", categoryservice.getAllCategory());
						Page<product> productpages=productrepo.findAllByAvailable("YES",pageable);
						model.addAttribute("productpages", productpages);
						model.addAttribute("currentpageno", pageno);
						model.addAttribute("totalpages",productpages.getTotalPages());
						model.addAttribute("search", "a");
						model.addAttribute("categoryid", -1);
				 }
		
		
		
		
		
		
		
		
		
		
	

		
		
		
	
	//model.addAttribute("pageloc", 0);
	return "shop";	
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/showallproduct/paging/{page}/{search}/{categoryid}")
	public  String showpages(HttpServletRequest request,@PathVariable("page") Integer pageno,@PathVariable("search") String search,@PathVariable("categoryid") Integer categoryid,Model model) {
		
		int number_of_pages=0;
		try {
			
			number_of_pages = (int) request.getSession().getAttribute("pages");
			
		}catch(Exception e) {
			number_of_pages=3;
			
		}
		
		
		
		
		
		
		int user_id = 0;
		try {
			 user_id = (int) request.getSession().getAttribute("userid");
			 Userdetail user=userdetailrepo.findById(user_id).get();
			 model.addAttribute("username", user.getFirstname());
		}catch(Exception e) {
			
			 model.addAttribute("username", "");	
		}
		
		
		
		
		
		
		if(pageno==-1) {
			pageno=0;
		}
		
	Pageable pageable=	PageRequest.of(pageno,number_of_pages);
	Page<product> productpages = null;
	
	if(search.equalsIgnoreCase("a")&&categoryid==-1) {
	productpages=productrepo.findAllByAvailable("YES",pageable);
	model.addAttribute("search", "a");
	model.addAttribute("categoryid", -1);
	}
	
	else if(categoryid!=-1){
		model.addAttribute("search", "a");
	
		model.addAttribute("categoryid", categoryid);
		Optional<Category> cat=categoryrepo.findById(categoryid);
		List<Category> categories=categoryrepo.findAllByName(cat.get().getName());
		 model.addAttribute("categories", categories);
		 productpages= productrepo.findAllByCategory_IdAndAvailable(categoryid,"YES",pageable);
	
	
	}
		
	else if(search.equals("a")==false&&categoryid==-1){
		model.addAttribute("search", search);
		
		model.addAttribute("categoryid", -1);
		System.out.println("\n\n searchhhhhhh");
		
		 
		 productpages=productrepo.findAllByNameAndAvailable(search,"YES",pageable);
	
		
	}

	
	
	
	model.addAttribute("productpages", productpages);
	model.addAttribute("currentpageno", pageno);
	model.addAttribute("totalpages",productpages.getTotalPages());
	return "shop";	
		
		
		
		
	}
	
	

 @GetMapping("/setpages")
 
public String setpa(@RequestParam("pages") int pages ,HttpServletRequest request) {
	 
	 request.getSession().setAttribute("pages", pages);
	 
	 System.out.println("\n\n\n pages"+pages);
	 
	 return "redirect:/showallproduct/paging/";
	 
 }









}
