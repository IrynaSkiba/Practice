package bsuir.dao.dao;

public class ConnectionData {
    static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB = "bsuir";
    static final String URL = "jdbc:mysql://127.0.0.1:3306/" + DB +
//            "?useUnicode=true&" +
//            "useJDBCCompliantTimezoneShift=true&" +
//            "useLegacyDatetimeCode=false" +
            "?serverTimezone=UTC";
    static final String USER = "root";
    static final String PASSWORD = "root";

    public static final String SQL_SELECT_USER_ID = "SELECT * FROM users " +
            "WHERE user_id=? order by user_id";
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users" +
            " order by user_id ";
    public static final String SQL_SELECT_EVENT_ID = "select events.event_id as id,events.name as name, date, user_id\n" +
            "services.service_id as service_id\n" +
            "from events\n" +
            "left join event_service\n" +
            "on events.event_id=event_service.event_id\n" +
            "left join services\n" +
            "on event_service.service_id=services.service_id\n" +
            "where events.event_id=?\n" +
            "order by event_id";
    public static final String SQL_SELECT_ALL_EVENTS = "select events.event_id as id, events.name as name, date,\n" +
            "user_id,\n" +
            "services.service_id as service_id\n" +
            "from events\n" +
            "left join users\n" +
            "on events.user_id=users.user_id\n" +
            "left join event_service\n" +
            "on events.event_id=event_service.event_id\n" +
            "left join services\n" +
            "on event_service.service_id=services.service_id" +
            "order by service_id";
    public static final String SQL_SELECT_SERVICE_ID = "select services.service_id as id," +
            " services.name as name,\n" +
            "services.phone as phone,\n" +
            "event_service.event_id  as event_id,\n" +
            "from services\n" +
            "left join event_service\n" +
            "on services.service_id=event_service.service_id\n" +
            "left join events\n" +
            "on events.event_id=event_service.event_id\n" +
            " WHERE services.service_id=? " +
            "order by services.service_id";
    public static final String SQL_SELECT_ALL_SERVICES = "select services.service_id as id," +
            " services.name as name,\n" +
            " services.phone as phone,\n" +
            "event_service.event_id  as event_id,\n" +
            "from services\n" +
            "left join event_service\n" +
            "on services.service_id=event_service.service_id\n" +
            "left join events\n" +
            "on events.event_id=event_service.event_id\n" +
            "order by services.service_id";
}
