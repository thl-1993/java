package edu.school21.ORM.session;

import edu.school21.ORM.user.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    private HibernateSession() {
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure()
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (HibernateException e) {
                System.out.println("Error while creating SessionFactory: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
