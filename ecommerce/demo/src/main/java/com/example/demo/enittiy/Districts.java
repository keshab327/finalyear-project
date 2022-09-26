package com.example.demo.enittiy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Districts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int provienceid;
	
	
	private String districtname;
	
	@ManyToOne
	private Provience proviec;
	
	


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
	 * @return the districtname
	 */
	public String getDistrictname() {
		return districtname;
	}


	/**
	 * @param districtname the districtname to set
	 */
	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}
	

}
