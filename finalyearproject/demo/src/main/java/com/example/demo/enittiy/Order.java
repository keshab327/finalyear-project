package com.example.demo.enittiy;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




@Entity
@Table(name="orderr")
public class Order {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;

private long shopid;

int customerid;

int cartindex;

String productname;
private int pid;
private int productid;
private String adress;
private String shippingadress;


private String currentadress;

private String email;
private long phonenumber;
private String status;
private double totalamountperitem;
private String departstatus;
private String deliverstatus;

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "ordereddate", nullable = true)
private Date orderedDate;


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "departdate", nullable = true)
private Date departdate;



@Temporal(TemporalType.TIMESTAMP)
@Column(name = "delivereddate", nullable = true)
private Date delivereddate;








@ManyToOne(fetch=FetchType.LAZY,cascade= CascadeType.MERGE)
@JoinColumn(name="pid2")
private Esewareturn esewareturn;


@ManyToOne
private Cart cart;









public Cart getCart() {
	return cart;
}
public void setCart(Cart cart) {
	this.cart = cart;
}
public int getCartindex() {
	return cartindex;
}
public void setCartindex(int cartindex) {
	this.cartindex = cartindex;
}



public String getCurrentadress() {
	return currentadress;
}
public void setCurrentadress(String currentadress) {
	this.currentadress = currentadress;
}
public Date getDelivereddate() {
	return delivereddate;
}
public void setDelivereddate(Date delivereddate) {
	this.delivereddate = delivereddate;
}
public Date getDepartdate() {
	return departdate;
}
public void setDepartdate(Date departdate) {
	this.departdate = departdate;
}
public String getDeliverstatus() {
	return deliverstatus;
}
public void setDeliverstatus(String deliverstatus) {
	this.deliverstatus = deliverstatus;
}
public String getDepartstatus() {
	return departstatus;
}
public void setDepartstatus(String departstatus) {
	this.departstatus = departstatus;
}






public long getShopid() {
	return shopid;
}
public void setShopid(long shopid) {
	this.shopid = shopid;
}

public String getProductname() {
	return productname;
}
public void setProductname(String productname) {
	this.productname = productname;
}



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCustomerid() {
	return customerid;
}
public void setCustomerid(int customerid) {
	this.customerid = customerid;
}
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public int getProductid() {
	return productid;
}
public void setProductid(int productid) {
	this.productid = productid;
}
public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}
public String getShippingadress() {
	return shippingadress;
}
public void setShippingadress(String shippingadress) {
	this.shippingadress = shippingadress;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public long getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(long phonenumber) {
	this.phonenumber = phonenumber;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public double getTotalamountperitem() {
	return totalamountperitem;
}
public void setTotalamountperitem(double totalamountperitem) {
	this.totalamountperitem = totalamountperitem;
}
public Esewareturn getEsewareturn() {
	return esewareturn;
}
public void setEsewareturn(Esewareturn esewareturn) {
	this.esewareturn = esewareturn;
}




public Date getOrderedDate() {
	return orderedDate;
}
public void setOrderedDate(Date orderedDate) {
	this.orderedDate = orderedDate;
}







@Override
public String toString() {
	return "Order [id=" + id + ", shopid=" + shopid + ", customerid=" + customerid + ", cartid=" 
			+ ", productname=" + productname + ", pid=" + pid + ", productid=" + productid + ", adress=" + adress
			+ ", shippingadress=" + shippingadress + ", email=" + email
			+ ", phonenumber=" + phonenumber + ", status=" + status + ", totalamountperitem=" + totalamountperitem
			+ ", departstatus=" + departstatus + ", deliverstatus=" + deliverstatus + ", orderedDate=" + orderedDate
			+ ", departdate=" + departdate + ", delivereddate=" + delivereddate + ", esewareturn=" + esewareturn + "]";
}






	
}


	
