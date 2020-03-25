package bsuir.dao.dao;

import java.sql.*;

import static bsuir.dao.dao.ConnectionData.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERS")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%d\n", resultSet.getInt("user_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
