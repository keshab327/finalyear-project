package com.example.demo.enittiy;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	private double product_service_charge;
	private double product_delivery_charge;
	private double product_tax;
	
	public double getProduct_service_charge() {
		return product_service_charge;
	}
	public void setProduct_service_charge(double product_service_charge) {
		this.product_service_charge = product_service_charge;
	}
	public double getProduct_delivery_charge() {
		return product_delivery_charge;
	}
	public void setProduct_delivery_charge(double product_delivery_charge) {
		this.product_delivery_charge = product_delivery_charge;
	}
	public double getProduct_tax() {
		return product_tax;
	}
	public void setProduct_tax(double product_tax) {
		this.product_tax = product_tax;
	}

	@OneToMany(fetch=FetchType.LAZY,mappedBy="category")
	private List<product> product;
	
	public Category() {
		super();
	}
	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public List<product> getProduct() {
		return product;
	}
	public void setProduct(List<product> product) {
		this.product = product;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category(int id, String name, double product_service_charge, double product_delivery_charge,
			double product_tax, List<com.example.demo.enittiy.product> product) {
		super();
		this.id = id;
		this.name = name;
		this.product_service_charge = product_service_charge;
		this.product_delivery_charge = product_delivery_charge;
		this.product_tax = product_tax;
		this.product = product;
	}
	

	

}