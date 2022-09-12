package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ShopRegisterRepository;
import com.example.demo.enittiy.ShopRegister;






@Service
public class ShopRegisterService {

	@Autowired
	private ShopRegisterRepository imageGalleryRepository;
	
	public void saveImage(ShopRegister imageGallery) {
		imageGalleryRepository.save(imageGallery);	
	}

	public List<ShopRegister> getAllActiveImages() {
		return imageGalleryRepository.findAll();
	}

	public Optional<ShopRegister> getImageById(Long id) {
		return imageGalleryRepository.findById(id);
	}
}
