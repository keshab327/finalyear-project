package com.example.demo.enittiy;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Deliveryboy {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String firstname;
    private String lastname;
    private String email;
    private String adress;
    
    
    
    @OneToOne
    private User user;
    
    @OneToMany
    private List<Order> ordersbydeliveryboy;
    
    
    
    
	public List<Order> getOrdersbydeliveryboy() {
		return ordersbydeliveryboy;
	}






	public void setOrdersbydeliveryboy(List<Order> ordersbydeliveryboy) {
		this.ordersbydeliveryboy = ordersbydeliveryboy;
	}






	public User getUser() {
		return user;
	}






	public void setUser(User user) {
		this.user = user;
	}






	public int getId() {
		return id;
	}






	public void setId(int id) {
		this.id = id;
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






	@Override
	public String toString() {
		return "Deliveryboy [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", adress=" + adress + "]";
	}
}
