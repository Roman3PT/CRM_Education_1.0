package model.dao;

import lombok.NoArgsConstructor;
import model.entity.Student;
import org.hibernate.SessionFactory;

@NoArgsConstructor
public class StudentDAO extends CRMEducationDAO<Student> {

    public StudentDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
