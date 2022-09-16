package com.example.demo.controller;

import  java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.ProductbuyRepository;
import com.example.demo.dao.Temporary_payment_supportRepository;
import com.example.demo.dao.cartRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.dto.paymentdetail;
import com.example.demo.enittiy.Order;
import com.example.demo.enittiy.Productbuy;
import com.example.demo.enittiy.product;



@Controller

public class BuyController {
	

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
	@Autowired
	ProductbuyRepository productbuyrepo;
	
	
	
	
@RequestMapping("/buy")
 public String buy() {
	
	return "buy";
}


	

	

	@PostMapping("/customer/payment")//goes to either singe item or multiple item
	public String esewasend(@ModelAttribute("payment") paymentdetail payment,@RequestParam("quantity") int quantity,@RequestParam("singleprice") double singleprice,@RequestParam("productbuyid") int productbuyid,HttpServletRequest request,Model model ) {
		
		System.out.println("\n\n\n after payment");
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
		
	long shippingphoneno=payment.getShippingphoneno();
		
	
	
	//to check shipping adress and phone no
		if(shipping_adress.equalsIgnoreCase("shipadress")) {
			shipping_adress="NOTAVAILABLE";
		}
	if(shippingphoneno==10) {
		
		shippingphoneno=0000;
	}
	
	
	
	
	//foe one payment only
		
		int payment1;
		String pagestring = null;

		try {
			
			 payment1 = (int) request.getSession().getAttribute("payment1");
			
			 System.out.print("\n\n\n after session   \n");
			 
			System.out.println("\n\n\npayment1"+payment1);
		
			
		
			if(payment1==1) {
	
		
			
			System.out.println("\n\n\n\n in single item  ");
			//order table
			
			Productbuy buy=productbuyrepo.findById(productbuyid).get();
			
			 Order order=new Order();
			 order.setProductbuy(buy);
			  order.setAdress(adress);
			  order.setShippingadress(shipping_adress);
			  order.setEmail(email);
			  order.setPhonenumber(phone);
			  
			  order.setCustomerid(customer_id);
			  order.setPid(pid);
			  
			  order.setProductid(product_id);
			  order.setTotalamountperitem(tAmt);
			  order.setQuantity(quantity);
			
			System.out.println("\n\n before productid");
			
			  
			  Optional<product> pp=productrepo.findById(product_id);
			  long id=Long.valueOf(pp.get().getShopregisterr().getId());
			  String esewaservicecode=pp.get().getShopregisterr().getEsewaservicecode();
			 
			  String name=pp.get().getName();
			  
			  System.out.println("\nproductnamein order"+name);
			  order.setShopid(id);
			  order.setProductname(name);
				order.setPhoneshipping(shippingphoneno);
			  
			order.setStatus("NOTCONFIRMED");
			order.setDepartstatus("NOTDEPRTED");
			order.setDeliverstatus("NOTDELIVERED");
			 orderrepo.save(order);
			 
			System.out.println("\n\n\n before setting attribute  ");
			
			tAmt=psc+pdc+txAmt+amt;
			
			
			
			model.addAttribute("quantity",quantity);
			model.addAttribute("singleprice",singleprice);
			model.addAttribute("adress",adress);
			model.addAttribute("shipping_adress",shipping_adress);
			model.addAttribute("shippingphoneno",shippingphoneno);
			
			model.addAttribute("phone",phone);
			model.addAttribute("email",email);
			model.addAttribute("txAmt", txAmt);
			model.addAttribute("tAmt",tAmt);
			model.addAttribute("psc",psc);
			model.addAttribute("pdc",pdc);
			model.addAttribute("amt",amt);
			model.addAttribute("pid",pid);
			model.addAttribute("product_id",product_id);
		//	model.addAttribute("esewacode",esewaservicecode);
			
			
			System.out.println("\n\n\n\n singleitem end  ");
			 request.getSession().removeAttribute("payment1");
			
			 pagestring= "esewa_single_item";
			
		
				 				 
				 
			}		 	 
		
			 
			 
				 
			 
			
			
		}catch(Exception e) {
			
			return"redirect:/cart";
		}
		
		
	return pagestring;	
		
	
	
}
	

	@GetMapping("/deliver_otherplace")
	public String deliver(@RequestParam("shipping_adress") String shipping_adress,@RequestParam("shippingphoneno") long phoneno ) {
	
		
		
		
		
		
		
		return shipping_adress;
		
		
		
		
		
	}
	


	}

