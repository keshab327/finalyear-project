package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enittiy.Role;
import com.example.demo.enittiy.User;


public interface RoleRepository extends JpaRepository<Role,Integer> {

	void save(User user);

	
	
}
