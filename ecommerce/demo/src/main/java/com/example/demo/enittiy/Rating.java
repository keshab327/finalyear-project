package com.example.demo.enittiy;





import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int rate;
	private String description;
	

	
	
	@ManyToOne
	private product productt;
	 
	@ManyToOne
	private Userdetail customer;
	
	

	
	
	
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	



	public product getProduct() {
		return productt;
	}

	public void setProduct(product product) {
		this.productt = product;
	}

	public Userdetail getCustomer() {
		return customer;
	}

	public void setCustomer(Userdetail customer) {
		this.customer = customer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	 public Rating() 
	 { super(); // TODO Auto-generated constructor stub }
	 }
	

	 



	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	 

}

