package com.example.demo.enittiy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Provience {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int provienceid;
	
	private String proviencename;

	/**
	 * @return the provienceid
	 */
	public int getProvienceid() {
		return provienceid;
	}

	/**
	 * @param provienceid the provienceid to set
	 */
	public void setProvienceid(int provienceid) {
		this.provienceid = provienceid;
	}

	/**
	 * @return the proviencename
	 */
	public String getProviencename() {
		return proviencename;
	}

	/**
	 * @param proviencename the proviencename to set
	 */
	public void setProviencename(String proviencename) {
		this.proviencename = proviencename;
	}
	
	
	
	
}
