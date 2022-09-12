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

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserdetailRepository;
import com.example.demo.enittiy.Role;
import com.example.demo.enittiy.User;
import com.example.demo.enittiy.Userdetail;


@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserdetailRepository userdetailrepo;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String login() {
		return "login";
		
	}
	@GetMapping("/register")
	public String register(){
		
	    return "register";
	    
		
		
		
		
	}
	@PostMapping("/register")
	public String registerHere(@ModelAttribute("user") User user,HttpServletRequest request,Model model) throws ServletException{
		
		
		
		
		String password=user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		List<Role> roles=new ArrayList<>();
		roles.add(roleRepository.findById(2).get());
		user.setRoles(roles);
		user.setEnable("YES");
		userRepository.save(user);
		
		
	Userdetail userdetail=new Userdetail();
	userdetail.setFirstname(user.getFirstName());
	userdetail.setLastname(user.getLastName());
	userdetail.setEmail(user.getEmail());
userdetail.setUser(userRepository.findUserByEmail(user.getEmail()).get());
		userdetailrepo.save(userdetail);
		
		
		
		//request.login(user.getEmail(),password);
	 
	  
		
		return "redirect:/login";
	
	
	
	
		
	
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
	
	
	
	}	
	
	
