package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.enittiy.Category;
import com.example.demo.enittiy.product;
import com.example.demo.globalAccess.GlobalData;
import com.example.demo.services.ProductService;
import com.example.demo.services.RatingService;
import com.example.demo.services.categoryservices;

@Controller
public class SearchController {

	@Autowired
	categoryservices categoryservice;
	
	@Autowired 
	RatingService ratingService;
	
	@Autowired
	ProductService productservice;
	@Autowired
      productRepository productrepo;
	
	@Autowired
	CategoryRepository categoryrepo;
	
	
	@GetMapping("/search")
	public String SearchByProductName(Model model,@RequestParam("key") String productname) {
	model.addAttribute("categories", categoryservice.getAllCategory());

	model.addAttribute("categoryid", -1);
	
	
	
	model.addAttribute("cartCount",GlobalData.cart.size());
	System.out.println("\nproductname "+productname);
int pageno=0;// productname="skirt";
	Pageable pageable=	PageRequest.of(pageno,3);	
	Page<product> productpages= productrepo.findAllByNameAndAvailable(productname,"YES",pageable);
	
	if(productpages.getContent().isEmpty()) {
		
		
		
		
		
		List<Category> categorylist=categoryrepo.findAllByName(productname);
	  int firstcategoryid=categorylist.get(0).getId();
		
		model.addAttribute("categories",categorylist);
		
		model.addAttribute("search", "a");
		
		model.addAttribute("categoryid", firstcategoryid);
		
		
		 productpages= productrepo.findAllByCategory_IdAndAvailable(firstcategoryid,"YES",pageable);
	
		 
		
		
	}
	else {
		model.addAttribute("search", productname);
		model.addAttribute("categoryid", -1);
	}
	
	model.addAttribute("productpages", productpages);
	model.addAttribute("currentpageno", pageno);
	model.addAttribute("totalpages",productpages.getTotalPages());
	
	
	model.addAttribute("pageno",0);
	
	return "shop";	
	
		
		
	}

	
	
	
	
	
	@GetMapping("/search/{page}/{key}")
	public String SearchByProductNameand_page_no_also_return(Model model,@PathVariable("key") String productname,@PathVariable("page") int pageno) {
	model.addAttribute("categories", categoryservice.getAllCategory());
	
	model.addAttribute("cartCount",GlobalData.cart.size());

	Pageable pageable=	PageRequest.of(pageno,3);	
	Page<product> productpages= productrepo.findAllByNameAndAvailable(productname,"YES",pageable);
	
	model.addAttribute("productpages", productpages);
	model.addAttribute("currentpageno", pageno);
	model.addAttribute("totalpages",productpages.getTotalPages());
	model.addAttribute("productname",productname);

	
	
	
		
		return "searchpaging";	
	}
	
	
	
	
	
	
	
	/*
	@GetMapping("/shop/category/{page}/{id}")
	public String shopByCategorypages(Model model,@PathVariable("id") int id,@PathVariable("page") int pageno) {
		
		Optional<Category> cat=categoryrepo.findById(id);
		List<Category> categories=categoryrepo.findAllByName(cat.get().getName());
		
	model.addAttribute("categories", categories);
	//model.addAttribute("products", productservice.getAllProductByCategoryId(id));
	//model.addAttribute("cartCount",GlobalData.cart.size());
//return "shop";
		
		
		Pageable pageable=	PageRequest.of(pageno,3);
		Page<product> productpages= productrepo.findAllByCategory_Id(id,pageable);
		
		model.addAttribute("productpages", productpages);
		model.addAttribute("currentpageno", pageno);
		model.addAttribute("totalpages",productpages.getTotalPages());
		model.addAttribute("categoryid",id);
		
		return "categorypaging";
			
	
	}
	
	*/
	
	
	
	
	
	
}
