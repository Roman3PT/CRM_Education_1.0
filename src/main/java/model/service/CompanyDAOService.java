package model.service;

import model.dao.CompanyDAO;
import model.entity.Company;
import model.hibernateUtil.HibernateUtil;

import java.util.List;

public class CompanyDAOService {

    private CompanyDAO companyDAO;

    public CompanyDAOService() {
        companyDAO = new CompanyDAO(HibernateUtil.getSessionFactory());
    }

    public void save(Company company) {
        companyDAO.save(company);
    }

    public List<Company> getListCompany() {
        return companyDAO.createQuery("FROM COMPANY", Company.class).list();
    }

    public void remove(Company company) {
        companyDAO.delete(company);
    }

    public Company getCompany(Long id) {
        return companyDAO.get(Company.class, id);
    }

    public Company getCompany(String name) {
        return companyDAO.createQuery("SELECT u FROM COMPANY u WHERE u.name = :companyName", Company.class)
                .setParameter("companyName", name).list().get(0);
    }

    public Company getCompany(String login, String password) {
        return companyDAO.createQuery("SELECT c FROM COMPANY c WHERE c.login = :loginValue AND c.password = :passwordValue", Company.class)
                .setParameter("loginValue", login)
                .setParameter("passwordValue", password)
                .list().get(0);
    }

    public List<Company> getListCompanyToContact() {
        return companyDAO.createQuery("SELECT u FROM COMPANY u LEFT JOIN CONTACT c ON u.id = c.company.id WHERE c.id IS NULL", Company.class).list();
    }

    public void update(Company company) {
        companyDAO.update(company);
    }

    public void close() {
        companyDAO.close();
    }
}
