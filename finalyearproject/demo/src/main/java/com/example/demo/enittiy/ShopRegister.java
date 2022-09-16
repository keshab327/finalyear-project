package com.example.demo.enittiy;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Shopregister")
public class ShopRegister {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long shopownerid;
	
	@Column(name = "owner_name", nullable = false)
	private String owner_name;
	
	@Column(name = "email",nullable = false,unique=true)
	private String email ;
	
	@Column(name = "phone_number", nullable = true)
	private Long phone_number;
	
	@Temporal(TemporalType.TIMESTAMP)
  
    private Date createDate;
	
	@Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
	
	
	@Lob
    @Column(name = "Image_tax", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image_tax;
    
    public byte[] getImage_tax() {
		return image_tax;
	}
	
	
	
    

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;	
	
	@Column(name = "adress",nullable = false, precision = 10, scale = 2)
    private String adress;
	
	
	private  String esewaservicecode;
	
    
    
    
    
    
	@OneToMany
	private List<Order> order;
	

	
	
	
	
	



	@Column(name = "verification", nullable = false)
	private String verification;
	
	@Column(name = "pan_number", nullable = false)
	private int pan_number;
	
	public void setPan_number(int pan_number) {
		this.pan_number = pan_number;
	}

	
	
	
	public String getEsewaservicecode() {
		return esewaservicecode;
	}




	public void setEsewaservicecode(String esewaservicecode) {
		this.esewaservicecode = esewaservicecode;
	}




	public String getVerification() {
		return verification;
	}


	public void setVerification(String verification) {
		this.verification = verification;
	}


	public int getPan_number() {
		return pan_number;
	}
	

	public String getOwner_name() {
		return owner_name;
	}


	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}


	


	

	
	




	public List<Order> getOrder() {
		return order;
	}



	public void setOrder(List<Order> order) {
		this.order = order;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Long getPhone_number() {
		return phone_number;
	}



	public void setPhone_number(Long phone_number) {
		this.phone_number = phone_number;
	}






	

	




	public void setImage_tax(byte[] image_tax) {
		this.image_tax = image_tax;
	}


	

	


	public Long getId() {
		return shopownerid;
	}


	public void setId(Long id) {
		this.shopownerid = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getAdress() {
		return adress;
	}



	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}







	@Override
	public String toString() {
		return "ShopRegister [id=" +shopownerid + ", owner_name=" + owner_name + ", email=" + email + ", phone_number="
				+ phone_number + ", verification=" + verification + ", pan_number=" + pan_number + ", name=" + name
				+ ", description=" + description + ", adress=" + adress + ", image=" + Arrays.toString(image)
				+ ", image_tax=" + Arrays.toString(image_tax) + ", createDate=" + createDate + "]";
	}



	public void setAdress(String adress) {
		// TODO Auto-generated method stub
		this.adress = adress;
	}


	
   
}

