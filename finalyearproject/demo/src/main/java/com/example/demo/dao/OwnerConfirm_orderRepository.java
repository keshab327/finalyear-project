package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.enittiy.OwnerConfirmOrder;



public interface OwnerConfirm_orderRepository extends JpaRepository<OwnerConfirmOrder,Integer> {

	Optional<OwnerConfirmOrder> getProductimageByIdAndShopid(int id, int i);

	List<OwnerConfirmOrder> findAllByShopid(int shopid);

	//Optional<OwerConfirm_order> findByProduct_id(int i);

}
