package model.service;

import model.dao.EventDAO;
import model.entity.Event;
import model.hibernateUtil.HibernateUtil;

import java.util.List;

public class EventDAOService {

    private EventDAO eventDAO;

    public EventDAOService() {
        eventDAO = new EventDAO(HibernateUtil.getSessionFactory());
    }

    public void save(Event event) {
        eventDAO.save(event);
    }

    public void close() {
        eventDAO.close();
    }

    public List<Event> getEvents() {
        return eventDAO.createQuery("FROM EVENT", Event.class).list();
    }

    public void deleteEvent(Long id) {
        Event event = eventDAO.get(Event.class, id);
        eventDAO.delete(event);
    }
}
