package model.service;

import model.dao.StudentDAO;
import model.entity.Student;
import model.hibernateUtil.HibernateUtil;

import java.util.List;

public class StudentDAOService {

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
}