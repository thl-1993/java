package edu.school21.ORM.manage;

import edu.school21.ORM.session.HibernateSession;
import edu.school21.ORM.user.User;
import org.hibernate.*;



public class OrmManager {
 public OrmManager(){}

    public void save(User user){
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void update(User user){
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public User findById(Long id) {
        try (Session session = HibernateSession.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            return user;
        }
    }
}
