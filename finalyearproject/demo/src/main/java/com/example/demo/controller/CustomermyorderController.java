package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.ProductreportRepository;
import com.example.demo.enittiy.Order;
import com.example.demo.enittiy.Productreport;



@Controller
public class CustomermyorderController {
	
	
	@Autowired
	OrderRepository orderrepo;
	
	@Autowired
	ProductreportRepository productreportrepo;
	
	
	
	@GetMapping("/customermyorder/myorders")
	public String show(Model model ,HttpServletRequest request)//for session to work use in function parameter of mapping//
	{
		
		System.out.println("\n\n in my orders");
	int customer_id=  (int) request.getSession().getAttribute("user_id");
	
	
	System.out.println("\n\nn in my orders 3rd line"+customer_id);
	System.out.println("shopid in shop seeorder"+customer_id);
		
	String deliverstatus="NOTDELIVERED";
	List<Order> orders = orderrepo.findAllByCustomeridAndDeliverstatus(customer_id, deliverstatus);
	
		model.addAttribute("orders", orders);
		
	
	
		return "myorders_deprt";
		
	}
	
	
	
	
	
	
	@GetMapping("/customermyorder/delivered")
	public String deliver(Model model ,HttpServletRequest request)//for session to work use in function parameter of mapping//
	{
		
		System.out.println("\n\n in my orders");
	int customer_id=  (int) request.getSession().getAttribute("user_id");
	
	
	System.out.println("\n\nn in my orders 3rd line"+customer_id);
	System.out.println("shopid in shop seeorder"+customer_id);
		
	String deliverstatus="DELIVERED";
	List<Order> orders = orderrepo.findAllByCustomeridAndDeliverstatus(customer_id, deliverstatus);
	
		model.addAttribute("orders", orders);
		
	
	
		return "myorders_delivered";
		
	}
	
	
	
	
	@GetMapping("/customermyorder/report/{orderid}")
	String showProductDetails(@PathVariable("orderid") int orderid, Optional<Order> order, Model model,HttpServletRequest request) {
	
	//	System.out.println("shopid:"+shopid);
		try {
			
			if (orderid != 0) {
				 order = orderrepo.findById(orderid);
				 model.addAttribute("order",order);
				 
				
			
			
			
				if (order.isPresent()) {
					model.addAttribute("productid", order.get().getProductid());
					model.addAttribute("productname", order.get().getProductname());
					model.addAttribute("shippingadress", order.get().getShippingadress());
					model.addAttribute("adress", order.get().getAdress());
					model.addAttribute("totalamountperitem", order.get().getTotalamountperitem());
					model.addAttribute("phonenumber", order.get().getPhonenumber());
					model.addAttribute("email", order.get().getEmail());
					model.addAttribute("email", order.get().getEmail());
					model.addAttribute("deliverstatus", order.get().getDeliverstatus());
					model.addAttribute("departstatus", order.get().getDepartstatus());
					model.addAttribute("orderedDate", order.get().getOrderedDate());
					model.addAttribute("id", order.get().getId());
					model.addAttribute("shopid",order.get().getShopid());
					

				}
	
			}
			}catch(Exception e) {
				System.out.print("exception occured");
			}
		return "customer_report";
	
	}
	

	 @PostMapping("/Customermyorder/submitreport") 
	  public @ResponseBody ResponseEntity<?>  saveProduct(@ModelAttribute("product") Productreport report ,@RequestParam("orderid") int orderid ) {
		  
		//  System.out.println("\n report details "+report.getOrderid()+"\norder id"+report.getReport()+"\n"+report.getReportstatus());
		  try {
			  Optional<Productreport> report1=productreportrepo.findByOrderr_Id(orderid);
			  if(report1.isEmpty()) {
				  report.setOrderr(orderrepo.findById(orderid).get());
				  
				  
			  }
			  else {
				  
				  report.setId(report1.get().getId());
				  report.setOrderr(report1.get().getOrderr());
				  
			  }
			  productreportrepo.save(report);
		 return new ResponseEntity<>("Product Saved With File - ",HttpStatus.OK);
		  
	  }catch (Exception e) {
		  
		  e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		  
	  }
	 }
}
	
	
	
	 
	
	

