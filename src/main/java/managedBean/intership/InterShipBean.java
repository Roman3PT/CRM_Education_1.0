package managedBean.intership;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Student;
import model.service.EventDAOService;
import model.service.StudentDAOService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ManagedBean
@ViewScoped
public class InterShipBean implements Serializable {

    private List<Student> students;
    private Student selectedStudent;
    private StudentDAOService studentDAOService;
    private EventDAOService eventDAOService;

    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        selectedStudent = new Student();
        studentDAOService = new StudentDAOService();
        eventDAOService = new EventDAOService();
        students.addAll(studentDAOService.getStudentsInterShip("3"));
    }

    public void distribute() {

    }

    @PreDestroy
    public void destroy() {

    }
}
