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
            testUpdateMessage(dataSource, scanner);
            testFindAllUsers(dataSource, scanner);
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

    private static void testUpdateMessage(DataSource dataSource, Scanner scanner) {
        MessagesRepositoryJdbcImpl messagesRepository = MessagesRepositoryJdbcImpl.getInstance(dataSource);

        System.out.print("Enter message ID to update -> ");
        long id;
        try {
            id = scanner.nextLong();
        } catch (InputMismatchException e) {
            System.err.println("Invalid input: " + e.getMessage());
            scanner.next();
            return;
        }

        Optional<Message> messageOptional = messagesRepository.findById(id);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();

            System.out.print("Enter new message text -> ");
            scanner.nextLine();
            String newText = scanner.nextLine();

            message.setText(newText);
            message.setDateTime(null);

            try {
                messagesRepository.update(message);
                System.out.println("Message updated successfully: " + message);
            } catch (Exception e) {
                System.err.println("Error updating message: " + e.getMessage());
            }
        } else {
            System.err.println("Message with specified ID not found.");
        }
    }

    private static void testFindAllUsers(DataSource dataSource, Scanner scanner) {
        UsersRepository usersRepository = UsersRepositoryJdbcImpl.getInstance(dataSource);
        System.out.print("Enter page number -> ");
        int page = scanner.nextInt();

        System.out.print("Enter page size -> ");
        int size = scanner.nextInt();

        try {
            List<User> users = usersRepository.findAll(page, size);

            if (users.isEmpty()) {
                System.out.println("No users found.");
            } else {
                for (User user : users) {
                    System.out.println(user);
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching users: " + e.getMessage());
        }
    }
}