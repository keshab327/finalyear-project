package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enittiy.Productreport;

public interface ProductreportRepository  extends JpaRepository<Productreport,Integer>  {

	List<Productreport> findAll();
	Optional<Productreport> findByOrderr_Id(int id);
	
	
}
