package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dao.OwnerConfirm_orderRepository;
import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.enittiy.OwnerConfirmOrder;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.enittiy.product;

@Controller
public class CustomerGiveOrderController {

	@Autowired
	productRepository productrepository;
	
	@Autowired
	private  ShopRegisterRepository shopregisteryrepository;
	@Autowired
	private OwnerConfirm_orderRepository ordersave;
	
	@GetMapping("/customer_order")
	public String orderSave()
	
	{
		
		int productid=1;
		Optional<product> product=productrepository.findById(productid);
		
		String productname=product.get().getName();
		double price=product.get().getPrice();
		String orderadress="dhangadhi pashupatitwol";
	  String shippingadress="dharam shampangko ghar";
	  int shopid=98;
	  String status="NOT_CONFIRMED";
	  Date orderDate = new Date();
	  
	 
	  long customernumber=986867900;
	  long id=21;//might give problem
	  
	Optional<ShopRegister> shop = shopregisteryrepository.findById(id);
	 byte[] productimage=shop.get().getImage();
	//temporarily saving table
 OwnerConfirmOrder order=new OwnerConfirmOrder();
 order.setProductname(productname);
 order.setPrice(price);
 order.setOrderadress(orderadress);
 order.setShippingadress(shippingadress);
 order.setStatus(status);
 order.setShopid(shopid);
 order.setProduct_id(productid);
 order.setOrderDate(orderDate);
 order.setProductImage(productimage);
 order.setCustomerphonenumbr(customernumber);
 

 System.out.println("\nsuccess");
 System.out.println(productname);
 System.out.println(price);
 
 ordersave.save(order);
	  
	 
		
		
		
	return "redirect:/shopwoner_home";
		
		
	}
}
