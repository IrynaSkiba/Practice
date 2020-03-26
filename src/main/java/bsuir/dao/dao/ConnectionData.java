package bsuir.dao.dao;

public class ConnectionData {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB = "bsuir";
    static final String URL = "jdbc:mysql://127.0.0.1:3306/" + DB +
            "?serverTimezone=UTC";
    static final String USER = "root";
    static final String PASSWORD = "root";

    public static final String SQL_SELECT_USER_ID = "SELECT * FROM users " +
            "WHERE user_id=? order by user_id";
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users " +
            "order by user_id ";
    public static final String SQL_SELECT_ALL_SERVICES = "SELECT * FROM services " +
            "order by service_id ";
    public static final String SQL_SELECT_SERVICE_ID = "SELECT * FROM services " +
            "WHERE service_id=? order by service_id";
    public static final String SQL_SELECT_ALL_EVENTS = "SELECT * FROM events " +
            "order by event_id ";
    public static final String SQL_SELECT_EVENT_ID = "SELECT * FROM events " +
            "WHERE event_id=? order by event_id";
}
