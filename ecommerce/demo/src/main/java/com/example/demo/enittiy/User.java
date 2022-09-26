package com.example.demo.enittiy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name="user_tbl")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer userid;

@Column(nullable=true)
private String firstName;

@Column(nullable=true)
private String lastName;
@Column(nullable = false,unique=true)
@NotEmpty
@Email(message="{errors.invalid_email}")
private String email;
@NotEmpty
private String password;



private String enable;



@OneToOne
ShopRegister shopregister;








@ManyToMany(cascade= CascadeType.MERGE,fetch=FetchType.EAGER)
@JoinTable(name="users_roles",
	joinColumns= {@JoinColumn(name="userid")},inverseJoinColumns= {@JoinColumn(
name="role_id")
		})
private List<Role> roles=new ArrayList<>();
	

public Integer getId() {
	return userid;
}






public ShopRegister getShopregister() {
	return shopregister;
}






public void setShopregister(ShopRegister shopregister) {
	this.shopregister = shopregister;
}






public void setId(Integer id) {
	this.userid = id;
}


public String getFirstName() {
	return firstName;
}


public void setFirstName(String firstName) {
	this.firstName = firstName;
}


public String getLastName() {
	return lastName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public List<Role> getRoles() {
	return roles;
}


public void setRoles(List<Role> roles) {
	this.roles = roles;
}



public String getEnable() {
	return enable;
}


public void setEnable(String enable) {
	this.enable = enable;
}




public User(User user) {
	this.firstName=user.getFirstName();
	this.lastName=user.getLastName();
	this.email=user.getEmail();
	this.password=user.getPassword();
	this.roles=user.getRoles();
	
}



public boolean hasRole(String roleName) {
    Iterator<Role> iterator = this.roles.iterator();
    while (iterator.hasNext()) {
        Role role = iterator.next();
        if (role.getName().equals(roleName)) {
            return true;
        }
    }
     
    return false;
}




public User() {
	super();
	// TODO Auto-generated constructor stub
}



		
		








	
}
