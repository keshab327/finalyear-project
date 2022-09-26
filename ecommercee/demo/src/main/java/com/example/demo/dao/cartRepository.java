package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.enittiy.Cart;
import com.example.demo.enittiy.ShopRegister;
import com.example.demo.enittiy.Userdetail;
import com.example.demo.enittiy.product;

@Repository
public interface cartRepository extends JpaRepository<Cart,Integer>{

	//List<Cart> findAllByUserid(int i);
	Optional<Cart> findByUserdetail_Userdetailid(int id);



	
/*	@Modifying
	@Transactional
	@Query("select sum(product_price) from Cart  Where userid = :userid")
	*/
	//int findSumByUserid(int userid);
	
	

	

}
