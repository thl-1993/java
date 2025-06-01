package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.security.SecureRandom;
import java.util.List;


@Component
public class UserServiceImpl implements UsersService {
    private UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("usersRepositoryJdbc") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
        String password = null;
        List<User> users = usersRepository.findAll();
        Long id = users.get(users.size() - 1).getId() + 1;
        password = createPass();
        usersRepository.save(new User(id, email, password));
        return createPass();
    }

    public String createPass() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
        int length = 12;

        SecureRandom random = new SecureRandom(); // Генератор случайных чисел
        StringBuilder password = new StringBuilder();

        // Генерация пароля
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

}
