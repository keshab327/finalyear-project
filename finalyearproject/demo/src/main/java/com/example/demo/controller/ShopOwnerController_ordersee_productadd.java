package com.example.demo.controller;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.OwnerConfirm_orderRepository;
import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.enittiy.CustomUserDetail;
import com.example.demo.enittiy.Order;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.enittiy.product;



@Controller
public class ShopOwnerController_ordersee_productadd {

	
	
	@Autowired
	private OrderRepository orderrepo;
	
	@Autowired
	private OwnerConfirm_orderRepository orderseerepository;
	
	@Autowired 
	ShopRegisterRepository shopregisterrepository;
	
	
	@Autowired
	private productRepository productrepo;
		
	
	Authentication authentication;
	CustomUserDetail userDetails;
	
	
	

	@GetMapping("/shopowner_home_return")
	public String home(Model model) {
		return "shopowner_home";
	}
	
	
	
	
	
	@RequestMapping("/shopwoner_home")
	public String shopwoner(HttpServletRequest request) {
		
		
		 String email=(String) request.getSession().getAttribute("email");
			//System.out.println("session works :"+email);
			
			Optional<ShopRegister> shopregister=shopregisterrepository.findByEmail(email);
			long shopidd=shopregister.get().getId();
			
			String shopid_string= String.valueOf(shopidd);
			//System.out.println("shopid: "+shopidd);
			request.getSession().setAttribute("shopid", shopid_string);
			
			//System.out.println(" session set in onwer home for shopowner_seeorder ");
		
		
		return "shopowner_home";
	}
	
	@GetMapping("/shopwoner_seeorder")
	public String show(Model model ,HttpServletRequest request)//for session to work use in function parameter of mapping//
	{
		
	
		
		
		String shopid_string;
		try {
		 
			shopid_string	= (String) request.getSession().getAttribute("shopid");
		}catch(Exception e) {
			
			return "redirect:/login";
		}
	int shopid=Integer. parseInt(shopid_string);
	long shopidd=Long.valueOf(shopid);

		
	
	ShopRegister owner=shopregisterrepository.findById(shopidd).get();
	List<Order> orders=owner.getOrder();
	for(Order o:orders) {
		System.out.print("\n\nadressssss "+o.getAdress());
		
	}
		model.addAttribute("orders", orders  );
		model.addAttribute("shopid",shopid);
	
		

	
		
		return "ownerconfirm_order";
		
	}
	
	
	
	@GetMapping("/display/{id}/{shopid}")
	@ResponseBody
	void showImage(@PathVariable("shopid") int shopid,@PathVariable("id") int id, HttpServletResponse response, Optional<Order> order)
			throws ServletException, IOException {
	
		 
		//System.out.println("shopidchaga:"+shopid);
		//System.out.println("in image display id:"+id);
		//order = orderseerepository.getProductimageByIdAndShopid(id,shopid);
		
					//response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
						//response.getOutputStream().write(order.get().getProductImage());
		
		//response.getOutputStream().close();
	}
	
	
	@GetMapping("/vieworderdetails/{orderindex}")
	String showProductDetails(@PathVariable("orderindex") int orderindex,  Model model,HttpServletRequest request) {
	
	//	System.out.println("shopid:"+shopid);
		try {
			
			String shopid_string= (String) request.getSession().getAttribute("shopid");
			int id=Integer. parseInt(shopid_string);
			long shopid=Long.valueOf(id);
			ShopRegister shop=shopregisterrepository.findById(shopid).get();
			List<Order> orderlist=shop.getOrder();
			
			Order order=orderlist.get(orderindex);
			
				 
				
			
			
			
				if (order!=null) {
					
					model.addAttribute("quantity", order.getQuantity());
					
					model.addAttribute("productid", order.getProductid());
					model.addAttribute("productname", order.getProductname());
					model.addAttribute("shippingadress", order.getShippingadress());
					model.addAttribute("shipphone",order.getPhoneshipping());
					model.addAttribute("adress", order.getAdress());
					model.addAttribute("totalamountperitem", order.getTotalamountperitem());
					model.addAttribute("phonenumber", order.getPhonenumber());
					model.addAttribute("email", order.getEmail());
					model.addAttribute("email", order.getEmail());
					model.addAttribute("deliverstatus", order.getDeliverstatus());
					model.addAttribute("departstatus", order.getDepartstatus());
					model.addAttribute("orderedDate", order.getOrderedDate());
					model.addAttribute("id", order.getId());
					model.addAttribute("orderindex", orderindex);
				
					

				}
	
			
			}catch(Exception e) {
				System.out.print("exception occured");
				return "redirect:/login";
			}
		return "show_order_details";
	
	}
	
	
	
	
	
	
	@GetMapping("shopowner/products_by_shopid")
	public String product(Model model,HttpServletRequest request) {
		
		String shopid_string= (String) request.getSession().getAttribute("shopid");
		int shop=Integer. parseInt(shopid_string);
		long shopid=Long.valueOf(shop);
		int pageno=0;
		Pageable pageable=	PageRequest.of(pageno,3);	
		Page<product> productpages= productrepo.findByShopregisterr_Shopownerid(shopid, pageable);
		
		model.addAttribute("productpages", productpages);
		model.addAttribute("currentpageno", pageno);
		model.addAttribute("totalpages",productpages.getTotalPages());
	
		model.addAttribute("product",productpages);
		return "products";
	}
	
	
	
	
	@GetMapping("shopowner/products_by_shopid/{page}")
	public String products(Model model,@PathVariable("page") int pageno,HttpServletRequest request) {
		
		String shopid_string= (String) request.getSession().getAttribute("shopid");
		int shop=Integer. parseInt(shopid_string);
		long shopid=Long.valueOf(shop);
	
		Pageable pageable=	PageRequest.of(pageno,3);	
		Page<product> productpages= productrepo.findByShopregisterr_Shopownerid(shopid, pageable);
		
		model.addAttribute("productpages", productpages);
		model.addAttribute("currentpageno", pageno);
		model.addAttribute("totalpages",productpages.getTotalPages());
	
		model.addAttribute("product",productpages);
		return "products";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

