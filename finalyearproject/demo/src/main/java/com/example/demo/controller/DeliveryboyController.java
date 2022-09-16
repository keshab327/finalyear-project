package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.DeliveryboyRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.dao.UserdetailRepository;
import com.example.demo.enittiy.Deliveryboy;
import com.example.demo.enittiy.Order;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.enittiy.Userdetail;
import com.example.demo.services.Emailservice;

@Controller
public class DeliveryboyController {

	@Autowired
	OrderRepository orderrepo;
	
	
	@Autowired
	Emailservice emailservice;
	
	@Autowired
	UserdetailRepository userdetailrepo;
	
	@Autowired
	ShopRegisterRepository shopregisterrepo;
	@Autowired
	DeliveryboyRepository deliveryboyrepo;
	
	
	@GetMapping("/Deliveryboy/search")
	public String search(Model model) {
	model.addAttribute("found", "YES");
	return "deliveryboy_searchproduct";
		
}
	
	
	
	
	
	@PostMapping("/deliveryboy/updatestatus/")
	String showProductDetails(@RequestParam("orderid") int orderid,@RequestParam("customerid") int customerid ,Model model,HttpServletRequest request) {
		
		String a="empty";
		System.out.println("shopid:"+orderid);
		try {
			
			
				
				
				 a=addattribute(orderid, customerid,model) ;
			
			
			}catch(Exception e) {
				System.out.print("exception occured");
				return "redirect:/Deliveryboy/search";
			}
		
		if(a.equalsIgnoreCase("empty")) {
	   
			model.addAttribute("found", "NO");
			return "deliveryboy_searchproduct";
		
			
		}
		else {
			model.addAttribute("found", "YES");
			return "deliveryboy_update_deliverystatus";
		}
	
	
	}
	
	
	
	
	
	
	
	

