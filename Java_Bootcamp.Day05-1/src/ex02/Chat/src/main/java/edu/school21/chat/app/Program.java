package edu.school21.chat.app;

import edu.school21.chat.models.ChatRoom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        DataSource dataSource = DatabaseUtils.getDataSource();
        if (dataSource != null) {
            Scanner scanner = new Scanner(System.in);
            testFindById(dataSource, scanner);
            testSaveMessage(dataSource);
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

    private static void testSaveMessage(DataSource dataSource) {
        MessagesRepositoryJdbcImpl messagesRepository = MessagesRepositoryJdbcImpl.getInstance(dataSource);

        User owner = new User(2L, "user", "user", new ArrayList<>(), new ArrayList<>());
        ChatRoom room = new ChatRoom(3L, "room", owner, new ArrayList<>());
        Message message = new Message(null, owner, room, "Hello test!", LocalDateTime.now());

        if (owner.getId() == null || room.getId() == null) {
            throw new NotSavedSubEntityException("Owner or room does not have a valid ID.");
        } else {
            Message newMessage = messagesRepository.save(message);
        if (message.getId() != null) {
            System.out.println("Saved message: " + newMessage.toString());
        } else {
            System.err.println("Failed to save the message.");
        }
        }
    }
}