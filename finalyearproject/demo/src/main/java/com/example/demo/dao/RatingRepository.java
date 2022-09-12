package com.example.demo.dao;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.enittiy.Cart;
import com.example.demo.enittiy.Rating; 
import com.example.demo.enittiy.product;
public interface RatingRepository extends JpaRepository<Rating,Integer>{

  public List<Rating> findByCustomer_Userdetailid(int id);
  public List<Rating> findByProductt_Productid(int id);
  public Optional<Rating> findByCustomer_UserdetailidAndProductt_Productid(int a,int b);

	
}
