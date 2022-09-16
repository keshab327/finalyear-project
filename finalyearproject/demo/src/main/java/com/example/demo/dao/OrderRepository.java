package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.enittiy.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {
	
	
	//void deleteAllByStatus(String s); for all delete list must be passed  void deleteAllByStatus(List<Order> f)
	Optional<Order> findByPid(int id);
	List<Order> findByStatus(String s);
	List<Order> findByShopid(long id);
	List<Order> findAllByshopid(long id);
	

	
	
	List<Order> findAllByCustomeridAndDeliverstatus(int a,String b);
	List<Order> findAllByCustomeridAndStatus(int a,String b);
	
	void save(Optional<Order> order);
	
	

}
