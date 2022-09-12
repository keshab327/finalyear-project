package com.example.demo.enittiy;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="userdetail")
public class Userdetail {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userdetailid;
	private String firstname;
	private String lastname;
	
	@Column(nullable = false,unique=true)
	@NotEmpty
	@Email(message="{errors.invalid_email}")
	private String email;
	
	@OneToOne
	private User user;
	
	@OneToMany
	private List<Order> order;
	 
	
	
	
	
	 
	

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public int getUserdetailid() {
		return userdetailid;
	}

	public void setUserdetailid(int userdetailid) {
		this.userdetailid = userdetailid;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
