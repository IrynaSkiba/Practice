package bsuir.dao.dao.impl;

import bsuir.dao.dao.ConnectorDB;
import bsuir.dao.dao.CrudDao;
import bsuir.dao.entity.Event;
import bsuir.dao.entity.Service;
import bsuir.dao.entity.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static bsuir.dao.dao.ConnectionData.SQL_SELECT_ALL_EVENTS;
import static bsuir.dao.dao.ConnectionData.SQL_SELECT_EVENT_ID;

public class EventDao implements CrudDao<Event> {
    @Override
    public Optional<Event> get(int id) {
        Event event = null;
        Service service;
        User user;
        boolean flag = true;
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_EVENT_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (flag) {
                    String name = rs.getString("name");
                    LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();

                    user = new User(rs.getInt("user_id"));
                    flag = false;
                    event = new Event(id, name, date, user);
                }
                if (rs.getInt("service_id") != 0) {
                    service = new Service(rs.getInt("service_id"));
                    event.getServices().add(service);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(event);
    }

    @Override
    public List<Event> getAll() {
        Event event;
        User user;
        ArrayList<Event> events = new ArrayList<>();
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_EVENTS);
            while (rs.next()) {
                int id = rs.getInt("event_id");
                String name = rs.getString("name");
                LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();

                user = new User(rs.getInt("user_id"));

                event = new Event(id, name, date, user);
                events.add(event);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return events;
    }

    @Override
    public void save(Event event) {
        try (Connection conn = ConnectorDB.getConnection();
             Statement statement = conn.createStatement()) {

            int row = statement.executeUpdate(generateInsert(event.getName(), event.getDate(), event.getUser().getId()));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public void update(Event event) {
        try (Connection conn = ConnectorDB.getConnection();
             Statement statement = conn.createStatement()) {

            int row = statement.executeUpdate(updateEventById(event.getId(), event.getName(), event.getDate(),
                    event.getUser().getId()));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public void delete(Event event) {
        try (Connection conn = ConnectorDB.getConnection();
             Statement statement = conn.createStatement()) {

            conn.setAutoCommit(false);
            statement.addBatch(deleteById(event.getId()));
            try {
                int[] rows = statement.executeBatch();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private static String generateInsert(String name, LocalDateTime date, int userId) {
        String str = "INSERT INTO events (name, date, user_id) " +
                "VALUES ('" + name + "'," + date + "," + "," + userId + ")";
        return str;
    }

    private static String updateEventById(int id, String name, LocalDateTime date, int userId) {
        return "UPDATE events SET name='" + name + " date=" + date + " user_id=" + userId +
                " WHERE id=" + id;
    }

    private static String deleteById(int id) {
        return "DELETE FROM events WHERE id=" + id;
    }
}
