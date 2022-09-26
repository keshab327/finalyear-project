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
	String provience;
	String district;
	String wardno;
	long phone;
	
	double txAmt;
	double tAmt;
	double psc;
	double pdc;
	double amt;
	int pid;
	int product_id=0;
	
	long shippingphoneno;
	String shippingprovience;
	String shippingdistrict;
	String shippingwardno;
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * @return the shippingprovience
	 */
	public String getShippingprovience() {
		return shippingprovience;
	}
	/**
	 * @param shippingprovience the shippingprovience to set
	 */
	public void setShippingprovience(String shippingprovience) {
		this.shippingprovience = shippingprovience;
	}
	/**
	 * @return the shippingdistrict
	 */
	public String getShippingdistrict() {
		return shippingdistrict;
	}
	/**
	 * @param shippingdistrict the shippingdistrict to set
	 */
	public void setShippingdistrict(String shippingdistrict) {
		this.shippingdistrict = shippingdistrict;
	}
	/**
	 * @return the shippingwardno
	 */
	public String getShippingwardno() {
		return shippingwardno;
	}
	/**
	 * @param shippingwardno the shippingwardno to set
	 */
	public void setShippingwardno(String shippingwardno) {
		this.shippingwardno = shippingwardno;
	}
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
	
	
	
	
	
	
	
/**
	 * @return the provience
	 */
	public String getProvience() {
		return provience;
	}
	/**
	 * @param provience the provience to set
	 */
	public void setProvience(String provience) {
		this.provience = provience;
	}
	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return the wardno
	 */
	public String getWardno() {
		return wardno;
	}
	/**
	 * @param wardno the wardno to set
	 */
	public void setWardno(String wardno) {
		this.wardno = wardno;
	}

	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	
	
	

	

}
