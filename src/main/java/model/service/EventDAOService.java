package model.service;

import model.dao.EventDAO;
import model.entity.Event;
import model.entity.Student;
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

    public List<Event> getEventsToCompany(String companyName) {
        return eventDAO.createQuery("SELECT e FROM EVENT e INNER JOIN COMPANY c ON e.company.id = c.id WHERE c.name = :companyName", Event.class)
                .setParameter("companyName", companyName).list();
    }

    public boolean getStatusOnPractice(Student student, Long number) {
        List<Event> events = eventDAO.createQuery("SELECT e FROM EVENT e WHERE e.student.id = :studentId AND e.type.id = :practiceNumber", Event.class)
                .setParameter("studentId", student.getId())
                .setParameter("practiceNumber", number).list();
        return events.isEmpty();
    }

    public boolean deleteEvent(Long id) {
        Event event = eventDAO.get(Event.class, id);
        boolean rc = !Objects.isNull(event);
        if (rc)
            eventDAO.delete(event);
        return rc;
    }

    public void deleteSelectedEvent(Event event) {
        eventDAO.delete(event);
    }

    public Event getEvent(Long id) {
        return eventDAO.get(Event.class, id);
    }
}
