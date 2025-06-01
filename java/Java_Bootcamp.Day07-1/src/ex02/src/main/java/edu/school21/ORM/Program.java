package edu.school21.ORM;

import edu.school21.ORM.manage.OrmManager;
import edu.school21.ORM.session.HibernateSession;
import edu.school21.ORM.user.User;

public class Program {
    public static void main(String[] args) {
        OrmManager userOrmManager = new OrmManager();
        User user = new User("Name1", "Lastname1", 10);
        userOrmManager.save(user);
        User user2 = new User("Name2", "Lastname2", 20);
        userOrmManager.save(user2);
        user2.setFirstName("NewName");
        user2.setAge(100);
        userOrmManager.update(user2);
        System.out.println(userOrmManager.findById(1L));
        HibernateSession.closeSessionFactory();
    }
}