	@GetMapping("/deliveryboy/updatestatus/{orderid}/{customerid}")
	String changestatus(@PathVariable("orderid") int orderid,@PathVariable("customerid") int customerid, Model model,HttpServletRequest request) {
		
		int deliveryboy_id=0;
		
		try {
			deliveryboy_id=	(int) request.getSession().getAttribute("delivery_boy_id");
		}catch(Exception e) {
		
			return "redirect:/login";
		}
		
		
		Userdetail customer=userdetailrepo.findById(customerid).get();
		List<Order> orders=customer.getOrder();
		Order order=new Order();
		int i=0;
	long shopid=0;
		Date date=new Date();
		
		for(Order o:orders)
		{ 
			i++;
		if(o.getId()==orderid) {
			o.setDeliverstatus("DELIVERED");
			o.setDelivereddate(date);
		    orders.remove(i-1); 
			
			orders.add(o);
		  
			shopid=o.getShopid();
		     order=o;
		     break;
		}
	}
		
		
		customer.setOrder(orders);
		
		
		i=0;
		ShopRegister shop=shopregisterrepo.findById(shopid).get();
		orders=shop.getOrder();
		for(Order o:orders)
		{ 
			i++;
		if(o.getId()==orderid) {
			o.setDeliverstatus("DELIVERED");
			o.setDelivereddate(date);
		    orders.remove(i-1); 
			
			orders.add(o);
		  
			shopid=o.getShopid();
		     order=o;
		     break;
		}
	}
		
		
		shop.setOrder(orders);
		shopregisterrepo.save(shop);
		
	     
		Deliveryboy deliveryboy=deliveryboyrepo.findById(deliveryboy_id).get();
		 orders=deliveryboy.getOrdersbydeliveryboy();
		
	
		for(Order o:orders)
		{ 
			i++;
		if(o.getId()==orderid) {
			o.setDeliverstatus("DELIVERED");
			o.setDelivereddate(date);
		    orders.remove(i-1); 
			
			orders.add(o);
		  
			shopid=o.getShopid();
		     order=o;
		     break;
		}
	}
		
		deliveryboy.setOrdersbydeliveryboy(orders);
		deliveryboyrepo.save(deliveryboy);
		
		
		String a=addattribute(orderid,customerid, model);
		
		 return "deliveryboy_update_deliverystatus";
			
	

	
	}
	
	
	
	
	
	
	
	
	@PostMapping("/deliveryboy/location")
	String locationupdate(@RequestParam("orderid") int orderid,@RequestParam("customerid") int customerid,@RequestParam("lastlocation") String lastlocation ,Model model,HttpServletRequest request) {
		
		int deliveryboy_id=0;
		
		try {
			deliveryboy_id=	(int) request.getSession().getAttribute("delivery_boy_id");
		}catch(Exception e) {
		
			return "redirect:/login";
		}
		
		
		Userdetail customer=userdetailrepo.findById(customerid).get();
		List<Order> orders=customer.getOrder();
		Order order=new Order();
		int i=0;
	long shopid=0;
		Date date=new Date();
		
		for(Order o:orders)
		{ 
			i++;
		if(o.getId()==orderid) {
			o.setCurrentadress(lastlocation);
		    orders.remove(i-1); 
			
			orders.add(o);
		  
			shopid=o.getShopid();
		     order=o;
		     break;
		}
	}
		
		
		customer.setOrder(orders);
		
		
		i=0;
		ShopRegister shop=shopregisterrepo.findById(shopid).get();
		orders=shop.getOrder();
		for(Order o:orders)
		{ 
			i++;
		if(o.getId()==orderid) {
			o.setCurrentadress(lastlocation);
		    orders.remove(i-1); 
			
			orders.add(o);
		  
			shopid=o.getShopid();
		     order=o;
		     break;
		}
	}
		
		
		shop.setOrder(orders);
		shopregisterrepo.save(shop);
		
	     
		Deliveryboy deliveryboy=deliveryboyrepo.findById(deliveryboy_id).get();
		 orders=deliveryboy.getOrdersbydeliveryboy();
		
	
		for(Order o:orders)
		{ 
			i++;
		if(o.getId()==orderid) {
			o.setCurrentadress(lastlocation);
			
		    orders.remove(i-1); 
			
			orders.add(o);
		  
			shopid=o.getShopid();
		     order=o;
		     break;
		}
	}
		
		deliveryboy.setOrdersbydeliveryboy(orders);
		deliveryboyrepo.save(deliveryboy);
		
		String a=addattribute(orderid,customerid, model);
		
		 return "deliveryboy_update_deliverystatus";
			
	

	
	}
	
	
	
	
	
	
	
	
public String addattribute(int orderid,int customerid,Model model)
{
	
	String a="empty";


Optional<Userdetail> customer=userdetailrepo.findById(customerid);
List<Order> orders=new ArrayList<Order>();

Order order=new Order();
if(customer.isEmpty()) {
	
	a="empty";
	System.out.println("\n\n\n in customer empty");
	
}else {
	
	orders=customer.get().getOrder();
	
	for(Order o:orders)
	{
		if(o.getId()==orderid)
		{
			System.out.println("\n\n\n in order not empty");
			a="notempty";
			order=o;
			break;
		
		}
	
		}
}



model.addAttribute("quantity", order.getQuantity());
	model.addAttribute("productid", order.getProductid());
	model.addAttribute("productname", order.getProductname());
	model.addAttribute("shippingadress", order.getShippingadress());
	model.addAttribute("shipphone",order.getPhoneshipping());
	model.addAttribute("adress", order.getAdress());
	model.addAttribute("totalamountperitem", order.getTotalamountperitem());
	model.addAttribute("phonenumber", order.getPhonenumber());
	model.addAttribute("email", order.getEmail());

	model.addAttribute("deliverstatus", order.getDeliverstatus());
	model.addAttribute("departstatus", order.getDepartstatus());
	model.addAttribute("orderedDate", order.getOrderedDate());
	model.addAttribute("id", order.getId());
	model.addAttribute("currentadress", order.getCurrentadress());
	model.addAttribute("productid", order.getProductid());
	model.addAttribute("customerid", order.getCustomerid());
	
	
	
if(a.equals("notempty")) {

	System.out.println("\\n\n not empty "+a);
	return order.getEmail();

	
}
else {
	System.out.println("\n\n  empty "+a);
	return a;
}

}
	
	
	

	
	
public int deliveryBoyEmail(String currentlocation,String customeremail) {
	
	
	
	 customeremail="jkeshab570@gmail.com";
	String inside_text="\n\nyour product has reached to the location\n "+currentlocation;
	String subject="From efancy";
	String a=emailservice.sendEmail(customeremail, subject, inside_text);
	
	
	
	return 1;
}
	
	
	





	

}