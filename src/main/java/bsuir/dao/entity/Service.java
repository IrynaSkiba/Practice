package bsuir.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Service {
    private int id;
    private String name;
    private int phone;
    private List<Event> events;

    public Service(int id, String name, int phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Service(int id) {
        this.id = id;
    }

    public Service() {
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + getId() +
                ", \"name\":\"" + getName() + '\"' +
                ", \"phone\":" + getPhone() +
                '}';
    }
}
