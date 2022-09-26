package com.example.demo.enittiy;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cartid;
	

	
	@OneToOne
	private Userdetail userdetail;
	





	
	
	

	public int getCartid() {
		return cartid;
	}



	public void setCartid(int cartid) {
		this.cartid = cartid;
	}



	public Userdetail getUserdetail() {
		return userdetail;
	}



	public void setUserdetail(Userdetail userdetail) {
		this.userdetail = userdetail;
	}



	public int getId() {
		return cartid;
	}



	public void setId(int id) {
		this.cartid = id;
	}




	
	

}