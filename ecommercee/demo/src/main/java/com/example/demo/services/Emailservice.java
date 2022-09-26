package com.example.demo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class Emailservice {

	 @Autowired
		JavaMailSender javaMailSender;
		
		
		public String sendEmail(String sendtoemail,String subject,String insidetext){
		//	sendtoemail="bhattarohit7@gmail.com";
			SimpleMailMessage sm = new SimpleMailMessage();
			sm.setFrom("1234kehab@gmail.com");//input the sender email Id or read it from properties file
			sm.setTo(sendtoemail);
			sm.setSubject(subject);
			sm.setText(insidetext);
			javaMailSender.send(sm);
			
			return "success";
		}
	
	
}





        
        
        
        













