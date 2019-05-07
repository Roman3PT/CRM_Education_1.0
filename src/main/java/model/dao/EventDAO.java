package model.dao;

import lombok.NoArgsConstructor;
import model.entity.Event;
import org.hibernate.SessionFactory;

@NoArgsConstructor
public class EventDAO extends CRMEducationDAO<Event> {

    public EventDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}