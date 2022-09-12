package com.example.demo.services;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.example.demo.dao.productRepository;
import com.example.demo.enittiy.Category;
import com.example.demo.enittiy.product;

@Service
public class ProductService {
 
	@Autowired
	productRepository productrepo;
	
	
	
	
	
	
	
	
	
	
	public List<product> getAllProduct()
	{
		return productrepo.findAll();//admin controller
	}
	public void addProduct(product products)
		{
		
		productrepo.save(products);
		
		}
	public void deleteProductById(int id) {
		productrepo.deleteById(id);
	}
	public List<product> getAllProductByCategoryId(int id) {
		return productrepo.findAllByCategory_Id(id);
	}
	public Optional<product> getProductById(int id) {
		return productrepo.findById(id);
	}
	public List<product> searchByProductName(String key) {
 if(key!=null) {
	 try {
		return productrepo.findAllProduct(key);
	} catch (Exception e) {
		e.printStackTrace();
	}
 }
		return productrepo.findAll();
	}
	
	
	

	
	
	

	
//	for sorting
//	public Page<product> listAll(int pageNum, String sortField, String sortDir) {
//	    int pageSize = 5;
//	    Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
//	            sortDir.equals("asc") ? Sort.by(sortField).ascending()
//	                                              : Sort.by(sortField).descending()
//	    );
//	     
//	    return productrepo.findAll(pageable);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
