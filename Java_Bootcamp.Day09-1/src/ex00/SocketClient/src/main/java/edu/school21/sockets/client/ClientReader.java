package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    private Scanner reader;
    private Scanner bufferedReader;
    private BufferedWriter bufferedWriter;
    private volatile boolean exit = false;
    private final String serverAddress;

    private void getMessageFromServer() throws IOException {
        WriteMsg threadWrite = new WriteMsg();
        threadWrite.start();
        String serverMessage = null;
        do {
            if (bufferedReader.hasNextLine()) {
                serverMessage = bufferedReader.nextLine();
                System.out.print("\r" + "  " + "\r");
                System.out.println(serverMessage);
                if (!serverMessage.equals("Successful!")) {
                    System.out.print("> ");
                } else {
                    threadWrite.terminate();
                    System.exit(0);
                }
            }
        }
        while (!Objects.equals(serverMessage, "Successful!")) ;
    }
    public Client(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public Scanner getReader() {
        return reader;
    }

    public void setReader(Scanner reader) {
        this.reader = reader;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Client{" +
                "reader=" + reader +
                ", bufferedReader=" + bufferedReader +
                ", bufferedWriter=" + bufferedWriter +
                ", exit=" + exit +
                ", serverAddress='" + serverAddress + '\'' +
                '}';
    }

    private class WriteMsg extends Thread {
        @Override
        public void run() {
            while (!exit) {
                try {
                    String message = reader.nextLine();
                    bufferedWriter.write(message);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        public void terminate() { exit = true; }
    }
    public void start(int port) {
        try (Socket socket = new Socket(serverAddress, port);
             Scanner bufferedReader = new Scanner(socket.getInputStream());
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            this.reader = new Scanner(System.in);
            this.bufferedReader = bufferedReader;
            this.bufferedWriter = bufferedWriter;
            getMessageFromServer();

        } catch (IOException ioException) {
            System.out.println("Error: " + ioException.getMessage());
            System.exit(-1);
        }
    }




}