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

    public void remove(Long id) {
        Student student = studentDAO.get(Student.class, id);
        studentDAO.delete(student);
    }

    public void save(Student student) {
        studentDAO.save(student);
    }

    public Student getStudent(Long id) {
        return studentDAO.get(Student.class, id);
    }

    public Student getStudent(String ticketNumber) {
        return (Student) studentDAO.createQuery("SELECT u FROM STUDENT u WHERE u.ticketNumber = :ticketNumberName", Student.class)
                .setParameter("ticketNumberName", ticketNumber).list().get(0);
    }

    public void update(Student student) {
        studentDAO.update(student);
    }

    public void close() {
        studentDAO.close();
    }
}