package model.service;

import model.dao.SpecialtyDAO;
import model.entity.Specialty;
import model.hibernateUtil.HibernateUtil;

public class SpecialtyDAOService {

    private SpecialtyDAO specialtyDAO;

    public SpecialtyDAOService() {
        specialtyDAO = new SpecialtyDAO(HibernateUtil.getSessionFactory());
    }

    public Specialty getSpecialty(String specialtyName) {
        return specialtyDAO.createQuery("SELECT u FROM SPECIALTY u WHERE u.name = :specialtyName", Specialty.class)
                .setParameter("specialtyName", specialtyName).list().get(0);
    }
}