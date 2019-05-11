package managedBean.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Company;
import model.entity.Event;
import model.service.CompanyDAOService;
import model.service.EventDAOService;
import model.service.EventTypeDAOService;
import model.service.StudentDAOService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
        eventTypeName = "Практика";
        companyDAOService = new CompanyDAOService();
        eventDAOService = new EventDAOService();
        companies = new ArrayList<>();
        companies.addAll(companyDAOService.getListCompany());
        event = new Event();
    }

    public void add() {
        event.setStudent(new StudentDAOService().getStudent(ticketNumber));
        event.setType(new EventTypeDAOService().getEventType(eventTypeName));
        event.setCompany(new CompanyDAOService().getCompany(companyName));
        event.setDescription(description);
        eventDAOService.save(event);
        back();
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