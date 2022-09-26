package com.example.demo.enittiy;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ValueGenerationType;

@Entity
public class OwnerConfirmOrder {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;

private String productname;
private long customerphonenumbr;

public long getCustomerphonenumbr() {
	return customerphonenumbr;
}

public void setCustomerphonenumbr(long customerphonenumbr) {
	this.customerphonenumbr = customerphonenumbr;
}

@Column(name = "product_id", length = Integer.MAX_VALUE, nullable = true)
private int product_id=0;

private int shopid;
private String shippingadress;
private String orderadress;
private double price;
private String status;


@Lob
@Column(name = "productimage", length = Integer.MAX_VALUE, nullable = true)
private byte[] productImage;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "order_date", nullable = true)
private Date orderDate;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getProductname() {
	return productname;
}

public void setProductname(String productname) {
	this.productname = productname;
}

public int getProduct_id() {
	return product_id;
}

public void setProduct_id(int product_id) {
	this.product_id = product_id;
}

public int getShopid() {
	return shopid;
}

public void setShopid(int shopid) {
	this.shopid = shopid;
}

public String getShippingadress() {
	return shippingadress;
}

public void setShippingadress(String shippingadress) {
	this.shippingadress = shippingadress;
}

public String getOrderadress() {
	return orderadress;
}

public void setOrderadress(String orderadress) {
	this.orderadress = orderadress;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public byte[] getProductImage() {
	return productImage;
}

public void setProductImage(byte[] productImage) {
	this.productImage = productImage;
}

public Date getOrderDate() {
	return orderDate;
}

public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
}

@Override
public String toString() {
	return "OwnerConfirmOrder [id=" + id + ", productname=" + productname + ", customerphonenumbr=" + customerphonenumbr
			+ ", product_id=" + product_id + ", shopid=" + shopid + ", shippingadress=" + shippingadress
			+ ", orderadress=" + orderadress + ", price=" + price + ", status=" + status + ", productImage="
			+ Arrays.toString(productImage) + ", orderDate=" + orderDate + "]";
}























	
	
}
