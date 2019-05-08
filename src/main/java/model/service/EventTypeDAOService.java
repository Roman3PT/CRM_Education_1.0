package model.service;

import lombok.Data;
import model.dao.EventTypeDAO;
import model.entity.EventType;
import model.hibernateUtil.HibernateUtil;

@Data
public class EventTypeDAOService {

    private EventTypeDAO eventTypeDAO;

    public EventTypeDAOService() {
        eventTypeDAO = new EventTypeDAO(HibernateUtil.getSessionFactory());
    }

    public EventType getEventType(String eventTypeName) {
        return eventTypeDAO.createQuery("SELECT u FROM TYPE_EVENT u WHERE u.name = :eventTypeName", EventType.class)
                .setParameter("eventTypeName", eventTypeName).list().get(0);
    }
}
