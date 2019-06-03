import model.entity.Specialty;
import model.entity.Student;
import model.hibernateUtil.HibernateUtil;
import model.service.SpecialtyDAOService;
import model.service.StudentDAOService;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.util.List;

public class TestDAO {

    private Logger LOG = Logger.getLogger(TestDAO.class);

    @Test
    public void hibernateUtil() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Test
    public void specialtyServiceTest() {
        Specialty specialty = new SpecialtyDAOService().getSpecialty("ПОИТ");
        LOG.info(specialty);
    }

    @Test
    public void studentList() {
        List<Student> students = new StudentDAOService().getStudents();
        LOG.info(students);
    }
}