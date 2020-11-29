package com.hibernate.Hibernate_Basics_01;

import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
public class App 
{
    public static void main( String[] args ) throws PersistenceException 
    {
    	StandardServiceRegistry  ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
    	Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
    	SessionFactory sf=md.getSessionFactoryBuilder().build();
    	Session session = sf.openSession();  
    	Transaction transaction=session.beginTransaction();
    	Employee e1=new Employee();
    	e1.setId(2);
    	e1.setFirsName("amol");
    	e1.setLastName("jagadambe");
    	session.save(e1);
    	transaction.commit();
    	sf.close();
    	session.close();
    	
    }
}
