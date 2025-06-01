package school21.spring.service.application;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.services.UserServiceImpl;
import school21.spring.service.services.UsersService;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        User user1 = new User(1L, "newUser1", "1");
        User user2 = new User(2L, "newUser2", "2");

        UsersRepository usersRepository = context.getBean("usersRepositoryJdbcTemp", UsersRepository.class);
        usersRepository.save(user1);
        usersRepository.save(user2);
        System.out.println(usersRepository.findAll());


        UsersService usersService = context.getBean(UsersService.class);
        usersService.signUp("newUser3");
        System.out.println(usersRepository.findAll());


    }
}
