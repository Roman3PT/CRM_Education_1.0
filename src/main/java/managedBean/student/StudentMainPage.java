package managedBean.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Student;
import model.service.StudentDAOService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RequestScoped
@ManagedBean
public class StudentMainPage implements Serializable {

    private Long number;
    private List<Student> students;

    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        students.addAll(new StudentDAOService().getStudents());
    }

    public void goToAddStudent() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("createStudentPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToUpdateStudent() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("updateStudentPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        new StudentDAOService().remove(number);
    }
}