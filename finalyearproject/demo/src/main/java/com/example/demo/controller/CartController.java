package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.Temporary_payment_supportRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserdetailRepository;
import com.example.demo.dao.cartRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.enittiy.Cart;
import com.example.demo.enittiy.Category;
import com.example.demo.enittiy.Order;
import com.example.demo.enittiy.Temporary_support_payment;
import com.example.demo.enittiy.product;
import com.example.demo.services.ProductService;

@Controller
public class CartController {
	
	@Autowired
	ProductService productService;
	@Autowired
	productRepository productrepo;
	@Autowired
	cartRepository cartrepo;
	@Autowired
	CategoryRepository categoryrepo;
	@Autowired
	Temporary_payment_supportRepository temprepo;
	
	@Autowired
	UserRepository userrepo;
	@Autowired
	UserdetailRepository userdetailrepo;
	
	@Autowired
	OrderRepository  orderrepo;
	








	
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id, HttpServletRequest request) {
		  
int user_id;
try {
	  user_id = (int) request.getSession().getAttribute("user_id");
	 System.out.println("\n\n in add to cart"+user_id);
	 
	 System.out.println("in sesssionjj");
		 
		
		 }catch(Exception e) {
			 System.out.println("\n\n session not set");
			 return "redirect:/register";
		 }
			 




		 //creting if cart is not present
		  
		  Cart cart=new Cart();
		  List<product> productlist=new ArrayList<product>();
		Optional<Cart> ca=cartrepo.findByUserdetail_Userdetailid(user_id);
		if(ca.isEmpty()) {
			cart.setUserdetail(userdetailrepo.findById(user_id).get());
		
			
		}else {
			cart=ca.get();
			productlist=cart.getProducts();
		}
		
		
		//adding product
		product product1= productrepo.findById(id).get(); 
		
	
		productlist.add(product1);
	
		cart.setProducts(productlist);
		cartrepo.save(cart);
		
	
	
		
		return "redirect:/cart";
		
	}

		

		
	
	
	
	@GetMapping("/cart")
	public String cartGet(Model model,HttpServletRequest request)
	{
		
		
		int user_id;
		request.getSession().setAttribute("payment1", 1);
		try {
			 user_id = (int) request.getSession().getAttribute("userid");
		
				 
				
				 }catch(Exception e) {
					 System.out.println("\n\n session not set");
					 return "redirect:/register";
				 }
		
		
		
		
		
		
		
		
		
		
		String s ="NOTCONFIRMED";
		List<Order> 	 order=orderrepo.findAllByCustomeridAndStatus(user_id, s);
		 
		 for(Order o:order) {
			 
			
			 orderrepo.deleteById(o.getId());
			 
		 }
		
	

		int count=0;
		Optional<Cart> ca=cartrepo.findByUserdetail_Userdetailid(user_id);
		
		
	List<product> products=ca.get().getProducts();
	double total_tax=0,total_psc=0,total_pdc=0,total_price=0,total_amount=0;
	for(product p:products) {
		Category c=new Category();
		c=p.getCategory();
		total_tax=total_tax+c.getProduct_tax()/100*p.getPrice();
		total_psc=total_psc+c.getProduct_service_charge();
		total_pdc=total_pdc+c.getProduct_delivery_charge();
		total_price=total_price+p.getPrice();
	
		count=count+1;
	}
	
	total_amount=total_tax+total_psc+total_pdc+total_price;
		
	
		float sum=0;
		

		
		System.out.println("\n\n\n sum in add cart"+sum);
		model.addAttribute("cartCount",count);
		model.addAttribute("total",total_amount);
		model.addAttribute("total_price_items",total_price);
		model.addAttribute("total_tax",total_tax);
		model.addAttribute("total_psc",total_psc);
		model.addAttribute("total_pdc",total_pdc);
	model.addAttribute("cartid",ca.get().getId());
		
		
		
		model.addAttribute("cart",products);
		
		
	   
	     return "cart";
	}
	

	
	@GetMapping("/cart/removeItem/{index}/{cartindex}")
	public String cartItemRemove(@PathVariable("index") int cartid,@PathVariable("cartindex") int cartindex,HttpServletRequest request) {
	     

		Cart cart=cartrepo.findById(cartid).get();
		List<product> products=cart.getProducts();
		products.remove(cartindex);
	    cart.setProducts(products);
       cartrepo.save(cart);
		
		
		return "redirect:/cart";
	}
	
	
	
	
	@GetMapping("/cart/buysingleItem/{productid}/{cartid}/{cartindex}")
	public String cartbuyRemove(@PathVariable("productid") int productid,@PathVariable("cartid") int cartid,@PathVariable("cartindex") int cartindex,Model model,HttpServletRequest request) {
		System.out.println("\n product_ididid"+productid);
		
		 request.getSession().setAttribute("payment1", 1);
		
		Cart cart=cartrepo.findById(cartid).get();
		
	List<product> products=cart.getProducts();
	
              product p=	products.get(cartindex);
	
	   double tax=0,psc=0,pdc=0,price=0,total=0;
	    
	
					Category c=p.getCategory();
			tax=(c.getProduct_tax()/100*p.getPrice());
			psc=c.getProduct_service_charge();
			pdc=c.getProduct_delivery_charge();
			price=p.getPrice();
		String name=p.getName();
			
			System.out.print("\n\n\n\npriceeeeeeeeee  "+p.getPrice()+"\n\n\n");
		
	total=price+tax+psc+pdc;
		
		
		
		model.addAttribute("amt",price);
		model.addAttribute("txAmt",tax);
		model.addAttribute("psc",psc);
		model.addAttribute("pdc",pdc);
		model.addAttribute("tAmt",total);
		model.addAttribute("product_id",productid);
		model.addAttribute("product_name",name);
		model.addAttribute("cartindex", cartindex);
		model.addAttribute("cartid", cartid);
		

		int min=10000000;
		int max=99999900;
		int number =(int)(Math.random()*(max-min+1)+min);
		model.addAttribute("transaction_id",number);
		System.out.println("\n\n\n transactionid   "+number);
	
	
	return "checkout";
	///	return "esewa_single_item";
	}
	
	
	
	
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		
return "checkout";
		
		
	}
	
	
	

	
	


	@GetMapping("/customer/allpayment/{cartid}")
	public String multipleitemt(@PathVariable("cartid") int cartid,Model model,HttpServletRequest request)
	{
		 temprepo.deleteAll();
		
		
				
		 request.getSession().setAttribute("payment1", 1);
		Cart cart=cartrepo.findById(cartid).get();
	    List<product> products=cart.getProducts();
	    
	    
		double total_txAmt=0,total_psc=0,total_pdc=0,total_price=0,total_amount=0;
	
		
		for (product p:products) {
		    total_price=(double) (total_price+p.getPrice()) ;
		
		    total_txAmt=(p.getCategory().getProduct_tax())*p.getPrice()+total_txAmt;
		    total_pdc=p.getCategory().getProduct_delivery_charge()+total_pdc;
		    total_psc=p.getCategory().getProduct_service_charge()+total_pdc;
		 
		    String productname=p.getName();
		    Category cat=p.getCategory();
		    //tempdatabase
            double total=(cat.getProduct_tax())/100*p.getPrice()+cat.getProduct_delivery_charge()+cat.getProduct_service_charge()+p.getPrice();
		    Temporary_support_payment temp=new   Temporary_support_payment();
		    temp.setProduct_id(p.getProduct_id());
		    temp.setTotal_peritem_amount(total);
		    temp.setProductname(productname);
		    temprepo.save(temp);
		    
		    
		    
		    }
	
		
	 total_amount=total_txAmt+total_psc+total_pdc+total_price;
		
		
		

		
		
		model.addAttribute("amt",total_price);
		model.addAttribute("txAmt",total_txAmt);
		model.addAttribute("psc",total_psc);
		model.addAttribute("pdc",total_pdc);
		model.addAttribute("tAmt",total_txAmt);
		model.addAttribute("product_id",0);
		model.addAttribute("cartindex", -5);
		model.addAttribute("cartid", cartid);
		
		
		
		

		int min=10000000;
		int max=99999900;
		int number =(int)(Math.random()*(max-min+1)+min);
		model.addAttribute("transaction_id",number);
		System.out.println("\n\n\n transactionid   "+number);
		
		
		
		return "checkout";
	
	
	
	
	
	
	}	
	

	
@GetMapping("/createcart")

public String cartcreate(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws ServletException{
	int user_id;
	
	try {
		 user_id = (int) request.getSession().getAttribute("userid");
		System.out.println("\n\n in add to cart"+user_id);

			 
			
			 }catch(Exception e) {
				 System.out.println("\n\n session not set");
				 return "redirect:/register";
			 }
	
	Cart cart=new Cart();
Optional<Cart> cartpresent=cartrepo.findByUserdetail_Userdetailid(user_id);
	if(cartpresent.isEmpty()) {
	
		
	    cart.setUserdetail(userdetailrepo.findById(user_id).get());
	    cartrepo.save(cart);
	    return "redirect:/cart";
		
	}
	else {
		cart=cartpresent.get();
		  return "redirect:/cart";
	}
	

}
}
   
	
	
	
  
	
	

	




