package edu.school21.chat.repositories;

import edu.school21.chat.models.User;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private static UsersRepositoryJdbcImpl instance;
    private final DataSource dataSource;

    private UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UsersRepositoryJdbcImpl getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new UsersRepositoryJdbcImpl(dataSource);
        }
        return instance;
    }
    private static final String FIND_ALL;

    static {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("WITH user_rooms AS ( ")
                .append("SELECT u.id AS user_id, u.login, u.password, ")
                .append("cr.id AS created_room_id, cr.name AS created_room_name, ")
                .append("cru.chatroom_id AS participated_room_id ")
                .append("FROM chat.User u ")
                .append("LEFT JOIN chat.Chatroom cr ON u.id = cr.owner ")
                .append("LEFT JOIN chat.User_Chatroom cru ON u.id = cru.user_id ")
                .append("ORDER BY u.id ")
                .append("OFFSET ? LIMIT ? ")
                .append(") ")
                .append("SELECT user_id, login, password, ")
                .append("array_agg(DISTINCT created_room_id) AS created_rooms, ")
                .append("array_agg(DISTINCT participated_room_id) AS participated_rooms ")
                .append("FROM user_rooms ")
                .append("GROUP BY user_id, login, password;");
        FIND_ALL = queryBuilder.toString();
    }

    @Override
    public List<User> findAll(int page, int size) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            statement.setInt(1, page * size);
            statement.setInt(2, size);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }

            return users;
        } catch (SQLException e) {
            throw new NotSavedSubEntityException(e.getMessage());
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("user_id"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .build();
    }
}