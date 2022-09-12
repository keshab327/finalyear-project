   package com.example.demo.configuration;


import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.example.demo.dao.DeliveryboyRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserdetailRepository;
import com.example.demo.enittiy.CustomUserDetail;
import com.example.demo.enittiy.Deliveryboy;
import com.example.demo.enittiy.User;
import com.example.demo.enittiy.Userdetail;
 
@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
	@Autowired
    UserRepository userrepo;
	@Autowired
    UserdetailRepository userdetailrepo;
	@Autowired
	DeliveryboyRepository deliveryboyrepo;
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
    	CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
    	userDetails = (CustomUserDetail) authentication.getPrincipal();
		String email= userDetails.getUsername();
		
		Model model = null;
         
        String redirectURL = request.getContextPath();
        
        Optional<User> user=userrepo.findUserByEmail(email);
      
        int id=user.get().getId();
       
        
        if (userDetails.hasRole("ROLE_ADMIN")) {
          
        	request.getSession().setAttribute("userid",id);
        	redirectURL = "/admin";
        } else if (userDetails.hasRole("ROLE_USER")) {
        	
        	  Optional<Userdetail> userdetail=userdetailrepo.findByUser_Userid(user.get().getId());
        	  int userdetail_id=userdetail.get().getUserdetailid();
        	
        request.getSession().setAttribute("user_id",userdetail_id);
        	
        request.getSession().setAttribute("userid",userdetail_id);
        	System.out.println("session in login for buyers"+id);
        	
        	
            redirectURL = "/buyer";
        } else if (userDetails.hasRole("ROLE_OWNER")) {
        	
        	request.getSession().setAttribute("email",email);
        	request.getSession().setAttribute("userid",user.get().getShopregister().getId());
        	
        	System.out.println("session in login+"+email);
        	String a="NO";
        	if(a.equals(user.get().getEnable())) {
       		 System.out.println("\nstringcomparing fine ");
        		redirectURL = "/changepassword1";
        	}
        		else {
        			redirectURL = "/shopwoner_home";//ShopOwnerSeeOrder_Controller
        		}
  
        	
        	
        	
            
        }
     else if (userDetails.hasRole("ROLE_DELIVERYBOY")) {
    
    	Deliveryboy d=deliveryboyrepo.findByEmail(email).get();
    	request.getSession().setAttribute("delivery_boy_id",d.getId());
    	request.getSession().setAttribute("userid",d.getId());
    	System.out.println("session in login"+email);
    	
    	if(user.get().getEnable().contains("NO")) {
   		 redirectURL = "/changepassword1";
   	}else {
   		redirectURL = "/Deliveryboy/search";//ShopOwnerSeeOrder_Controller
   	}
    	
    	
        
    }
        
        request.getSession().setAttribute("u_id",id);
         
        response.sendRedirect(redirectURL);
         
    }
 
}
