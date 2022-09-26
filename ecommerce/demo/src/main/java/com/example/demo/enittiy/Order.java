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
import javax.persistence.OneToOne;
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



String productname;
private int pid;
private int productid;
private String provience;
private String district;
private String wardno;

@Column(nullable = true)
private String shippingprovience;
@Column(nullable = true)
private String shippingdistrict;
@Column(nullable = true)
private String shippingwardno;


private String currentadress;

private String email;
private long phonenumber;
private String status;
private double totalamountperitem;
private String departstatus;
private String deliverstatus;

@Column( nullable = true)
private String cancellstatus;

@Column( nullable = true)
private boolean cancellcheck;



private int quantity;

@Column(nullable = true)
private String adminsendorder;

@Column(nullable = true)
private long phoneshipping;


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "ordereddate", nullable = true)
private Date orderedDate;


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "departdate", nullable = true)
private Date departdate;



@Temporal(TemporalType.TIMESTAMP)
@Column(name = "delivereddate", nullable = true)
private Date delivereddate;

@Temporal(TemporalType.TIMESTAMP)
@Column( nullable = true)
private Date cancelldate;



@ManyToOne
private Productbuy productbuy;


















/**
 * @return the cancellcheck
 */
public boolean isCancellcheck() {
	return cancellcheck;
}

public boolean getCancellcheck() {
	return cancellcheck;
}

/**
 * @param cancellcheck the cancellcheck to set
 */
public void setCancellcheck(boolean cancellcheck) {
	this.cancellcheck = cancellcheck;
}

public Date getCancelldate() {
	return cancelldate;
}

public void setCancelldate(Date cancelldate) {
	this.cancelldate = cancelldate;
}

public Productbuy getProductbuy() {
	return productbuy;
}


/**
 * @return the cancellstatus
 */
public String getCancellstatus() {
	return cancellstatus;
}

/**
 * @param cancellstatus the cancellstatus to set
 */
public void setCancellstatus(String cancellstatus) {
	this.cancellstatus = cancellstatus;
}

public String getAdminsendorder() {
	return adminsendorder;
}
/**
 * @param adminsendorder the adminsendorder to set
 */
public void setAdminsendorder(String adminsendorder) {
	this.adminsendorder = adminsendorder;
}
public void setProductbuy(Productbuy productbuy) {
	this.productbuy = productbuy;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public long getPhoneshipping() {
	return phoneshipping;
}
public void setPhoneshipping(long phoneshipping) {
	this.phoneshipping = phoneshipping;
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




public Date getOrderedDate() {
	return orderedDate;
}
public void setOrderedDate(Date orderedDate) {
	this.orderedDate = orderedDate;
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











	
}


	
