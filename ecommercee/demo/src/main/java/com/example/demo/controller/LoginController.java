   package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.DistrictsReoistory;
import com.example.demo.dao.ProvienceRepository;
import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserdetailRepository;
import com.example.demo.enittiy.Provience;
import com.example.demo.enittiy.Role;
import com.example.demo.enittiy.User;
import com.example.demo.enittiy.Userdetail;
import com.example.demo.services.Emailservice;


@Controller
public class LoginController {
@Autowired
ProvienceRepository proviencerepo;
@Autowired
DistrictsReoistory districtrepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	Emailservice emailservice;
	@Autowired
	UserdetailRepository userdetailrepo;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String login() {
		return "login";
		
	}
	@GetMapping("/register")
	public String register(Model model){
		
		List<Userdetail> users=userdetailrepo.findAll();
		if(users.isEmpty()) {
			List<Provience> proviences=proviencerepo.findAll();
			model.addAttribute("proviences", proviences);
			
		
			
			model.addAttribute("p1", districtrepo.findByProviec_Provienceid(1));
			model.addAttribute("p2", districtrepo.findByProviec_Provienceid(2));
			model.addAttribute("p3", districtrepo.findByProviec_Provienceid(3));
			model.addAttribute("p4", districtrepo.findByProviec_Provienceid(4));
			model.addAttribute("p5", districtrepo.findByProviec_Provienceid(5));
			model.addAttribute("p6", districtrepo.findByProviec_Provienceid(6));
			model.addAttribute("p7", districtrepo.findByProviec_Provienceid(7));
			
			
			return "/register";
		}
		else {
		for(Userdetail u:users) {
			if(u.getCheckk()==0)
			{
				int id=u.getUserdetailid();
				userdetailrepo.deleteById(id);
				
			}
			
		}
		}
	    
		List<Provience> proviences=proviencerepo.findAll();
		model.addAttribute("proviences", proviences);
		
	
		
		model.addAttribute("p1", districtrepo.findByProviec_Provienceid(1));
		model.addAttribute("p2", districtrepo.findByProviec_Provienceid(2));
		model.addAttribute("p3", districtrepo.findByProviec_Provienceid(3));
		model.addAttribute("p4", districtrepo.findByProviec_Provienceid(4));
		model.addAttribute("p5", districtrepo.findByProviec_Provienceid(5));
		model.addAttribute("p6", districtrepo.findByProviec_Provienceid(6));
		model.addAttribute("p7", districtrepo.findByProviec_Provienceid(7));
		
		
		return "register";
	    
		
		
		
		
	}
	@PostMapping("/register")
	public String registerHere(@ModelAttribute("user") User user,@RequestParam("wardno") String ward,@RequestParam("district") String district,@RequestParam("provience") String proviece,HttpServletRequest request,Model model) throws ServletException{
		
		
		int min=10000000;
		int max=99999900;
		int number =(int)(Math.random()*(max-min+1)+min);
		
		/*
	
		*/
		
	Userdetail userdetail=new Userdetail();
	userdetail.setFirstname(user.getFirstName());
	userdetail.setLastname(user.getLastName());
	userdetail.setOtp(number);
	userdetail.setEmail(user.getEmail());
	userdetail.setCheckk(0);
	userdetail.setProvience(proviece);
	userdetail.setDistrict(district);
	userdetail.setWardno(ward);
//userdetail.setUser(userRepository.findUserByEmail(user.getEmail()).get());
		userdetailrepo.save(userdetail);
		
		
		
		//request.login(user.getEmail(),password);
	 int userid=userdetailrepo.findByEmail(user.getEmail()).get().getUserdetailid();
	  model.addAttribute("userid", userid);
	  model.addAttribute("password", user.getPassword());
	  model.addAttribute("otpmatched", "yes");
	  model.addAttribute("attemps", 1);
		
	  //String  email=user.getEmail();
	String   email="jkeshab570@gmail.com";
	String subject="FROM E-FACNY";
	String text= String.valueOf(number);
	System.out.println("\n\n\n otp"+text);
		String a=emailservice.sendEmail(email, subject, text);
	  
	  return "userotpverify";
	
	
	
	
		
	
	}
	
	
	@GetMapping("/shopRegister2")
	public String shopRegister() {
		return "shopRegister2";
		
	}
	
	
	
