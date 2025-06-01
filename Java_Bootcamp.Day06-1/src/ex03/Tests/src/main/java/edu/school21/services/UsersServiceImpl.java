package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    boolean authenticate(String login, String password) {
        boolean status = false;
        User user = usersRepository.findByLogin(login);
        if (user == null) {
            return false;
        }
        if (user.isStatus()) {
            throw new AlreadyAuthenticatedException("User already authenticated");
        }
        if (user.getPassword().equals(password)) {
            user.setStatus(true);
            usersRepository.update(user);
            status = true;

        }
        return status;
    }


}
