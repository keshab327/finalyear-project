package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.enittiy.Category;
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
	public  String showpagess( Model model) {
		Integer pageno=0;
	Pageable pageable=	PageRequest.of(pageno,3);
	
	model.addAttribute("categories", categoryservice.getAllCategory());
	Page<product> productpages=productrepo.findAllByAvailable("YES",pageable);
	model.addAttribute("productpages", productpages);
	model.addAttribute("currentpageno", pageno);
	model.addAttribute("totalpages",productpages.getTotalPages());
	model.addAttribute("search", "a");
	model.addAttribute("categoryid", -1);
	//model.addAttribute("pageloc", 0);
	return "shop";	
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/showallproduct/paging/{page}/{search}/{categoryid}")
	public  String showpages(@PathVariable("page") Integer pageno,@PathVariable("search") String search,@PathVariable("categoryid") Integer categoryid,Model model) {
		
		if(pageno==-1) {
			pageno=0;
		}
		
	Pageable pageable=	PageRequest.of(pageno,3);
	Page<product> productpages = null;
	
	if(search.equalsIgnoreCase("a")&&categoryid==-1) {
	productpages=productrepo.findAllByAvailable("YES",pageable);
	model.addAttribute("search", "a");
	model.addAttribute("categoryid", -1);
	}
	
	else if(search.equalsIgnoreCase("a")&&categoryid!=-1){
		model.addAttribute("search", "a");
	
		model.addAttribute("categoryid", categoryid);
		Optional<Category> cat=categoryrepo.findById(categoryid);
		List<Category> categories=categoryrepo.findAllByName(cat.get().getName());
		 model.addAttribute("categories", categories);
		 productpages= productrepo.findAllByCategory_IdAndAvailable(categoryid,"YES",pageable);
	
	
	}
		
	else if(categoryid==-1){
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
	
	
	

}
