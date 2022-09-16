package com.example.demo.controller;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.ProductbuyRepository;
import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.dao.Temporary_payment_supportRepository;
import com.example.demo.dao.cartRepository;
import com.example.demo.enittiy.Order;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.services.Emailservice;

@RestController
public class EsewacontrollerResponseback {


	
	@Autowired
	private OrderRepository orderrepo;
	@Autowired
	private ProductbuyRepository productbuyrepo;
	
	@Autowired
	private ShopRegisterRepository shopregisterrepo;
	
	
	@Autowired
	private Emailservice emailservice;
	
	@Autowired
	Temporary_payment_supportRepository temprepo;
	@Autowired
	cartRepository cartrepo;
	
	
	@PostMapping("/esewasuccess")

	public String esewaback(@RequestParam(name="oid") int pid, @RequestParam(name="amt") double tAmt,@RequestParam(name="refId") long refId) {
	  
		System.out.println("\n\n\npid"+pid+"\namt"+tAmt+"\nrefId"+refId);
		
		Date createDate = new Date();
		Order order=orderrepo.findByPid(pid).get();
		order.setOrderedDate(createDate);
		order.setStatus("CONFIRMED");
		orderrepo.save(order);
		
		
		
		 order=orderrepo.findByPid(pid).get();
		ShopRegister shop=shopregisterrepo.findById(order.getShopid()).get();
		List<Order> orders=shop.getOrder();
		orders.add(order);
		shopregisterrepo.save(shop);
		
		
	//	productbuyrepo.deleteById(order.getProductbuy().getProductbuyid());
		
		
		
		///send mail to customer
		 String sendto="jkeshab570@gmail.com";
		 String subject="from efancy";
		 String inside_text="dear customer your payment has been success soon your order will be placed";
          String msg=	emailservice.sendEmail(sendto,subject,inside_text);
		

return "success";

	
	
}
	
	
	
	
	private Object findById(long shopid) {
		// TODO Auto-generated method stub
		return null;
	}




	@PostMapping("/esewafail")

	public String esewabackfail(Model model) 
		{
		
		
		
	
			String s ="NOTCONFIRMED";
			List<Order> 	 order=orderrepo.findByStatus(s);
			 
			 for(Order o:order) {
				 
				
				 orderrepo.deleteById(o.getId());
				 
			 }
			 String sendto="jkeshab570@gmail.com";
			 String subject="from efancy";
			 String inside_text="dear customer your payment has been rejected you will soon receive your return";
	          String msg=	emailservice.sendEmail(sendto,subject,inside_text);
		
		
		
		
			return "success return for fail";
			
			
			
			
		}
	
	
	

	
	

	
	}

