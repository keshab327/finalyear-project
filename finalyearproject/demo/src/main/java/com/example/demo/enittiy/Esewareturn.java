package com.example.demo.enittiy;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="esewaback")
public class Esewareturn {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int pid;
	private double totoal_amount;
	private long refid_esewa;
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="esewareturn")
	private List<Order> order;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public double getTotoal_amount() {
		return totoal_amount;
	}


	public void setTotoal_amount(double totoal_amount) {
		this.totoal_amount = totoal_amount;
	}


	public long getRefid_esewa() {
		return refid_esewa;
	}


	public void setRefid_esewa(long refid_esewa) {
		this.refid_esewa = refid_esewa;
	}


	public List<Order> getOrder() {
		return order;
	}


	public void setOrder(List<Order> order) {
		this.order = order;
	}


	@Override
	public String toString() {
		return "Esewareturn [id=" + id + ", pid=" + pid + ", totoal_amount=" + totoal_amount + ", refid_esewa="
				+ refid_esewa + ", order=" + order + "]";
	}

}
