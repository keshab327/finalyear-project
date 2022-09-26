package com.example.demo.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Path;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.CategoryrequestRepository;
import com.example.demo.dao.DeliveryboyRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.PreviousCountRepository;
import com.example.demo.dao.ProductreportRepository;
import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.productRepository;
import com.example.demo.dto.productDto;
import com.example.demo.enittiy.Category;
import com.example.demo.enittiy.Categoryrequest;
import com.example.demo.enittiy.Deliveryboy;
import com.example.demo.enittiy.Order;
import com.example.demo.enittiy.OwnerConfirmOrder;
import com.example.demo.enittiy.Productreport;
import com.example.demo.enittiy.Role;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.enittiy.User;

import com.example.demo.services.ProductService;
import com.example.demo.services.ShopRegisterService;
import com.example.demo.services.categoryservices;
import com.example.demo.enittiy.product;


@Controller
public class AdminController {
	public static String uploadDir=System.getProperty("user.dir")+"src/main/resources/static/productimage/cotton.jpg";
	@Autowired
	CategoryrequestRepository categoryrequestrepo;
	@Autowired
	categoryservices servicescategory;
	@Autowired
	ProductService productservice;
	
	@Autowired
	UserRepository userrepo;
	@Autowired
	OrderRepository orderepo;
	
	
	@Autowired
	productRepository product_repository;
	@Autowired
	ShopRegisterRepository shoprepo;
	@Autowired
   CategoryRepository category;
	
	@Autowired
	ProductreportRepository reportrepo;
	
	@Autowired
	DeliveryboyRepository deliveryboyrepo;
	
	@Autowired
	RoleRepository rolerepo;
	
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	
	
	@Autowired
	private ShopRegisterService imageGalleryService;
	
	@Autowired
	private  ShopRegisterRepository shopregisteryrepository;
	
	@Autowired
	PreviousCountRepository previousCountrepository;
	
	
	

	/*
	 * @Autowired productDto productDTO;
	 */
	
	/*@GetMapping("/")
	public String firstpage() {
		return "login";
	}*/
	
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	

//	Category section
	
