package com.example.demo.enittiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Categoryrequest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int categoryrequestid;
	
	private  String categoryname;
	private String description;
	 private String confirmstatus;
	 
	 @Lob
	 @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
	 private byte[] categoryimage;
	 
	 
	 
	/**
	 * @return the categoryimage
	 */
	public byte[] getCategoryimage() {
		return categoryimage;
	}
	/**
	 * @param categoryimage the categoryimage to set
	 */
	public void setCategoryimage(byte[] categoryimage) {
		this.categoryimage = categoryimage;
	}
	/**
	 * @return the categoryrequestid
	 */
	public int getCategoryrequestid() {
		return categoryrequestid;
	}
	/**
	 * @param categoryrequestid the categoryrequestid to set
	 */
	public void setCategoryrequestid(int categoryrequestid) {
		this.categoryrequestid = categoryrequestid;
	}
	/**
	 * @return the categoryname
	 */
	public String getCategoryname() {
		return categoryname;
	}
	/**
	 * @param categoryname the categoryname to set
	 */
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the confirmstatus
	 */
	public String getConfirmstatus() {
		return confirmstatus;
	}
	/**
	 * @param confirmstatus the confirmstatus to set
	 */
	public void setConfirmstatus(String confirmstatus) {
		this.confirmstatus = confirmstatus;
	}
	
	
	
	
}
