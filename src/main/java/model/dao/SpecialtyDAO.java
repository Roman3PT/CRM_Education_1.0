package model.dao;

import lombok.NoArgsConstructor;
import model.entity.Specialty;
import org.hibernate.SessionFactory;

@NoArgsConstructor
public class SpecialtyDAO extends CRMEducationDAO<Specialty> {

    public SpecialtyDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}