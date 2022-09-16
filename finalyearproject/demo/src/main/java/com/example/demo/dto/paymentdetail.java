package com.example.demo.dto;


import java.beans.JavaBean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data 
public class paymentdetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idddd;
	
	String firstname;
	String lastname;
	String email;
	String adress;
	long phone;
	String shipping_adress;
	double txAmt;
	double tAmt;
	double psc;
	double pdc;
	double amt;
	int pid;
	int product_id=0;
	
	long shippingphoneno;
	
	
	
	
	
	
	
	
	/**
	 * @return the product_id
	 */
	public int getProduct_id() {
		return product_id;
	}
	public long getShippingphoneno() {
		return shippingphoneno;
	}
	public void setShippingphoneno(long shippingphoneno) {
		this.shippingphoneno = shippingphoneno;
	}

	public int getProdct_id() {
		return product_id;
	}
	public void setProduct_id(int prodct_id) {
		this.product_id = prodct_id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public double getTxAmt() {
		return txAmt;
	}
	public void setTxAmt(double txAmt) {
		this.txAmt = txAmt;
	}
	public double gettAmt() {
		return tAmt;
	}
	public void settAmt(double tAmt) {
		this.tAmt = tAmt;
	}
	public double getPsc() {
		return psc;
	}
	public void setPsc(double psc) {
		this.psc = psc;
	}
	public double getPdc() {
		return pdc;
	}
	public void setPdc(double pdc) {
		this.pdc = pdc;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getShipping_adress() {
		return shipping_adress;
	}
	public void setShipping_adress(String shipping_adress) {
		this.shipping_adress = shipping_adress;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	
	
	

	

}
