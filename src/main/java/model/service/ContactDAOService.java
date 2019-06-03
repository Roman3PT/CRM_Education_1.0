package model.service;

import model.dao.ContactDAO;
import model.entity.Contact;
import model.hibernateUtil.HibernateUtil;

import java.util.List;

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

    public void remove(Contact contact) {
        contactDAO.delete(contact);
    }

    public Contact getContact(Long id) {
        return contactDAO.get(Contact.class, id);
    }
}