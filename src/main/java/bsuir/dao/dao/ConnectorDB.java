package bsuir.dao.dao;

import java.sql.*;

import static bsuir.dao.dao.ConnectionData.*;

public class ConnectorDB  {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
