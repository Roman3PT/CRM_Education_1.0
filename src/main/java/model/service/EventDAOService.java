package model.service;

import model.dao.EventDAO;
import model.entity.Event;
import model.hibernateUtil.HibernateUtil;

import java.util.List;
import java.util.Objects;

public class EventDAOService {

    private EventDAO eventDAO;

    public EventDAOService() {
        eventDAO = new EventDAO(HibernateUtil.getSessionFactory());
    }

    public void save(Event event) {
        eventDAO.save(event);
    }

    public void update(Event event) {
        eventDAO.update(event);
    }

    public void close() {
        eventDAO.close();
    }

    public List<Event> getEvents() {
        return eventDAO.createQuery("FROM EVENT", Event.class).list();
    }

    public boolean deleteEvent(Long id) {
        Event event = eventDAO.get(Event.class, id);
        boolean rc = !Objects.isNull(event);
        if (rc)
            eventDAO.delete(event);
        return rc;
    }

    public Event getEvent(Long id) {
        return eventDAO.get(Event.class, id);
    }
}
