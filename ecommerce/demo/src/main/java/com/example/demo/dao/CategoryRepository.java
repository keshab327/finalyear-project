package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enittiy.Category;
import com.example.demo.enittiy.ShopRegister;

public interface CategoryRepository 
extends JpaRepository<Category,Integer>{
	Optional<Category> findById(int id);
	List<Category> findAllByName(String s);

}
