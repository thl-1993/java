package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import lombok.Getter;
import org.springframework.dao.EmptyResultDataAccessException;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ThreadConnection extends Thread {
    private final Socket socket;
    private final UsersService usersService;
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;
    private boolean isFinished;
    @Getter
    private Long chatId;

    public ThreadConnection(Socket socket, UsersService usersService) throws IOException {
        this.socket = socket;
        this.usersService = usersService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.isFinished = false;
    }

    @Override
    public void run() {
        System.out.println("Client connected: " + socket.getInetAddress());
        try {
            while (!isFinished) {
                sendMessageClient("Hello from Server!");
                bufferedWriter.flush();
                getClientAction();
            }
        } catch (IOException | EmptyResultDataAccessException | NullPointerException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        } finally {
            try {
                refuseConnection();
            } catch (IOException e) {
                System.err.println("Error while closing connection: " + e.getMessage());
            }
        }
    }


    private void getClientAction() throws IOException {
        String clientWord = bufferedReader.readLine();
        if (clientWord != null) {
            switch (clientWord.toLowerCase()) {
                case "signup":
                    signUp();
                    break;
                case "exit":
                    sendMessageClient("Successful!");
                    refuseConnection();
                    break;
                default:
                    sendMessageClient("Unknown command: " + clientWord);
            }
        }
    }


    private void signUp() throws IOException {
        try {
            sendMessageClient("Enter username:");
            String username = bufferedReader.readLine();
            sendMessageClient("Enter password:");
            String password = bufferedReader.readLine();
            if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                sendMessageClient("Invalid data for signUp.");
            } else {
                if (!usersService.signUp(username, password)) {
                    sendMessageClient("Failed! User with this login already exists.");
                } else {
                    sendMessageClient("Successful!");
                    refuseConnection();
                }
            }
        } catch (IOException ioException) {
            handleIOException(ioException);
        }
    }

    void sendMessageClient(String message) throws IOException {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (SocketException e) {
            System.out.println("Client disconnected unexpectedly: " + e.getMessage());
            try {
                refuseConnection();
            } catch (IOException ioException) {
                System.err.println("Error closing connection: " + ioException.getMessage());
            }
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    void refuseConnection() throws IOException {
        try {
            if (Server.removeConnection(this)) {
                bufferedReader.close();
                bufferedWriter.close();
                socket.close();
                System.out.println("Connection closed: " + socket.getInetAddress());
            }
        } catch (IOException ioException) {
            handleIOException(ioException);
        } finally {
            isFinished = true;
        }
    }

    private void handleIOException(IOException e) throws IOException {
        sendMessageClient("An error occurred. Closing connection." + e.getMessage() );
        refuseConnection();
    }
}