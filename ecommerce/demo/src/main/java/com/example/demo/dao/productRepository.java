package com.example.demo.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.enittiy.OwnerConfirmOrder;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.enittiy.product;

public interface productRepository extends JpaRepository<product,Integer>{
	List<product> findAllByCategory_Id(int id);
	//List<product> findAllByShopRegister_Id(int id);
	
	
///do not use findAllByShop_id
	List<product> findAllByShopregisterr_Shopownerid(long id);
	public Page<product> findAllByCategory_IdAndAvailable(int id,String a ,Pageable pageable);
	public Page<product> findAllByNameAndAvailable(String name,String a ,Pageable pageable);
	public Page<product> findAllByAvailable(String a,Pageable pageable);
	
	public Page<product> findByShopregisterr_Shopownerid(long id,Pageable pageable);
	

	
	@Query("select p from product p where p.name like %?1%"
			+ "OR p.category like %?1%"
			+ "OR p.price like %?1%"
			+ "OR p.description like %?1%")	
	public List<product> findAllProduct(String key);

	@Query("Select c from product c where c.name like %:key%")
	List<product> findByKey(String key);
	

}
