package com.example.demo;

import lombok.Data;

@Data
public class Employee {
public Employee() {
		System.out.print("employee constructor");
	}
private String empName;
private String empAddress;
private long salary;
public String getEmpName() {
	return empName;
}
public void setEmpName(String empName) {
	this.empName = empName;
}
public String getEmpAddress() {
	return empAddress;
}
public void setEmpAddress(String empAddress) {
	this.empAddress = empAddress;
}
public long getSalary() {
	return salary;
}
public void setSalary(long salary) {
	this.salary = salary;
}
}
