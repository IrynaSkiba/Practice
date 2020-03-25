package bsuir.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private int id;
    private String login;
    private String password;
    private List<Event> events;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(int id) {
        this.id = id;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + getId() +
                ", \"login\":\"" + getLogin() + '\"' +
                ", \"password\":\"" + getPassword() + '\"' +
                '}';
    }
}
