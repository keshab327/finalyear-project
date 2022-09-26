package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.enittiy.ShopRegister;







@Repository
public interface ShopRegisterRepository extends JpaRepository<ShopRegister, Long>{

	long countByVerification(String verification);

	Optional<ShopRegister> findByEmail(String email);
	
	
}

