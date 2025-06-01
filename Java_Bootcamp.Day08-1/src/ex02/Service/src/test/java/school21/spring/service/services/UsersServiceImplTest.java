package school21.spring.service.services;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import school21.spring.service.config.TestApplicationConfig;
import school21.spring.service.repositories.UsersRepository;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

@Component
public class UsersServiceImplTest {
    AnnotationConfigApplicationContext context;
    UsersRepository usersRepository;
    UsersService usersService;

    @Test
    public void signUpTest_userNotSigned_success() {
        this.context =
                new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        this.usersRepository = context.getBean(
                "usersRepositoryJdbc", UsersRepository.class);
        this.usersService = context.getBean(
                "userServiceImpl", UserServiceImpl.class);

        String result = this.usersService.signUp("test@example.com");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}