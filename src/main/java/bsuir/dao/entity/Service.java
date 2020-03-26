package bsuir.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "services")
public class Service {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private long phone;
    @ManyToMany(mappedBy = "services")
    private List<Event> events;

    public Service(int id, String name, long phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.events = Collections.emptyList();
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
