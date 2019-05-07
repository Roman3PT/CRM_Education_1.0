package model.dao;

import lombok.NoArgsConstructor;
import model.entity.Company;
import org.hibernate.SessionFactory;

@NoArgsConstructor
public class CompanyDAO extends CRMEducationDAO<Company> {

    private SessionFactory sessionFactory;

    public CompanyDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }
}