package com.example.demo.enittiy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CountRecord {


@Id

private Long id;

private long previous_count;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Long getPrevious_count() {
	return previous_count;
}

public void setPrevious_count(Long previous_count) {
	this.previous_count = previous_count;
}

@Override
public String toString() {
	return "CountRecord [id=" + id + ", previous_count=" + previous_count + "]";
}


	
}
