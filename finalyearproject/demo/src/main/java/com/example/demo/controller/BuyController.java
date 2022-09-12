package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.EsewareturnRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.Temporary_payment_supportRepository;
import com.example.demo.dao.cartRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.dto.paymentdetail;
import com.example.demo.enittiy.Cart;
import com.example.demo.enittiy.Order;
import com.example.demo.enittiy.Temporary_support_payment;
import com.example.demo.enittiy.product;
import  java.util.Optional;



@Controller

public class BuyController {
		@Autowired
	EsewareturnRepository esewareturn;

	@Autowired
	Temporary_payment_supportRepository temprepo;

	@Autowired
	productRepository productrepo;

	@Autowired
	CategoryRepository categoryrepo;
	@Autowired
	OrderRepository orderrepo;
	@Autowired
	cartRepository cartrepo;
	
	
	
	
@RequestMapping("/buy")
 public String buy() {
	
	return "buy";
}


	

	

	@PostMapping("/customer/payment")//goes to either singe item or multiple item
	public String esewasend(@ModelAttribute("payment") paymentdetail payment,@RequestParam("cartid") int cartid,Model model,HttpServletRequest request ) {
		
		
	int customer_id=0;
		
		try {
			 customer_id = (int) request.getSession().getAttribute("user_id");
			System.out.println("\n\n in add to cart"+customer_id);
			
			 
		
				 
				
				 }catch(Exception e) {
					 System.out.println("\n\n user  session not set");
					 return "redirect:/login";
				 }
		
		
		
		
		
		String email=payment.getEmail();
		String adress=payment.getAdress();
		long phone=payment.getPhone();
		String shipping_adress=payment.getShipping_adress();
		double txAmt=payment.getTxAmt();
		double tAmt=payment.gettAmt();
		double psc=payment.getPsc();
		double pdc=payment.getPdc();
		double amt=payment.getAmt();
		int pid=payment.getPid();
		int product_id=payment.getProdct_id();
		int cartindex=payment.getCartindex();
	
	
		
		int payment1;
		String pagestring = null;

		try {
			
			 payment1 = (int) request.getSession().getAttribute("payment1");
			
			 System.out.print("\n\n\n after session   \n");
			 
			 
			 if(payment1==1) {
				 
				 System.out.print("\n\n\n before productid=0   \n");
				 System.out.println("\n\n\n product id  "+product_id);
				 
				 if(product_id==0) {
						
						
						
						

					  List<Temporary_support_payment> temp=temprepo.findAll();
					  
					  for(Temporary_support_payment t:temp) {
						 
						  //order table fill
						  Order order=new Order();
						  order.setAdress(adress);
						  order.setShippingadress(shipping_adress);
						  order.setEmail(email);
						  order.setPhonenumber(phone);
						  
						  order.setCustomerid(customer_id);
						  order.setPid(pid);
						  
						order.setStatus("NOTCONFIRMED");
						order.setDepartstatus("NOTDEPRTED");
						order.setDeliverstatus("NOTDELIVERED");
						 
						  
						  order.setProductid(t.getProduct_id());
						  order.setTotalamountperitem(t.getTotal_peritem_amount());
						  order.setCartindex(0);
						  Cart cart=cartrepo.findById(cartid).get();
						  order.setCart(cart);
						 
						  
						  
						  Optional<product> pp=productrepo.findById(t.getProduct_id());
						  long id=Long.valueOf(pp.get().getShopregisterr().getId());
						  String name=pp.get().getName();
						  order.setShopid(id);
						  order.setProductname(name);
						  
						  orderrepo.save(order);
						  
						  
					  }
					 
					temprepo.deleteAll();    
					
					tAmt=psc+pdc+txAmt+amt;
					model.addAttribute("adress",adress);
					model.addAttribute("shipping_adress",shipping_adress);
					model.addAttribute("phone",phone);
					model.addAttribute("email",email);
					model.addAttribute("txAmt", txAmt);
					model.addAttribute("tAmt",tAmt);
					model.addAttribute("psc",psc);
					model.addAttribute("pdc",pdc);
					model.addAttribute("amt",amt);
					model.addAttribute("pid",pid);
				model.addAttribute("product_id",product_id);
					
				
				System.out.println("\n\n\nin all payment  ");
					//delete tempdatabase
					temprepo.deleteAll();	
					 request.getSession().removeAttribute("payment1");
					 pagestring= "esewa_all_item";
				}
		
			
		
			
		else 
		{
			
			System.out.println("\n\n\n\n in single item  ");
			//order table
			
			 Order order=new Order();
			  order.setAdress(adress);
			  order.setShippingadress(shipping_adress);
			  order.setEmail(email);
			  order.setPhonenumber(phone);
			  
			  order.setCustomerid(customer_id);
			  order.setPid(pid);
			  
			  order.setProductid(product_id);
			  order.setTotalamountperitem(tAmt);
			  order.setCartindex(cartindex);
			  Cart cart=cartrepo.findById(cartid).get();
			  order.setCart(cart);
			  
			  Optional<product> pp=productrepo.findById(product_id);
			  long id=Long.valueOf(pp.get().getShopregisterr().getId());
			  String name=pp.get().getName();
			  System.out.println("\nproductnamein order"+name);
			  order.setShopid(id);
			  order.setProductname(name);
			  
			order.setStatus("NOTCONFIRMED");
			order.setDepartstatus("NOTDEPRTED");
			order.setDeliverstatus("NOTDELIVERED");
			 orderrepo.save(order);
			 
			System.out.println("\n\n\n before setting attribute  ");
			
			tAmt=psc+pdc+txAmt+amt;
			model.addAttribute("adress",adress);
			model.addAttribute("shipping_adress",shipping_adress);
			model.addAttribute("phone",phone);
			model.addAttribute("email",email);
			model.addAttribute("txAmt", txAmt);
			model.addAttribute("tAmt",tAmt);
			model.addAttribute("psc",psc);
			model.addAttribute("pdc",pdc);
			model.addAttribute("amt",amt);
			model.addAttribute("pid",pid);
			model.addAttribute("product_id",product_id);
			
			
			System.out.println("\n\n\n\n singleitem end  ");
			 request.getSession().removeAttribute("payment1");
			
			 pagestring= "esewa_single_item";
			
		}
				 				 
				 
			 }		 
		
			 
			 
				 
			 
			
			
		}catch(Exception e) {
			
			return"redirect:/cart";
		}
		
		
	return pagestring;	
		
	
	
}
	



	}

