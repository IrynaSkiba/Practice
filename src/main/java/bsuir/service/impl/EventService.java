package bsuir.service.impl;

import bsuir.dao.dao.impl.EventDao;
import bsuir.dao.entity.Event;
import bsuir.service.CrudService;

import java.util.List;
import java.util.Optional;

public class EventService implements CrudService<Event> {
    private EventDao eventDao;

    public EventService() {
        this.eventDao = new EventDao();
    }

    @Override
    public void create(Event event) {
        eventDao.save(event);
    }

    @Override
    public Event get(int id) {
        Optional<Event> optional = eventDao.get(id);
        Event event = null;
        if (optional.isPresent()) {
            event = optional.get();
        }
        return event;
    }

    @Override
    public List<Event> getAll() {
        return eventDao.getAll();
    }

    @Override
    public void update(Event event) {
        eventDao.update(event);
    }

    @Override
    public void delete(int id) {
        Optional<Event> optional = eventDao.get(id);
        optional.ifPresent(event -> eventDao.delete(event));
    }
}