	@GetMapping("/changepassword1")
	public String Changepassword(Model model) {
		
		
		
		model.addAttribute("error_oldpass","NO");
		model.addAttribute("error_newpass","NO");
		return "passwordchange";
		
	}
	
	@PostMapping("/changepassword2")
	public String Changepassword2(@RequestParam("password1") String password1,@RequestParam("password2") String password2,@RequestParam("password3") String password3,Model model,HttpServletRequest request ) {
		
	
		  if (password2.equals(password3)) {
			
			  int userid=0;;
				try {  userid=(int) request.getSession().getAttribute("u_id");
				 
				 }catch(Exception e) {
					 e.printStackTrace();
					 return "redirect:/login";}
				 
			  List<Integer> li=new ArrayList<Integer>();
			  li.add(userid);
			  List<User> users=userRepository.findAllById(li);
			  for(User u:users) {
				  if(bCryptPasswordEncoder.matches(password1,u.getPassword())) {
				  u.setPassword(bCryptPasswordEncoder.encode(password2));
				  u.setEnable("YES");
				  userRepository.save(u);
			  }else {
				  model.addAttribute("error_oldpass","YES");  
				  model.addAttribute("error_newpass","NO");
				  return "passwordchange";
			  }
				  
			  }	  
			  
			//  User user=new User();
			  //Optional<User> userr=userRepository.findById(userid);
			  //user.setEmail(userr.get().getEmail());
			//  user.setEnable("YES");
			  //user.setFirstName(userr.get().getFirstName());
			  //user.setLastName(userr.get().getLastName());
			  //user.setId(userr.get().getId());
			  //user.setPassword(bCryptPasswordEncoder.encode(password1));
		//userRepository.save(user);
				
			 
		  }else {
		  
			  
			  model.addAttribute("error_newpass","YES");
			  model.addAttribute("error_oldpass","NO");
			  return "passwordchange";
			}
		
		return "login";
		
	}
	
	@GetMapping("/adminnnnn")
	public void shopRegiste() {
		
	User user=new User();
	user.setFirstName("keshab");
	user.setEnable("yes");
	user.setEmail("keshabjoshi998@gmail.com");
	user.setLastName("joshi");
	user.setPassword(bCryptPasswordEncoder.encode("123456789"));
	List<Role> role=new ArrayList<Role>();
	role.add(roleRepository.findById(1).get());
	user.setRoles(role);
	userRepository.save(user);
		
		
	}
	
	
	@PostMapping("/otpverify")
	public String verify(@RequestParam("attemps") int attemps,@RequestParam("userid") int userid,@RequestParam("password") String password,@RequestParam("otp")  int otp,Model model) {
		attemps=attemps+1;
		Userdetail userr=userdetailrepo.findById(userid).get();
		if(userr.getOtp()==otp) {
			User user=new User();
			user.setEmail(userr.getEmail());
			user.setPassword(bCryptPasswordEncoder.encode(password));
			List<Role> roles=new ArrayList<>();
			roles.add(roleRepository.findById(2).get());
			user.setRoles(roles);
			user.setEnable("YES");
			
			userRepository.save(user);
			user=userRepository.findUserByEmail(userr.getEmail()).get();
			userr.setUser(user);
			userr.setCheckk(1);
			userdetailrepo.save(userr);
			
			System.out.println("before login transmit");
			return "redirect:/login";
		}else {
			
			if(attemps==4) {
				userdetailrepo.deleteById(userid);
				return "redirect:/register";
				
			}
			  model.addAttribute("userid", userid);
			  model.addAttribute("password", password);
			  model.addAttribute("otpmatched", "yes");
		model.addAttribute("attemps", attemps);
		model.addAttribute("otpmatched", "no");
		return "userotpverify";
		}
		
		
		
	}
	
	
	}	
	
	
