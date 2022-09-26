package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.DistrictsReoistory;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.ProductbuyRepository;
import com.example.demo.dao.ProvienceRepository;
import com.example.demo.dao.Temporary_payment_supportRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserdetailRepository;
import com.example.demo.dao.cartRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.enittiy.Cart;
import com.example.demo.enittiy.Order;
import com.example.demo.enittiy.Productbuy;
import com.example.demo.enittiy.Provience;
import com.example.demo.enittiy.Userdetail;
import com.example.demo.enittiy.product;
import com.example.demo.services.ProductService;

@Controller
public class CartController {
	
	@Autowired
	ProvienceRepository proviencerepo;
	@Autowired
	DistrictsReoistory districtrepo;
	
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
	
	@Autowired
	ProductbuyRepository productbuyrepo;
	








	
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id, HttpServletRequest request,Model model) {
		  
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
		 
		Optional<Cart> ca=cartrepo.findByUserdetail_Userdetailid(user_id);
		if(ca.isEmpty()) {
			cart.setUserdetail(userdetailrepo.findById(user_id).get());
			cartrepo.save(cart);
			
		}		
		 
		Optional<Cart> caa=cartrepo.findByUserdetail_Userdetailid(user_id);
		int cartid=caa.get().getCartid();
		cart=caa.get();
	
		int quantity;
	product product=productrepo.findById(id).get();
	double psc=product.getCategory().getProduct_service_charge();
	double pdc=product.getCategory().getProduct_delivery_charge();
	double tax=product.getCategory().getProduct_tax();
	double txamt=(tax/100)*product.getPrice();
	double price=product.getPrice();
	double total=psc+pdc+txamt+price;
	
	
	
	
		Productbuy buy=new Productbuy();
		Optional<Productbuy> p= productbuyrepo.findByProducttt_productidAndCart_cartid(id ,cartid);
		if(p.isEmpty()) {
			
			buy.setCart(cart);
			buy.setProducttt(productrepo.findById(id).get());
		
			buy.setPdc(pdc);
			buy.setPsc(psc);
			buy.setTotaltax(txamt);
			buy.setTotal(total);
			
			
		}else {
			
			buy=p.get();
		
			
		}
		
		
		
		
		productbuyrepo.save(buy);
		
	
	
	
		//cart.setProducts(productlist);
		model.addAttribute("available_quantity",productrepo.findById(id).get().getQuantity());
		
		
	
	
		
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
		
		
		
		
	//delete not confirmed orders	
		String s ="NOTCONFIRMED";
		List<Order> 	 order=orderrepo.findAllByCustomeridAndStatus(user_id, s);
		 
		 for(Order o:order) {
			 
			
			 orderrepo.deleteById(o.getId());
			 
		 }
		
	
		 
		 
		  
		  Cart cart=new Cart();
		 
		Optional<Cart> ca=cartrepo.findByUserdetail_Userdetailid(user_id);
		if(ca.isEmpty()) {
			cart.setUserdetail(userdetailrepo.findById(user_id).get());
			cartrepo.save(cart);
			
		}		
		 
		
		//if cart is present
		Optional<Cart> caa=cartrepo.findByUserdetail_Userdetailid(user_id);
		int cartid=caa.get().getCartid();
		cart=caa.get();
	
	
		
		
int count=0;
		Productbuy buy=new Productbuy();
		List<Productbuy> products= productbuyrepo.findByCart_cartid(cartid);
		for(Productbuy p:products) {
			count++;
		}
		
		model.addAttribute("count",count);
		model.addAttribute("cart",products);
		
		
	   
	     return "cart";
	}
	

	
	@GetMapping("/cart/removeItem/{productbuyid}")
	public String cartItemRemove(@PathVariable("productbuyid") int id) {
	     

	productbuyrepo.deleteById(id);
       
		
		
		return "redirect:/cart";
	}
	
	
	
	
	@GetMapping("/cart/Item/")
	public String cartbuyRemove(@RequestParam("productid") int productbuyid,@RequestParam("quantity") int quantity,HttpServletRequest request,Model model) {
	
		request.getSession().setAttribute("payment1", 1);
		
	
		
		
		//to fetch qunantity
		
		int user_id;
		request.getSession().setAttribute("payment1", 1);
		try {
			 user_id = (int) request.getSession().getAttribute("userid");
		
				 
				
				 }catch(Exception e) {
					 System.out.println("\n\n session not set");
					 return "redirect:/register";
				 }
		
		//finding available quantity
		
		  Productbuy buy=productbuyrepo.findById(productbuyid).get();
		product p=productrepo.findById(buy.getProducttt().getProductid()).get();
	
		
	
	  
	
			
			   double tax=quantity*buy.getTotaltax(),psc=buy.getPsc(),pdc=buy.getPdc(),price=buy.getProducttt().getPrice()*quantity;	
		
		
double	total=price+tax+psc+pdc;


		model.addAttribute("quantity",quantity);
		model.addAttribute("singleprice",buy.getProducttt().getProductid());
		model.addAttribute("amt",price);
		model.addAttribute("txAmt",tax);
		model.addAttribute("psc",psc);
		model.addAttribute("pdc",pdc);
		model.addAttribute("tAmt",total);
		
		
		  System.out.print("\n\n\nafter total amount\n\n");
		
		model.addAttribute("product_id",buy.getProducttt().getProduct_id());
		model.addAttribute("product_name",buy.getProducttt().getName());
		model.addAttribute("productbuyid", productbuyid);
		model.addAttribute("cartid", 0);
		model.addAttribute("shippingadress","keshab");
		
		model.addAttribute("ship","FALSE");
		

		int min=10000000;
		int max=99999900;
		int number =(int)(Math.random()*(max-min+1)+min);
		model.addAttribute("transaction_id",number);
		System.out.println("\n\n\n transactionid   "+number);
	
		
		//set name and adress
		
		Userdetail user=userdetailrepo.findById(user_id).get();
		String firstname=user.getFirstname();
		String lastname=user.getLastname();
		String email=user.getEmail();
		String provience=user.getProvience();
		String district=user.getDistrict();
		String wardno=user.getWardno();
		
		
		//
		
		model.addAttribute("firstname", firstname);
		model.addAttribute("lastname",lastname);
		model.addAttribute("email", email);
		model.addAttribute("provience", provience);
		model.addAttribute("district", district);
		model.addAttribute("wardno", wardno);
		
		
		
		
		//Set adress filds
		
		List<Provience> proviences=proviencerepo.findAll();
		model.addAttribute("proviences", proviences);
		
	
		
		model.addAttribute("p1", districtrepo.findByProviec_Provienceid(1));
		model.addAttribute("p2", districtrepo.findByProviec_Provienceid(2));
		model.addAttribute("p3", districtrepo.findByProviec_Provienceid(3));
		model.addAttribute("p4", districtrepo.findByProviec_Provienceid(4));
		model.addAttribute("p5", districtrepo.findByProviec_Provienceid(5));
		model.addAttribute("p6", districtrepo.findByProviec_Provienceid(6));
		model.addAttribute("p7", districtrepo.findByProviec_Provienceid(7));
		
		
		
	
	return "checkout";
	
	  
	 
	}
	
	
	
	

	@GetMapping("/directbuy/{id}")
	public String directbuy(@PathVariable("id") int id,HttpServletRequest request,Model model) {
		  
		request.getSession().setAttribute("payment1", 1);
		
		
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
		 
		Optional<Cart> ca=cartrepo.findByUserdetail_Userdetailid(user_id);
		if(ca.isEmpty()) {
			cart.setUserdetail(userdetailrepo.findById(user_id).get());
			cartrepo.save(cart);
			
		}		
		 
		Optional<Cart> caa=cartrepo.findByUserdetail_Userdetailid(user_id);
		int cartid=caa.get().getCartid();
		cart=caa.get();
	
		int quantity;
	product product=productrepo.findById(id).get();
	double psc=product.getCategory().getProduct_service_charge();
	double pdc=product.getCategory().getProduct_delivery_charge();
	double tax=product.getCategory().getProduct_tax();
	double txamt=(tax/100)*product.getPrice();
	double price=product.getPrice();
	double total=psc+pdc+txamt+price;
	
	
	
	
		Productbuy buy=new Productbuy();
		Optional<Productbuy> p= productbuyrepo.findByProducttt_productidAndCart_cartid(id ,cartid);
		if(p.isEmpty()) {
			
			buy.setCart(cart);
			buy.setProducttt(productrepo.findById(id).get());
		
			buy.setPdc(pdc);
			buy.setPsc(psc);
			buy.setTotaltax(txamt);
			buy.setTotal(total);
			
			
		}else {
			
			buy=p.get();
		
			
		}
		
		
		
		
		productbuyrepo.save(buy);
		
		
		//fetch from Productbuy repo
		
		
		
		Optional<Productbuy> bu= productbuyrepo.findByProducttt_productidAndCart_cartid(id ,cartid);
		int productbuyid=bu.get().getProductbuyid();
		
		
		
		
		
		
		//buyyy

		  Productbuy buyy=productbuyrepo.findById(productbuyid).get();
	//	product p=productrepo.findById(buyy.getProducttt().getProductid()).get();
	
		
	quantity=1;
	  
	
			
			   double taxx=quantity*buyy.getTotaltax(),pscc=buyy.getPsc(),pdcc=buyy.getPdc(),pricee=buyy.getProducttt().getPrice()*quantity;	
		
		
double	totall=price+tax+psc+pdc;


		model.addAttribute("quantity",quantity);
		model.addAttribute("singleprice",buyy.getProducttt().getProductid());
		model.addAttribute("amt",price);
		model.addAttribute("txAmt",tax);
		model.addAttribute("psc",psc);
		model.addAttribute("pdc",pdc);
		model.addAttribute("tAmt",total);
		
		
		  System.out.print("\n\n\nafter total amount\n\n");
		
		model.addAttribute("product_id",buyy.getProducttt().getProduct_id());
		model.addAttribute("product_name",buyy.getProducttt().getName());
		model.addAttribute("productbuyid", productbuyid);
		model.addAttribute("cartid", 0);
		model.addAttribute("shippingadress","keshab");
		
		model.addAttribute("ship","FALSE");
		

		int min=10000000;
		int max=99999900;
		int number =(int)(Math.random()*(max-min+1)+min);
		model.addAttribute("transaction_id",number);
		System.out.println("\n\n\n transactionid   "+number);
		
		
		
		
		Userdetail user=userdetailrepo.findById(user_id).get();
		String firstname=user.getFirstname();
		String lastname=user.getLastname();
		String email=user.getEmail();
		String provience=user.getProvience();
		String district=user.getDistrict();
		String wardno=user.getWardno();
		
		
		//
		
		model.addAttribute("firstname", firstname);
		model.addAttribute("lastname",lastname);
		model.addAttribute("email", email);
		model.addAttribute("provience", provience);
		model.addAttribute("district", district);
		model.addAttribute("wardno", wardno);
		
		
		
	
		List<Provience> proviences=proviencerepo.findAll();
		model.addAttribute("proviences", proviences);
		
	
		
		model.addAttribute("p1", districtrepo.findByProviec_Provienceid(1));
		model.addAttribute("p2", districtrepo.findByProviec_Provienceid(2));
		model.addAttribute("p3", districtrepo.findByProviec_Provienceid(3));
		model.addAttribute("p4", districtrepo.findByProviec_Provienceid(4));
		model.addAttribute("p5", districtrepo.findByProviec_Provienceid(5));
		model.addAttribute("p6", districtrepo.findByProviec_Provienceid(6));
		model.addAttribute("p7", districtrepo.findByProviec_Provienceid(7));
		
		
		
		
		
		
		
		
		
		
		
		
	return "checkout";
	
		
		
		
		

		
	
	  
	 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		
return "checkout";
		
		
	}
	
	
	
	/*
	
	


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

*/
}
   
	
	
	
  
	
	

	




