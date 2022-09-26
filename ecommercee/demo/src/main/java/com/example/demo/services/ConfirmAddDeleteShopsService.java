package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.enittiy.Role;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.enittiy.User;



@Service
public class ConfirmAddDeleteShopsService {

	@Autowired
	ShopRegisterRepository shopregisterrepo;
	
	@Autowired
	UserRepository userrepo;
	
	
	@Autowired
	Emailservice emailservice;
	
@Autowired
	RoleRepository rolerepository;

@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	


	
	public String confirmandadd(Long id) {
		
		Optional<ShopRegister> shopregister=shopregisterrepo.findById(id);;
		
		
	
		
		
		String name=shopregister.get().getOwner_name();
		
		String email=shopregister.get().getEmail();
		String role="ROLE_OWNER";
		
		
		//password generation
		int min=10000000;
		int max=99999900;
		int number =(int)(Math.random()*(max-min+1)+min);
		
		
		String password=Integer.toString(number);
		
	
	shopregister.get().setVerification("CONFIRMED");
		
		
		//Adding user
		
		User user=new User();
		user.setFirstName(name);
		List<Role> roles=new ArrayList<>();
		roles.add(rolerepository.findById(0).get());
		user.setRoles(roles);
		user.setEmail(email);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setEnable("NO");
		
		ShopRegister shop=shopregister.get();
		user.setShopregister(shop);
		userrepo.save(user);
		System.out.println("owner_passwordddddddddddddd:"+password);
		
		
		
		//user  added
		
		
		//msg to owner phone with password and user name i.e email
		String inside_text="\n"+name+"\nThank for registration\n your user name is:"+email+"\n password is:"+password;
		String subject="From_efancy";
		String emaill="jkeshab570@gmail.com";
	String a=emailservice.sendEmail(emaill, subject, inside_text);
		
		
		
		//email send
	
	
		
		
		
		return "success";
		
		
	}
	
	
	public String rejectShop(Long id) {
		
	Optional<ShopRegister> shopregister;
		
		shopregister=shopregisterrepo.findById(id);
		
		
		
		String name=shopregister.get().getOwner_name();
		
		
		
		shopregisterrepo.deleteById(id);
		
		
		
		
		
		//msg to owner phone notifiyinng their request has been declined
				
		String inside_text="\nFROM e-fancy\n\n"+name+"\nyour request has been declined due to improper information provided\n\n  please try again";
	   String subject="From_efancy";
		String emaill="jkeshab570@gmail.com";
	String a=emailservice.sendEmail(emaill, subject, inside_text);
				
				
			
			
		

		return "aaa";
		
		
		
	}
	
	public String deleteShop(Long id) {
		
		
		Optional<ShopRegister> shopregister;
		shopregister=shopregisterrepo.findById(id);
		System.out.println(id);
		System.out.println("in delete from both table shop");
		
		
		
		String email=shopregister.get().getEmail();
		
	
		//delete from user table so shop couldn't login

		userrepo.deleteByEmail(email);
		
		//delete form shop table
		shopregisterrepo.deleteById(id);
		
		System.out.println("succesfully removed from system");
		
		return "aaa";
		
		
		
	}
	
	
	
	
	
	
	
}
