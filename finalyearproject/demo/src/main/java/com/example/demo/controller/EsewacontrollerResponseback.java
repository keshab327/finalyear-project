package com.example.demo.controller;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EsewareturnRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.dao.Temporary_payment_supportRepository;
import com.example.demo.dao.cartRepository;
import com.example.demo.enittiy.Cart;
import com.example.demo.enittiy.Esewareturn;
import com.example.demo.enittiy.Order;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.enittiy.product;
import com.example.demo.services.Emailservice;

@RestController
public class EsewacontrollerResponseback {

	@Autowired
	private EsewareturnRepository esewarepo;
	
	@Autowired
	private OrderRepository orderrepo;
	
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
		
		Esewareturn esewa=new Esewareturn();
		esewa.setTotoal_amount(tAmt);
		esewa.setRefid_esewa(refId);
		esewa.setPid(pid);
		esewarepo.save(esewa);
		
		
		
		
		Date createDate = new Date();
		int i=0;
		List<Order> order=orderrepo.findByPid(pid);
	//	System.out.println("\\n\n\n order fetched\n");
		for(Order o:order) {
			o.setStatus("CONFIRMED");
			o.setOrderedDate(createDate);
			orderrepo.save(o);
			ShopRegister shop=shopregisterrepo.findById(o.getShopid()).get();
			List<Order> orderin_shop=shop.getOrder();
			orderin_shop.add(o);
			shop.setOrder(orderin_shop);
			shopregisterrepo.save(shop);
			
			if(o.getCartindex()!=-5) {
				
				Cart cart=o.getCart();
				List<product> productlist=cart.getProducts();
				productlist.remove(o.getCartindex());
				cart.setProducts(productlist);
				cartrepo.save(cart);
				
			}
			else {
				Cart cart=o.getCart();
				List<product> productlist=cart.getProducts();
				productlist.remove(i);
				cart.setProducts(productlist);
				cartrepo.save(cart);
			}
		i++;
			
					
		}
		
		
	
		
		
	
		 
		
		
		
	
		
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

