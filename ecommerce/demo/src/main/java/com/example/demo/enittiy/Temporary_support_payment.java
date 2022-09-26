package com.example.demo.enittiy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="temporary_payment_suppport")
public class Temporary_support_payment {
	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String productname;
	private int product_id;
	private double total_peritem_amount;
	public double getTotal_peritem_amount() {
		return total_peritem_amount;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public void setTotal_peritem_amount(double total_peritem_amount) {
		this.total_peritem_amount = total_peritem_amount;
	}

	@Override
	public String toString() {
		return "Temporary_support_payment [id=" + id + ", productname=" + productname + ", product_id=" + product_id
				+ ", total_peritem_amount=" + total_peritem_amount + "]";
	}
	
	
}
