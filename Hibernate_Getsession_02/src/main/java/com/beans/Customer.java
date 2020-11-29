package com.beans;


import javax.persistence.Id;



@javax.persistence.Entity

public class Customer {
	@Id
	private int cid;
private String cname;
private int age;
public int getCid() {
	return cid;
}
public void setCid(int cid) {
	this.cid = cid;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
@Override
public String toString() {
	return "Customer [cid=" + cid + ", cname=" + cname + ", age=" + age + "]";
}

}
