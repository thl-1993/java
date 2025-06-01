package school21.spring.service.application;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;


import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws SQLException {
        User user1 = new User(1L, "email1");
        User user2 = new User(2L, "email2");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        usersRepository.save(user1);
        usersRepository.save(user2);
        System.out.println("FindALL-");
        System.out.println(usersRepository.findAll());
        UsersRepository usersRepository2 = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository2.findAll());
        System.out.println("FindById-");
        System.out.println(usersRepository.findById(2L));
        System.out.println(usersRepository2.findById(2L));
        System.out.println("FindByEmail-");
        System.out.println(usersRepository.findByEmail("email1"));
        System.out.println(usersRepository2.findByEmail("email1"));
    }
}
