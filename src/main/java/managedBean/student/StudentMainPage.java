package managedBean.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Student;
import model.service.StudentDAOService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ViewScoped
@ManagedBean
public class StudentMainPage implements Serializable {

    private Long number;
    private String study;
    private List<Student> students;
    private StudentDAOService studentDAOService;

    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        studentDAOService = new StudentDAOService();
        students.addAll(studentDAOService.getStudents());
    }

    public void goToAddStudent() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("createStudentPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToUpdateStudent() {
        if (number < 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Введите корректный номер"));
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", number);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("updateStudentPage.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void remove() {
        if (number < 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Введите корректный номер"));
        } else {
            studentDAOService.remove(number);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("studentMainPage.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String studentStyding(boolean isExist) {
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