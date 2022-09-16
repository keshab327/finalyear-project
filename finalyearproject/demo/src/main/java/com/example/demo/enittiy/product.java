package com.example.demo.enittiy;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class product {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int productid;
private String name;
private double price;

private int quantity;

private String available;


@Lob
@Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
private byte[] product_image;




@ManyToOne(fetch=FetchType.LAZY,cascade= CascadeType.MERGE)
@JoinColumn(name="cate_id")
private Category category;



@ManyToOne
private ShopRegister shopregisterr;














public int getProductid() {
	return productid;
}

public void setProductid(int productid) {
	this.productid = productid;
}

public ShopRegister getShopregisterr() {
	return shopregisterr;
}

public void setShopregisterr(ShopRegister shopregisterr) {
	this.shopregisterr = shopregisterr;
}



public String getAvailable() {
	return available;
}

public void setAvailable(String available) {
	this.available = available;
}




private String description;

private float rate;




public float getRate() {
	return rate;
}

public void setRate(double d) {
	this.rate = (float) d;
}




public void setRate(float rate) {
	this.rate = rate;
}











public byte[] getProduct_image() {
	return product_image;
}

public void setProduct_image(byte[] product_image) {
	this.product_image = product_image;
}

public int getProduct_id() {
	return productid;
}



public void setProduct_id(int product_id) {
	this.productid = product_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}

public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public void setShopregister(Optional<ShopRegister> shop) {
	// TODO Auto-generated method stub
	
	
}


	
}