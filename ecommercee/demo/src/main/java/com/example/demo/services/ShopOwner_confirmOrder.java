package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OwnerConfirm_orderRepository;
import com.example.demo.enittiy.OwnerConfirmOrder;

@Service
public class ShopOwner_confirmOrder {
	
	@Autowired
	private OwnerConfirm_orderRepository order;

	public List<OwnerConfirmOrder> getAllOrder(int shopid){
		return order.findAllByShopid(shopid) ;
		
	}
	
	
}
