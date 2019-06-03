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
import java.util.Objects;

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
    private Student selectedStudent;

    private String course;
    private String group;
    private Boolean filter;

    @PostConstruct
    public void init() {
        selectedStudent = new Student();
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
        if (Objects.isNull(selectedStudent))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Введите корректный номер"));
        else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().clear();
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedStudent", selectedStudent);
                FacesContext.getCurrentInstance().getExternalContext().redirect("updateStudentPage.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void remove() {
        if (Objects.isNull(selectedStudent))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Введите корректный номер"));
        else {
            studentDAOService.remove(selectedStudent);
            refreshPage();
        }
    }

    public String studentStyding(boolean isExist) {
        if (isExist)
            return "Да";
        else
            return "Нет";
    }

    public void getStudentIsNotAtPractice() {
        if (Objects.isNull(course) || Objects.isNull(group)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Введите корректный номер"));
        } else
            studentDAOService.getStudentFromCompany(group, 1);
    }

    public void refreshPage() {
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