package managedBean.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Company;
import model.entity.Event;
import model.entity.EventType;
import model.entity.Student;
import model.service.CompanyDAOService;
import model.service.EventDAOService;
import model.service.EventTypeDAOService;
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
@ManagedBean
@ViewScoped
public class EventCreatePage implements Serializable {

    private List<Company> companies;
    private String companyName;
    private Event event;

    private String eventTypeName;
    private String ticketNumber;
    private String description;

    private CompanyDAOService companyDAOService;
    private EventDAOService eventDAOService;

    @PostConstruct
    public void init() {
        eventTypeName = "Практика производственная";
        companyDAOService = new CompanyDAOService();
        eventDAOService = new EventDAOService();
        companies = new ArrayList<>();
        companies.addAll(companyDAOService.getListCompany());
        event = new Event();
    }

    public void add() {
        Student student = new StudentDAOService().getStudent(ticketNumber);
        EventType type = new EventTypeDAOService().getEventType(eventTypeName);
        if (student.getCourseNumber().equals("3") && type.getName().equals("Практика преддипломная")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Студент 3 курса не может пройти преддипломную практику"));
        } else {
            if (student.getCourseNumber().equals("4") && type.getName().equals("Практика производственная")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Студент 4 курса уже прошел производственную практику"));
            } else {
                event.setStudent(student);
                event.setType(type);
                event.setCompany(new CompanyDAOService().getCompany(companyName));
                event.setDescription(description);
                eventDAOService.save(event);
                back();
            }
        }
    }

    private boolean assertStudentAndCourse(Student student, EventType type) {
        boolean rc = true;
        if (student.getCourseNumber().equals("3") && type.getName().equals("Практика преддипломная")) {
            rc = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Студент 3 курса не может пройти преддипломную практику"));
        }
        if (student.getCourseNumber().equals("4") && type.getName().equals("Практика производственная")) {
            rc = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Студент 4 курса уже прошел производственную практику"));
        }
        return rc;
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("eventMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        companies = null;
        companyDAOService.close();
    }
}