package fr.cyu.depinfo.agp.tahiti.persistence.bde.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {
    private static SessionFactory sessionFactory;
    private static Configuration config;

    public static Configuration getConfig() {
        if (config == null) {
            config = new Configuration();
            config.addAnnotatedClass(OfferData.class);

            config.configure("./hibernate.cfg.xml");
        }
        return config;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = getConfig();
                sessionFactory = config.buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }

}
