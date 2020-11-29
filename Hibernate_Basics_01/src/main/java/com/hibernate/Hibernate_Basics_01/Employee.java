package com.hibernate.Hibernate_Basics_01;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_employee")
public class Employee {

@Id
private int id;
@Column(name="first_name")
private String firsName;
@Column(name="last_name")
private String lastName;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFirsName() {
	return firsName;
}
public void setFirsName(String firsName) {
	this.firsName = firsName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}

}
