package com.example.demo.enittiy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class Productbuy {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productbuyid;
	

    double pdc;
    double psc;
    double totaltax;
    double total;
	
	@ManyToOne
	private product producttt;
	
	
	@ManyToOne
	private Cart cart;


	public int getProductbuyid() {
		return productbuyid;
	}


	public void setProductbuyid(int productbuyid) {
		this.productbuyid = productbuyid;
	}




	public double getPdc() {
		return pdc;
	}


	public void setPdc(double pdc) {
		this.pdc = pdc;
	}


	public double getPsc() {
		return psc;
	}


	public void setPsc(double psc) {
		this.psc = psc;
	}


	public double getTotaltax() {
		return totaltax;
	}


	public void setTotaltax(double totaltax) {
		this.totaltax = totaltax;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public product getProducttt() {
		return producttt;
	}


	public void setProducttt(product producttt) {
		this.producttt = producttt;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	
	
	
	
	
	
	
}
