package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;

@Component
public class ThreadConnectionFactory {

    public ThreadConnection create(Socket socket, UsersService usersService) throws IOException {
        return new ThreadConnection(socket, usersService);
    }
}