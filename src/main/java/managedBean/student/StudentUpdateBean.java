package managedBean.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Student;
import model.service.SpecialtyDAOService;
import model.service.StudentDAOService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ManagedBean
@ViewScoped
public class StudentUpdateBean implements Serializable {

    private String name;
    private String surname;
    private String secondName;

    private String specialty;
    private String admissionYear;
    private String phoneNumber;
    private String ticketNumber;
    private String course;
    private String groupNumber;
    private boolean existing;
    private Integer rating;
    private String description;

    private SpecialtyDAOService specialtyDAOService;
    private StudentDAOService studentDAOService;
    private Student student;

    @PostConstruct
    public void init() {
        student = (Student) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectedStudent");
        specialtyDAOService = new SpecialtyDAOService();
        studentDAOService = new StudentDAOService();
        splitFullName(student.getFullName());
        specialty = student.getSpecialty().getName();
        admissionYear = student.getAdmissionYear();
        phoneNumber = student.getPhoneNumber();
        ticketNumber = student.getTicketNumber();
        course = student.getCourseNumber();
        groupNumber = student.getGroupNumber();
        existing = student.isExisting();
        rating = student.getRating();
        description = student.getDescription();
    }

    public void updateSelectedStudent() {
        student.setFullName(name + " " + surname + " " + secondName);
        student.setAdmissionYear(admissionYear);
        student.setPhoneNumber(phoneNumber);
        student.setTicketNumber(ticketNumber);
        student.setSpecialty(specialtyDAOService.getSpecialty(specialty));

        student.setCourseNumber(course);
        student.setGroupNumber(groupNumber);
        student.setExisting(existing);
        student.setRating(rating);
        student.setDescription(description);
        studentDAOService.update(student);
        back();
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("studentMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void splitFullName(String fullName) {
        String[] _fullName = fullName.split(" ");
        name = _fullName[0];
        surname = _fullName[1];
        secondName = _fullName[2];

    }

    public String studentStuding(boolean isExist) {
        if (isExist)
            return "Да";
        else
            return "Нет";
    }

    @PreDestroy
    public void destroy() {
        studentDAOService.close();
    }
}