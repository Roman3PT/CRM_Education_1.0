package managedBean.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Student;
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
    private StudentDAOService studentDAOService;

    private Student student;

    @PostConstruct
    public void init() {
        Long id = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
        studentDAOService = new StudentDAOService();
        student = studentDAOService.getStudent(id);
        splitFullName(student.getFullName());
    }

    public void updateStudent() {
        student.setFullName(name + " " + surname + " " + secondName);
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

    @PreDestroy
    public void destroy() {
        studentDAOService.close();
    }
}