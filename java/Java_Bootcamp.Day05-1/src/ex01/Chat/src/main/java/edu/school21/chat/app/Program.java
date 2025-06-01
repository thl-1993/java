package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.*;
import javax.sql.DataSource;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        DataSource dataSource = DatabaseUtils.getDataSource();
        if (dataSource != null) {
            Scanner scanner = new Scanner(System.in);
            testFindById(dataSource, scanner);
        }
    }

    private static void testFindById(DataSource dataSource, Scanner scanner) {
        System.out.print("Enter message ID to find -> ");
        long id;

        try {
            id = scanner.nextLong();
        } catch (Exception e) {
            System.err.println("Invalid input: " + e.getMessage());
            return;
        }

        MessagesRepositoryJdbcImpl messagesRepository = MessagesRepositoryJdbcImpl.getInstance(dataSource);
        Optional<Message> message = messagesRepository.findById(id);

        if (message.isPresent()) {
            System.out.println(message.get());
        } else {
            System.err.println("Message not found.");
        }
    }
}