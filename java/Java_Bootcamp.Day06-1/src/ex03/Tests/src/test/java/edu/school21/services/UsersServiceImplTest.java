package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


public class UsersServiceImplTest {
    private UsersRepository usersRepository= Mockito.mock(UsersRepository.class);
    UsersServiceImpl usersService = new UsersServiceImpl(usersRepository);

    @Test
    public void UsersServiceImplAuthenticateSuccess() {
        User user = new User(1,"log1","pass1");
        user.setStatus(false);

        Mockito.when(usersRepository.findByLogin("log1")).thenReturn(user);
        boolean auth = usersService.authenticate("log1","pass1");
        Mockito.verify(usersRepository, Mockito.times(1)).update(user);
        assertTrue(auth);
    }

    @Test
    public void UsersServiceImplAuthenticateFalsePass() {
        User user = new User(1,"log1","pass1");
        user.setStatus(false);

        Mockito.when(usersRepository.findByLogin("log1")).thenReturn(user);
        boolean auth = usersService.authenticate("log1","pass2");
        assertFalse(auth);
    }

    @Test
    public void UsersServiceImplAuthenticateFalseLog() {
        User user = new User(1,"log1","pass1");
        user.setStatus(false);

        Mockito.when(usersRepository.findByLogin("log1")).thenReturn(null);
        boolean auth = usersService.authenticate("log1","pass1");
        assertFalse(auth);
    }
    @Test
    public void UsersServiceImplAuthenticateAlreadyAuth() {
        User user = new User(1,"log1","pass1");
        user.setStatus(true);

        Mockito.when(usersRepository.findByLogin("log1")).thenReturn(user);
        assertThrows(AlreadyAuthenticatedException.class, () -> usersService.authenticate("log1", "pass1"));;
    }



}
