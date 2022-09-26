package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enittiy.Deliveryboy;

public interface DeliveryboyRepository extends JpaRepository<Deliveryboy,Integer> {
	
	
	Optional<Deliveryboy> findByEmail(String a);
	
	

}
