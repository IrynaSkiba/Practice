package bsuir.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Event {
    private int id;
    private String name;
    private BigDecimal cost;
    private User user;
    private List<Service> services;

    public Event(int id, String name, BigDecimal cost, User user) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.user = user;
        this.services = Collections.emptyList();
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
                ", \"cost\":" + getCost() +
                '}';
    }
}
