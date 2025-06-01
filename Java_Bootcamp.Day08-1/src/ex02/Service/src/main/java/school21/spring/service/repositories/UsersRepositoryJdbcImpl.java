package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("usersRepositoryJdbc")
public class UsersRepositoryJdbcImpl implements UsersRepository {
    private DataSource dataSource;

    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Optional<User> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users where id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                User user = new User(resultSet.getLong("id"), resultSet.getString("email"), resultSet.getString("password"));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User entity) {
        Long id = entity.getId();
        String email = entity.getEmail();
        String password = entity.getPassword();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (id, email, password) VALUES (?, ?, ?)");
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(User entity) {
        Long id = entity.getId();
        String email = entity.getEmail();
        String password = entity.getPassword();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE users SET email = ?, password = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                preparedStatement.setLong(3, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getLong("id"), resultSet.getString("email"), resultSet.getString("password"));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
