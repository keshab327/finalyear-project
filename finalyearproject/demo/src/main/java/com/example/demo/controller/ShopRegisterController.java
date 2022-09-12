package com.example.demo.controller;


import java.io.IOException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.PreviousCountRepository;
import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.enittiy.CountRecord;
import com.example.demo.enittiy.ShopRegister;

import com.example.demo.services.ShopRegisterService;





@Controller
public class ShopRegisterController {
	

	
	
	@Autowired
	private ShopRegisterService imageGalleryService;
	
	@Autowired
	private  ShopRegisterRepository shopregisteryrepository;
	
	@Autowired
	PreviousCountRepository previousCountrepository;



	/*@GetMapping(value = {"/shopRegister2"})
	public String addProductPage() {
		return "shopRegister2";
	}*/

	@PostMapping("/image/saveImageDetails")
	public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("phone_number") long phone,@RequestParam("email") String email,@RequestParam("name") String name,@RequestParam("owner_name") String owner_name,@RequestParam("pan_number") int pan_number,
			@RequestParam("adress") String adress, @RequestParam("description") String description, Model model, HttpServletRequest request
			,final @RequestParam("image") MultipartFile file,final @RequestParam("image_tax_report") MultipartFile file_tax) {
		try {
		
			
			
			//getting count of current  records
		/*	CountRecord  countrecord= new CountRecord();
			
				long count=	shopregisteryrepository.count();
			long id=1;
				//saving into count databasse 
				countrecord.setPrevious_count(count);
				countrecord.setId(id);
		previousCountrepository.save(countrecord);
        System.out.println("count"+count);

		//couting and saving end  */
			
			
			
			
			
		
			String confirm="NOT_CONFIRMED";
			
	
			String[] names = name.split(",");
			String[] descriptions = description.split(",");
			String[] adresss = adress.split(",");
			String[] owner_names = adress.split(",");
			String[] emails = email.split(",");
			Date createDate = new Date();
			
		
			
				
			
		
	
			byte[] imageData = file.getBytes();
			byte[] image_tax = file_tax.getBytes();
			
			
			ShopRegister imageGallery = new ShopRegister();
			imageGallery.setName(names[0]);
			imageGallery.setImage(imageData);
			imageGallery.setPan_number(pan_number);
			imageGallery.setOwner_name(owner_names[0]);
			imageGallery.setEmail(emails[0]);
			imageGallery.setPhone_number(phone);
			
		imageGallery.setVerification(confirm);
			
			imageGallery.setImage_tax(image_tax );
			
			
			imageGallery.setAdress(adresss[0]);
			imageGallery.setDescription(descriptions[0]);
			imageGallery.setCreateDate(createDate);
			
			imageGalleryService.saveImage(imageGallery);
			
		
			
			return new ResponseEntity<>("Successfully submitted request - ",HttpStatus.OK);
			
			// return "redirect:/shopregister2";
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<ShopRegister> imageGallery)
			throws ServletException, IOException {
		//log.info("Id :: " + id);
		imageGallery = imageGalleryService.getImageById(id);
		
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(imageGallery.get().getImage());
		
		response.getOutputStream().close();
	}
	
	
	
	
	
	

	
	@GetMapping("image/display_tax/{id}")
	@ResponseBody
	void showImage_tax(@PathVariable("id") Long id, HttpServletResponse responsee, Optional<ShopRegister> imageGalleryy)
			throws ServletException, IOException {
		//log.info("Id :: " + id);
		
		//System.out.println("in  image controller starter");
		imageGalleryy = imageGalleryService.getImageById(id);
		
		responsee.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		responsee.getOutputStream().write(imageGalleryy.get().getImage_tax());
		
		responsee.getOutputStream().close();
	}
	
	
	
	
	

	@GetMapping("image/imageDetails/{id}")
	String showProductDetails(@PathVariable("id") Long id, Optional<ShopRegister> imageGallery, Model model) {
		try {
			
			if (id != 0) {
				imageGallery = imageGalleryService.getImageById(id);
			String confirm;
			
			
				if (imageGallery.isPresent()) {
					model.addAttribute("id", imageGallery.get().getId());
					model.addAttribute("description", imageGallery.get().getDescription());
					model.addAttribute("name", imageGallery.get().getName());
					model.addAttribute("owner_name", imageGallery.get().getOwner_name());
					model.addAttribute("adress", imageGallery.get().getAdress());
					model.addAttribute("email", imageGallery.get().getEmail());
					model.addAttribute("pan_number", imageGallery.get().getPan_number());
					model.addAttribute("phone_number", imageGallery.get().getPhone_number());
					confirm=imageGallery.get().getVerification();
					System.out.println("aaaaaaaaaaaaaaaaaaaaaaaverification"+confirm);
					
					if(confirm.equalsIgnoreCase("CONFIRMED"))
					{
						return "confirm1";
					}
					
					
					
					return "confirm";
				}
				return "redirect:/shopRegister2";
			}
		return "redirect:/shopRegister2";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/shopRegister2";
		}	
	}


}	

