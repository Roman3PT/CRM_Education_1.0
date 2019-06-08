package model.service;

import model.dao.StudentDAO;
import model.entity.Student;
import model.hibernateUtil.HibernateUtil;

import java.util.List;

public final class StudentDAOService {

    private StudentDAO studentDAO;

    public StudentDAOService() {
        studentDAO = new StudentDAO(HibernateUtil.getSessionFactory());
    }

    public List<Student> getStudents() {
        return studentDAO.createQuery("FROM STUDENT", Student.class).list();
    }

    public void remove(Student student) {
        studentDAO.delete(student);
    }

    public void save(Student student) {
        studentDAO.save(student);
    }

    public Student getStudent(Long id) {
        return studentDAO.get(Student.class, id);
    }

    public Student getStudent(String ticketNumber) {
        return studentDAO.createQuery("SELECT u FROM STUDENT u WHERE u.ticketNumber = :ticketNumberName", Student.class)
                .setParameter("ticketNumberName", ticketNumber).list().get(0);
    }

    public List<Student> getStudentFromCompany(String group, Integer type_event_id) {
        return studentDAO.createQuery("SELECT s.* FROM STUDENT s" +
                " LEFT JOIN EVENT e on e.type_event.id = e.student.id " +
                "left join TYPE_EVENT t on e.type_event.id = t.id" +
                "where t.id = :typeEventName AND s.group = :groupName", Student.class)
                .setParameter("typeEventName", type_event_id)
                .setParameter("groupName", group).list();
    }

    public void update(Student student) {
        studentDAO.update(student);
    }

    public List<Student> getStudentsInterShip(String course) {
        return studentDAO.createQuery("SELECT s FROM STUDENT s WHERE s.courseNumber = :course", Student.class)
                .setParameter("course", course).list();
    }

    public void close() {
        studentDAO.close();
    }
}