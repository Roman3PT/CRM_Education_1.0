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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ManagedBean
@SessionScoped
public class EventUpdatePage implements Serializable {

    private String eventTypeName;
    private String ticketNumber;
    private String companyName;
    private List<Company> companies;
    private String description;

    private Event event;
    private EventDAOService eventDAOService;
    private CompanyDAOService companyDAOService;

    @PostConstruct
    public void init() {
        Long id = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
        eventDAOService = new EventDAOService();
        companyDAOService = new CompanyDAOService();
        event = eventDAOService.getEvent(id);
        eventTypeName = event.getType().getName();
        ticketNumber = event.getStudent().getTicketNumber();
        companyName = event.getCompany().getName();
        companies = new ArrayList<>();
        companies.addAll(companyDAOService.getListCompany());
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("eventMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        event.setType(new EventTypeDAOService().getEventType(eventTypeName));
        event.setStudent(new StudentDAOService().getStudent(ticketNumber));
        event.setCompany(companyDAOService.getCompany(companyName));
        event.setDescription(description);
        eventDAOService.update(event);
        back();
    }

    @PreDestroy
    public void destroy() {
        companies = null;
        eventDAOService.close();
        companyDAOService.close();
    }
}