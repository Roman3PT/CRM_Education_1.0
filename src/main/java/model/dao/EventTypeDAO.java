package model.dao;

import lombok.NoArgsConstructor;
import model.entity.EventType;
import org.hibernate.SessionFactory;

@NoArgsConstructor
public class EventTypeDAO extends CRMEducationDAO<EventType> {

    public EventTypeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
