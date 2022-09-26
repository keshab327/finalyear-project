package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.UserdetailRepository;
import com.example.demo.enittiy.Userdetail;
import com.example.demo.globalAccess.GlobalData;
import com.example.demo.services.ProductService;
import com.example.demo.services.categoryservices;

@Controller
public class HomeController {

	@Autowired
	categoryservices categoryservice;
	@Autowired
	UserdetailRepository userdetailrepo;
	
	@Autowired
	ProductService productservice;
 
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());

		return "shop";
	}
	
	@GetMapping("/")
	public String hom(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());

	return	"redirect:/showallproduct/paging/";
	}
	
	
	

	@RequestMapping("/buyer")
	public String shopwoner(HttpServletRequest request,Model model) {
		
		
	
		return "redirect:/showallproduct/paging/";
	}
	
	
	
	
		
	

}
