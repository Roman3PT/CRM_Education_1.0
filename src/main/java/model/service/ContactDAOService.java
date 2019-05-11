package model.service;

import model.dao.ContactDAO;
import model.entity.Contact;
import model.hibernateUtil.HibernateUtil;

import java.util.List;
import java.util.Objects;

public class ContactDAOService {

    private ContactDAO contactDAO;

    public ContactDAOService() {
        contactDAO = new ContactDAO(HibernateUtil.getSessionFactory());
    }

    public void save(Contact contact) {
        contactDAO.save(contact);
    }

    public void close() {
        contactDAO.close();
    }

    public void update(Contact contact) {
        contactDAO.update(contact);
    }

    public List<Contact> getContacts() {
        return contactDAO.createQuery("FROM CONTACT", Contact.class).list();
    }

    public boolean remove(Long id) {
        Contact contact = contactDAO.get(Contact.class, id);
        boolean rc = !Objects.isNull(contact);
        if (rc)
            contactDAO.delete(contact);
        return rc;
    }

    public Contact getContact(Long id) {
        return contactDAO.get(Contact.class, id);
    }
}