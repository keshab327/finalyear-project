package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enittiy.Districts;

public interface DistrictsReoistory extends JpaRepository<Districts, Integer>{

	List<Districts> findByProviec_Provienceid(int id);
	
	
}
