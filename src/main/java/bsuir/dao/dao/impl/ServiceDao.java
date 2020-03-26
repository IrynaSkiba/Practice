package bsuir.dao.dao.impl;

import bsuir.dao.dao.ConnectorDB;
import bsuir.dao.dao.CrudDao;
import bsuir.dao.entity.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static bsuir.dao.dao.ConnectionData.SQL_SELECT_ALL_SERVICES;
import static bsuir.dao.dao.ConnectionData.SQL_SELECT_SERVICE_ID;

public class ServiceDao implements CrudDao<Service> {
    @Override
    public Optional<Service> get(int id) {
        Service service = null;
        boolean flag = true;
        HashSet<Integer> eventSet = new HashSet<>();
        try (Connection connection = ConnectorDB.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(SQL_SELECT_SERVICE_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (flag) {
                    String name = rs.getString("name");
                    long phone = rs.getInt("phone");

                    service = new Service(id, name, phone);
                    flag = false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(service);
    }

    @Override
    public List<Service> getAll() {
        Service service;
        List<Service> services = new ArrayList<>();
        try (Connection connection = ConnectorDB.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_SERVICES);
            while (rs.next()) {
                int id = rs.getInt("service_id");
                String name = rs.getString("name");
                long phone = rs.getLong("phone");
                service = new Service(id, name, phone);
                services.add(service);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return services;
    }

    @Override
    public void save(Service service) {
        try (Connection conn = ConnectorDB.getConnection();
             Statement statement = conn.createStatement()) {

            int row = statement.executeUpdate(generateInsert(service.getName(), service.getPhone()));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public void update(Service service) {
        try (Connection conn = ConnectorDB.getConnection();
             Statement statement = conn.createStatement()) {

            int row = statement.executeUpdate(updateServiceById(service.getId(), service.getName(), service.getPhone()));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public void delete(Service service) {
        try (Connection conn = ConnectorDB.getConnection();
             Statement statement = conn.createStatement()) {

            conn.setAutoCommit(false);
            statement.addBatch(deleteById(service.getId()));

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

    private static String generateInsert(String name, long phone) {
        return "INSERT INTO services (name, phone) " +
                "VALUES ('" + name + "', " + phone + ")";
    }

    private static String updateServiceById(int id, String name, long phone) {
        return "UPDATE services SET name='" + name + "' phone=" + phone +
                " WHERE service_id=" + id;
    }

    private static String deleteById(int id) {
        return "DELETE FROM services WHERE service_id=" + id;
    }
}
