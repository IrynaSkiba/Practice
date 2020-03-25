package bsuir.dao.dao.impl;

import bsuir.dao.dao.ConnectorDB;
import bsuir.dao.dao.CrudDao;
import bsuir.dao.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static bsuir.dao.dao.ConnectionData.SQL_SELECT_ALL_USERS;
import static bsuir.dao.dao.ConnectionData.SQL_SELECT_USER_ID;

public class UserDao implements CrudDao<User> {
    @Override
    public Optional<User> get(int id) {
        User user = null;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String login = rs.getString("login");
                String password = rs.getString("password");

                user = new User(id, login, password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(user);

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        User user;
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                user = new User(id, login, password);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void save(User user) {
        try (Connection conn = ConnectorDB.getConnection();
             Statement statement = conn.createStatement()) {

            int row = statement.executeUpdate(generateInsert(user.getLogin(), user.getPassword()));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try (Connection conn = ConnectorDB.getConnection();
             Statement statement = conn.createStatement()) {

            int row = statement.executeUpdate(updateUserById(user.getId(), user.getLogin(), user.getPassword()));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try (Connection conn = ConnectorDB.getConnection();
             Statement statement = conn.createStatement()) {

            statement.executeUpdate(deleteById(user.getId()));
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private static String generateInsert(String login, String password) {
        String str = "INSERT INTO users (login, password) " +
                "VALUES ('" + login + "','" + password + ")";
        return str;
    }

    private static String updateUserById(int id, String login, String password) {
        return "UPDATE users SET login='" + login + "'password='" + password +
                "' WHERE user_id=" + id;
    }

    private static String deleteById(int id) {
        return "DELETE FROM users WHERE user_id=" + id;
    }
}
