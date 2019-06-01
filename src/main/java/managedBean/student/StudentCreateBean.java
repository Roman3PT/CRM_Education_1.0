package managedBean.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Student;
import model.service.SpecialtyDAOService;
import model.service.StudentDAOService;
import org.primefaces.component.growl.Growl;

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
public class StudentCreateBean implements Serializable {

    private String name;
    private String surname;
    private String secondName;
    private String date;
    private boolean study;
    private String phoneNumber;
    private String specialtyName;
    private String ticketNumber;
    private String course;
    private String group;
    private Integer rating;
    private String description;
    private StudentDAOService studentDAOService;
    private Student student;

    private Growl growl;

    @PostConstruct
    public void init() {
        specialtyName = "ИСиТ";
        studentDAOService = new StudentDAOService();
        student = new Student();
        growl = new Growl();
    }

    public void add() {
        student.setFullName(name + " " + surname + " " + secondName);
        student.setSpecialty(new SpecialtyDAOService().getSpecialty(specialtyName));
        student.setAdmissionYear(date);
        student.setPhoneNumber(phoneNumber);
        student.setTicketNumber(ticketNumber);
        student.setCourseNumber(course);
        student.setGroupNumber(group);
        student.setExisting(study);
        student.setRating(rating);
        student.setDescription(description);
        studentDAOService.save(student);
        back();
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("studentMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        studentDAOService.close();
    }
}