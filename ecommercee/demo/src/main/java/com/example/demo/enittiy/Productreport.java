package com.example.demo.enittiy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.productDto;

@Entity
public class Productreport {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String report;

	private String Reportstatus;
   

	
	
	
	
@OneToOne
private Order orderr;






public int getId() {
	return id;
}






public void setId(int id) {
	this.id = id;
}






public String getReport() {
	return report;
}






public void setReport(String report) {
	this.report = report;
}






public String getReportstatus() {
	return Reportstatus;
}






public void setReportstatus(String reportstatus) {
	Reportstatus = reportstatus;
}






public Order getOrderr() {
	return orderr;
}






public void setOrderr(Order orderr) {
	this.orderr = orderr;
}








	
}
