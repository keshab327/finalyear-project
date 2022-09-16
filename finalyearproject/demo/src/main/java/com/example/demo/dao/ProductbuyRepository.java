package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enittiy.Productbuy;

public interface ProductbuyRepository extends JpaRepository<Productbuy, Integer> {

	Optional<Productbuy> findByProducttt_productidAndCart_cartid(int a,int b);
	
	List<Productbuy> findByCart_cartid(int id);
	
	
}
