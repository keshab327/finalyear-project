        package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.dto.paymentdetail;
import com.example.demo.services.CustomUserDetailService;

//import com.example.demo.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//@Autowired
	//GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
	@Autowired
	CustomUserDetailService customUserDetailService;
	@Autowired
	CustomLoginSuccessHandler customsuccesshandler;
	
	
	protected void configure(HttpSecurity http) throws Exception{
	http
	
	   .authorizeRequests()
	 
	  .antMatchers("/","/shop/**","/register","/forgotpassword","/NewPassword","/search/**","/esewafail/**","/esewasuccess/**","/shop/**","/display_productimage/**","/shopRegister2/**","/image/display_tax/**","/image/display/**","/image/saveImageDetails/**","/product/update/**","/changepassword1/**","/changepassword2/**","/showallproduct/paging/**","/showallproduct/pagingg/**","/adminnnnn/**","/cartindex/**","/buyer/**").permitAll()
	  .antMatchers("/admin/**","/image/show/**","/delete_shop_request/**","/image/imageDetails/**","/admin/shopprodducts/**").hasRole("ADMIN")
	  .antMatchers("/Deliveryboy/search/**","/deliveryboy/updatestatus/**","/deliveryboy/updatestatus2/**","/deliveryboy/location/**").hasRole("DELIVERYBOY")
	  .antMatchers("/customer/allpayment/**","/customer_order/**","/checkout/**","/esewa/**","/cart/buysingleItem/**","/customermyorder/myorders","/createcart/**").hasRole("USER")
	   //.antMatchers("/admin/**","admin/image/show/**").hasRole("ADMIN")//no need for ROLE_ADMIN this is equal to ADMIN
		 // .antMatchers("/shopowner/products_by_shopid/**","admin/owner/depart/**").hasRole("OWNER")
	   .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .failureUrl("/login?error=true")
        
        
        .permitAll()
        .failureUrl("/login?error=true")
         .successHandler(customsuccesshandler )//customsuccesshandler
  .usernameParameter("email")
        .passwordParameter("password")
        .and()
      //  .oauth2Login()
        //.loginPage("/login")
        //.successHandler(googleOAuth2SuccessHandler)
        //.and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .and()
        .exceptionHandling()
        .and()
        .csrf()
        .disable();
	http.headers().frameOptions().disable();
	
}
@Bean
public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
}
protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	auth.userDetailsService(customUserDetailService);
}
public void configure(WebSecurity web) throws Exception{
	web.ignoring().antMatchers("/resources/**","/static/**","/images/**","/productImages/**","/css/**","/js/**");
}
}
