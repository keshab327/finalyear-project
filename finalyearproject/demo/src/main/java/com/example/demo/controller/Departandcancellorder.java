package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.dao.UserdetailRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.enittiy.Order;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.enittiy.Userdetail;
import com.example.demo.enittiy.product;
import com.example.demo.services.Emailservice;

@Controller
public class Departandcancellorder {

	@Autowired
	OrderRepository orderrepo;
	
	@Autowired
	UserdetailRepository userdetailrepo;
	
	@Autowired
	Emailservice emailservice;
	@Autowired
	productRepository productrepo;
	
	@Autowired
    ShopRegisterRepository shoprepo  ;
	
	
	@GetMapping("/owner/depart/{orderid}/{orderindex}")
	public String addToCart(@PathVariable("orderid") int orderid,@PathVariable("orderindex") int orderindex,HttpServletRequest request) {
		
	
		
		long shopid=0;
	
	
		
		try {
			String shopid_string= (String) request.getSession().getAttribute("shopid");
			int shop=Integer. parseInt(shopid_string);
			 shopid=Long.valueOf(shop);
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		ShopRegister shop=shoprepo.findById(shopid).get();
		List<Order> order=shop.getOrder();
		Date date=new Date();
		
		//change order status to depart
		order.get(orderindex).setDepartdate(date);
	
		order.get(orderindex).setDepartstatus("DEPRTED");
		order.get(orderindex).setDepartdate(date);
		shoprepo.save(shop);
		
		//product left from shop
		
		//to reduce quantity
		
		
		
		int customerid=order.get(orderindex).getCustomerid();
	    Userdetail customer=userdetailrepo.findById(customerid).get();
	    List<Order> customerorder=customer.getOrder();
	    customerorder.add(shoprepo.findById(shopid).get().getOrder().get(orderindex));
	    userdetailrepo.save(customer);
		
		int productid=order.get(orderindex).getProductid();
		
		product p=productrepo.findById(productid).get();
		
		
			if(p.getQuantity()==1||p.getQuantity()==0) {
				
				//long shopid=p.getShopid();
				Optional<ShopRegister> owner=shoprepo.findById(shopid);
			//	String ownremail=owner.get().getEmail();
				String owneremail="jkeshab570@gmail.com";
				String inside_text="Dear owner\n\n  your product : "+p.getName()+"\n id:"+p.getProduct_id()+"  has all been purchased  please add more";
				String subject="FROM E-FANCY";
				String a=emailservice.sendEmail(owneremail, subject, inside_text);

				//remove product so that customer do not see it
				p.setQuantity(0);
				p.setAvailable("NO");
				productrepo.save(p);
				
			}
			else {
				
				Order o=orderrepo.findById(orderid).get();
				
				int oldquantity=p.getQuantity();
				p.setQuantity(oldquantity-o.getQuantity());
				productrepo.save(p);
				
			}
			
		
		
		
		
		
		
		
		//String customeremail=customer.getEmail();
		String customeremail="jkeshab570@gmail.com";
		String inside_text="your product has been departed soon will be delivered";
		String subject="From efancy";
		String a=emailservice.sendEmail(customeremail, subject, inside_text);
		
		
		
		
		 return "redirect:/shopwoner_seeorder";
	
	
	
	
}
	
	
	
	
	
	@GetMapping("/owner/cancell/{orderid}/{orderindex}")
	public String cancell(@PathVariable("orderid") int orderid,@PathVariable("orderindex") int orderindex,HttpServletRequest request) {
		

		long shopid=0;
	
	
		
		try {
			String shopid_string= (String) request.getSession().getAttribute("shopid");
			int shop=Integer. parseInt(shopid_string);
			 shopid=Long.valueOf(shop);
		}catch(Exception e) {
			return "redirect:/login";
		}
		
		ShopRegister shop=shoprepo.findById(shopid).get();
		shop.getOrder().remove(orderindex);
	    shoprepo.save(shop);
		
		
		Optional<Order> orderr=orderrepo.findById(orderid);
	//	String customeremail=orderr.get().getEmail();
		String customeremail="jkeshab570@gmail.com";
		String inside_text="your order has bee cancelled your payment will soon be returned";
		String subject="From efancy";
		
		String a=emailservice.sendEmail(customeremail, subject, inside_text);
		
		//delete reject order
		
		
		
		
		
		
		
		 return "redirect:/shopwoner_seeorder";
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
}