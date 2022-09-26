package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enittiy.Role;
import com.example.demo.enittiy.Userdetail;

public interface UserdetailRepository extends JpaRepository<Userdetail, Integer> {

	Optional<Userdetail> findByUser_Userid(int id);
	//Optional<Userdetail> findByUserdetail_UserdetailidAndOrder_id(int a,int b);

	Optional<Role> findUserByEmail(String email);
	Optional<Userdetail> findByEmail(String email);
	void deleteAllByCheckk(int a);
	
}
