/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.VetSystem.RestAPI.config;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author DELL
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {

            Configuration cfg = new Configuration();
            Properties p = new Properties();
            //load properties file
            p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            cfg.setProperties(p);
            // build session factory
            //SessionFactory factory = cfg.buildSessionFactory();
            // get session
            //Session session = factory.openSession();
            //System.out.println(session.isConnected());
            // close session
            //session.close();
            //System.out.println(session.isConnected());

            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            //sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            sessionFactory = cfg.buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