	@GetMapping("/admin/categories")
	public String categories(Model model) {
		model.addAttribute("categories",servicescategory.getAllCategory());
		return "categories";
	}
	@GetMapping("/admin/categories/add")
	public String getcategory(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
	
	@PostMapping("/admin/categories/add")
	public String postcategory(@ModelAttribute("category") Category category) {
		servicescategory.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@PostMapping("/admin/categoriesrequest/add")
	public String categoryrequest(@ModelAttribute("category") Category category,@RequestParam("category_request_id") int catid) {
		servicescategory.addCategory(category);
		Categoryrequest request=categoryrequestrepo.findById(catid).get();
		request.setConfirmstatus("CONFIRMED");
		categoryrequestrepo.save(request);
		
		return "redirect:/admin/seecategory_request";
		
	}
	
	
	
	
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategoryById(@PathVariable int id) {
		servicescategory.deleteCategoryById(id);
		return "redirect:/admin/categories"; 
	}
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategoryById(@PathVariable int id,Model model) {
		Optional<Category> category=servicescategory.getCategoryById(id);
		if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}
		else {
		return "404";
	}}
	
	//product
	
	
	@GetMapping("/products")
	public String product(Model model) {
		model.addAttribute("product",productservice.getAllProduct());
		return "products";
	}
	
	
	@GetMapping("/products/add")
	public String getproduct(Model model) {
		model.addAttribute("product", new productDto());
		model.addAttribute("categories", servicescategory.getAllCategory());
		return "productsAdd";
	}	

	
	
	
	
	
	
	

	  @PostMapping("/products/add") 
	  public String saveProduct(@ModelAttribute("product") productDto productDTO,
			  @RequestParam("productImage") MultipartFile file,
			  @RequestParam("imgName")String imgName,HttpServletRequest request)throws IOException{
		  product products=new product();
		  products.setProduct_id(productDTO.getProduct_id());
		  products.setName(productDTO.getName());
		//  products.setCategory(servicescategory.getCategoryById(productDTO.getCategoryId()).get());
		  
		  products.setCategory(category.findById(productDTO.getCategoryId()).get());
		  products.setDescription(productDTO.getDescription());
		  products.setPrice(productDTO.getPrice());
		  products.setQuantity(productDTO.getQuantity());
		  products.setAvailable("YES");
		  
	  byte[] imageData = file.getBytes();
	  
	  Long owner_id;
		  products.setProduct_image(imageData);
		try {  
	  int idd=Integer. parseInt((String) request.getSession().getAttribute("shopid"));
		
		
		   owner_id=Long.valueOf(idd);
		  
		  String email=(String) request.getSession().getAttribute("email");
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/login";
			
		}
		 Optional<ShopRegister> shop= shoprepo.findById(owner_id);
		  System.out.println("\n\n\nhhhhhhhhhhhhh"+shop.get().getEmail());
		 products.setShopregisterr(shop.get());
		  
		  
		  

		productservice.addProduct(products);
		System.out.println("\n\n\n\ninn add product complete\n\n\n\n\n");
		return "redirect:/shopowner/products_by_shopid"; 

	  
	  }
	  
	  
  
	  
	  @GetMapping("/product/delete/{id}")
		public String deleteProductById(@PathVariable int id) {
			productservice.deleteProductById(id);
			return "redirect:/shopowner/products_by_shopid"; 
		}
	  
	@GetMapping("/product/update/{id}")
	public String updateProduct(@PathVariable int id,Model model) {
		productDto productDTO=new productDto();
		product product=productservice.getProductById(id).get();
		productDTO.setProduct_id(product.getProduct_id());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setQuantity(product.getQuantity());
		
		model.addAttribute("categories", servicescategory.getAllCategory());
		model.addAttribute("product", productDTO);
		return "productupdate";
	}






	@PostMapping("/product/update") 
	public String update(@ModelAttribute("product") productDto productDTO,
			  @RequestParam("productImage") MultipartFile file,
		@RequestParam("imgName")String imgName,HttpServletRequest request)throws IOException{
		
		product products=new product();
		  products.setProduct_id(productDTO.getProduct_id());
		  products.setName(productDTO.getName());
		//  products.setCategory(servicescategory.getCategoryById(productDTO.getCategoryId()).get());
		  
		  products.setCategory(category.findById(productDTO.getCategoryId()).get());
		  products.setDescription(productDTO.getDescription());
		  products.setPrice(productDTO.getPrice());
		  products.setQuantity(productDTO.getQuantity());
		 products.setAvailable("YES");
		  

		 
		 
		  if(file.isEmpty()) {
			  Optional<product> productforimage=product_repository.findById(productDTO.getProduct_id());
			  byte[] imageData=productforimage.get().getProduct_image();
			  products.setProduct_image(imageData);
		  }
		  else {
		  byte[] imageData = file.getBytes();
		  products.setProduct_image(imageData);
		  }
		  
		  
	//setting shop id	  
	Long owner_id;
		
		try {  
	int idd=Integer. parseInt((String) request.getSession().getAttribute("shopid"));
		
	
		   owner_id=Long.valueOf(idd);
		  
		  String email=(String) request.getSession().getAttribute("email");
		}catch(Exception e){
			e.printStackTrace();
			return "redirect:/login";
			
		}
		 Optional<ShopRegister> shop= shoprepo.findById(owner_id);
	
		 products.setShopregisterr(shop.get());
		  
		  
		  

		 product_repository.save(products);
		return "redirect:/shopowner/products_by_shopid"; 


	}



@GetMapping("/display_productimage/{id}")
@ResponseBody
void showImage(@PathVariable("id") int id, HttpServletResponse response, Optional<product> product)
		throws ServletException, IOException {

	 
	
	product=product_repository.findById(id);
	
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
					response.getOutputStream().write(product.get().getProduct_image());
	
	response.getOutputStream().close();
}




@GetMapping("/cartindex/{index}")
@ResponseBody
void index(@PathVariable("index") int index, HttpServletResponse response, Optional<product> product)
		throws ServletException, IOException {

	 
	
	response.setContentType("text/plain"); 
	
	
			int a=index+1;
					response.getOutputStream().write(a);
	
	response.getOutputStream().close();
}








@GetMapping("/admin/seereport")
public String a(Model model ,HttpServletRequest request)//for session to work use in function parameter of mapping//
{
	

     List<Productreport> reports=reportrepo.findAll();
   
	 model.addAttribute("reports",reports);
	

	  return "adminsee_report";
	
}







@GetMapping("/admin/registerdeliveryboy")
public String se()//for session to work use in function parameter of mapping//
{
	

	
	
	return "admin_deliveryboy";
	
}











@PostMapping("/admin/registerdeliveryboy")
public String seereports(@ModelAttribute("deliveryboy") Deliveryboy deliveryboy,@RequestParam("password") String password,Model model ,HttpServletRequest request)//for session to work use in function parameter of mapping//
{
	
	System.out.println("\n\ndelivery details from register"+deliveryboy.getAdress()+"\n"+deliveryboy.getEmail()+"\npassword"+password);
	
	
	User user=new User();
	user.setFirstName(deliveryboy.getFirstname());
	user.setLastName(deliveryboy.getLastname());
	user.setEmail(deliveryboy.getEmail());
	
	user.setPassword(bCryptPasswordEncoder.encode(password));
	user.setEnable("NO");
	
	
	List<Role> roles=new ArrayList<>();
	roles.add(rolerepo.findById(3).get());
	user.setRoles(roles);
	userrepo.save(user);
	
	user=userrepo.findUserByEmail(deliveryboy.getEmail()).get();
	deliveryboy.setUser(user);
	
	deliveryboyrepo.save(deliveryboy);
	return "adminHome";
	
}




@GetMapping("/admin/image/show")
String show(Model map) {
	List<ShopRegister> shops = imageGalleryService.getAllActiveImages();
	map.addAttribute("shops", shops);
	
	long new_count=	shopregisteryrepository.count();
	map.addAttribute("count",new_count);
	String verification="NOT_CONFIRMED";
	long not_confirmed=shopregisteryrepository.countByVerification(verification);
	
//	System.out.println("total:"+new_count);
//	System.out.println("not_confirmed"+not_confirmed);
	
	map.addAttribute("not_confirmed",not_confirmed);
	

	return "shops_of_owner";
}





@GetMapping("/admin/show/deliveryboy")
String showdelivery(Model map) {
	List<Deliveryboy> deliveryboy=deliveryboyrepo.findAll();
	map.addAttribute("deliveryboys", deliveryboy);
	
	
	
  return "admin_see_deliveryboys";
}



@GetMapping("/admin/remove/deliveryboy/{email}/{id}")
String removedelivery(@PathVariable("email") String email,@PathVariable("id") int id,Model map) {
	
	
	deliveryboyrepo.deleteById(id);
	Optional<User> user=userrepo.findUserByEmail(email);
	userrepo.deleteById(user.get().getId());
	
	List<Deliveryboy> deliveryboy=deliveryboyrepo.findAll();
	map.addAttribute("deliveryboys", deliveryboy);
	
	
  return "admin_see_deliveryboys";
}




@GetMapping("/admin/shopprodducts/{page}/{shopid}")
public String products(Model model,@PathVariable("page") int pageno,@PathVariable("shopid") long shopid,HttpServletRequest request) {
	
	

	Pageable pageable=	PageRequest.of(pageno,3);	
	Page<product> productpages= product_repository.findByShopregisterr_Shopownerid(shopid,pageable);
	
	
	model.addAttribute("currentpageno", pageno);
	model.addAttribute("totalpages",productpages.getTotalPages());
      model.addAttribute("products",productpages);
      model.addAttribute("shopid",shopid);
	
	return "adminsee_shopproducts";
	
}



@GetMapping("/admin/seeorders")
public String setorderto_ower(Model model) {
	
	List<Order> orders=orderepo.findByStatus("CONFIRMED");
	model.addAttribute("orders", orders);
	
	 int count =orderepo.countByAdminsendorder("NOTSENT");
		long total= orderepo.count();
		System.out.println("\n\n\n total"+total);
		
model.addAttribute("count", count);	
model.addAttribute("total", total);	
	
	return "admin_see_orders";
	

	
	
	
	
	
	
}






@GetMapping("admin/seeorders/{id}")
public String admin_see_order_details(@PathVariable("id") int id, Optional<ShopRegister> imageGallery, Model model) {

	
		Order order= orderepo.findById(id).get();
		
		
		
			if (order!=null) {
				
				model.addAttribute("quantity", order.getQuantity());
				
				model.addAttribute("productid", order.getProductid());
				model.addAttribute("productname", order.getProductname());
				
				
				model.addAttribute("provience",order.getProvience());
				model.addAttribute("district",order.getDistrict());
				model.addAttribute("wardno",order.getWardno());
				
				model.addAttribute("shippingdistrict",order.getShippingdistrict());
				model.addAttribute("shippingprovience",order.getShippingprovience());
				model.addAttribute("shippingwardno",order.getShippingwardno());
				
				
				
				model.addAttribute("shipphone",order.getPhoneshipping());
			
				model.addAttribute("totalamountperitem", order.getTotalamountperitem());
				model.addAttribute("phonenumber", order.getPhonenumber());
				model.addAttribute("email", order.getEmail());
				model.addAttribute("email", order.getEmail());
				model.addAttribute("deliverstatus", order.getDeliverstatus());
				model.addAttribute("departstatus", order.getDepartstatus());
				model.addAttribute("orderedDate", order.getOrderedDate());
				model.addAttribute("id", order.getId());
				model.addAttribute("shopid", order.getShopid());
				
			model.addAttribute("send_status", order.getAdminsendorder());
				

			}

		
		
		
	return "adminseeorder_detail";

}

@GetMapping("/admin/send_order_shop/{orderid}")
public String sendToShop(@PathVariable("orderid") int orderid) {
	
	Order o=orderepo.findById(orderid).get();
	o.setAdminsendorder("SENT");
	orderepo.save(o);
	ShopRegister shop=shoprepo.findById(o.getShopid()).get();
	List<Order> orders=shop.getOrder();
	orders.add(o);
	shoprepo.save(shop);
	
	
	
	return "redirect:/admin/seeorders";
}


@GetMapping("/admin/seecategory_request")

public String see(Model model) {
	
List<Categoryrequest> requests	=categoryrequestrepo.findAll();
	model.addAttribute("categories", requests);

return "admin_see_category_requests";
}


@GetMapping("/admin/confirmcategory")

public String confirmcategory(@RequestParam("category_request") int id,Model model) {
	
	System.out.println("\n\n\n idd"+id);
	
Categoryrequest request	=categoryrequestrepo.findById(id).get();


model.addAttribute("categoryname", request.getCategoryname());
	model.addAttribute("categories", request.getDescription());
	model.addAttribute("category_request_id", request.getCategoryrequestid());

return "category_request_add";
}




@GetMapping("/admin/see_category_image/{id}")
@ResponseBody
void showcategoryreq(@PathVariable("id") int id, HttpServletResponse response, Optional<product> product)
		throws ServletException, IOException {

	 System.out.print("\n\n\n iddddd in image display"+id);
	
	Categoryrequest ca=categoryrequestrepo.findById(id).get();
	
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
					response.getOutputStream().write(ca.getCategoryimage());
	
	response.getOutputStream().close();
}






}




