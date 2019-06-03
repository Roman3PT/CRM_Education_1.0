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
public class EventUpdatePage implements Serializable {

    private String eventTypeName;
    private String ticketNumber;
    private String companyName;
    private List<Company> companies;
    private String description;

    private Event selectedEvent;
    private EventDAOService eventDAOService;
    private CompanyDAOService companyDAOService;

    @PostConstruct
    public void init() {
        selectedEvent = (Event) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectedEvent");
        eventDAOService = new EventDAOService();
        companyDAOService = new CompanyDAOService();
        eventTypeName = selectedEvent.getType().getName();
        ticketNumber = selectedEvent.getStudent().getTicketNumber();
        companyName = selectedEvent.getCompany().getName();
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
        selectedEvent.setType(new EventTypeDAOService().getEventType(eventTypeName));
        selectedEvent.setStudent(new StudentDAOService().getStudent(ticketNumber));
        selectedEvent.setCompany(companyDAOService.getCompany(companyName));
        selectedEvent.setDescription(description);
        eventDAOService.update(selectedEvent);
        back();
    }

    @PreDestroy
    public void destroy() {
        companies = null;
        eventDAOService.close();
        companyDAOService.close();
    }
}