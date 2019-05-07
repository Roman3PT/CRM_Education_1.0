package model.dao;

import lombok.NoArgsConstructor;
import model.entity.Contact;
import org.hibernate.SessionFactory;

@NoArgsConstructor
public class ContactDAO extends CRMEducationDAO<Contact> {

    private SessionFactory sessionFactory;

    public ContactDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }
}
