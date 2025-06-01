package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.models.ChatRoom;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private static MessagesRepositoryJdbcImpl instance;
    private final DataSource dataSource;
    private MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public static MessagesRepositoryJdbcImpl getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new MessagesRepositoryJdbcImpl(dataSource);
        }
        return instance;
    }

    private static final String FIND_BY_ID = "SELECT " +
            "m.id AS m_id, " +
            "m.text AS m_text, " +
            "m.date_time AS m_datetime, " +
            "u.id AS u_id, " +
            "u.login AS u_login, " +
            "u.password AS u_password, " +
            "r.id AS r_id, " +
            "r.name AS r_name " +
            "FROM chat.Message m " +
            "LEFT JOIN chat.User u ON m.author = u.id " +
            "LEFT JOIN chat.Chatroom r ON m.room = r.id " +
            "WHERE m.id = ?;";

    private static final String SAVE_MESSAGE = "INSERT INTO chat.Message (author, room, text, date_time) VALUES (?, ?, ?, ?)";


    @Override
    public Optional<Message> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) throw new RuntimeException("Query result is empty");

            User user = User.builder()
                    .id(resultSet.getLong("u_id"))
                    .login(resultSet.getString("u_login"))
                    .password(resultSet.getString("u_password"))
                    .createdRooms(null)
                    .socializeRooms(null)
                    .build();

            ChatRoom chatRoom = ChatRoom.builder()
                    .id(resultSet.getLong("r_id"))
                    .name(resultSet.getString("r_name"))
                    .owner(null)
                    .messages(null)
                    .build();

            Message message = Message.builder()
                    .id(resultSet.getLong("m_id"))
                    .author(user)
                    .room(chatRoom)
                    .text(resultSet.getString("m_text"))
                    .dateTime(resultSet.getTimestamp("m_datetime").toLocalDateTime())
                    .build();

            return Optional.of(message);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Message save(Message message) {
        if (message.getAuthor() == null || message.getAuthor().getId() == null) {
            throw new NotSavedSubEntityException("Message author has not been saved.");
        }
        if (message.getRoom() == null || message.getRoom().getId() == null) {
            throw new NotSavedSubEntityException("Message room has not been saved.");
        }

        // Используем константу для запроса
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_MESSAGE, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getRoom().getId());
            statement.setString(3, message.getText());
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(message.getDateTime()));

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Saving message failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    message.setId(generatedKeys.getLong(1));
                    return message;
                } else {
                    throw new SQLException("Saving message failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the message: " + e.getMessage(), e);
        }
    }
}