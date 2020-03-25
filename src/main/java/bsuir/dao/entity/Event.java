package bsuir.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Event {
    private int id;
    private String name;
    private LocalDateTime date;
    private User user;
    private List<Service> services;

    public Event(int id, String name, LocalDateTime date, User user) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.user = user;
    }

    public Event(int id) {
        this.id = id;
    }

    public Event() {
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + getId() +
                ", \"name\":\"" + getName() + '\"' +
                ", \"date\":" + getDate().toString() +
                '}';
    }
}
