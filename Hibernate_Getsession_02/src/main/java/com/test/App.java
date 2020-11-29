package com.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.beans.Customer;

public class App {
public static void main(String[] args) {
	Customer customer=new Customer();
	Configuration con=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class);
	ServiceRegistry sr=new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
	SessionFactory sf=con.buildSessionFactory(sr);
	Session s=sf.openSession();
	Transaction tx=s.beginTransaction();
	customer=(Customer) s.get(Customer.class, 101);
	System.out.println(customer);
	}
}
