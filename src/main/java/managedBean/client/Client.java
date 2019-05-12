package managedBean.client;

import lombok.Data;
import model.entity.Event;
import model.service.EventDAOService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ViewScoped
@ManagedBean
public class Client implements Serializable {

    private List<Event> events;
    private EventDAOService eventDAOService;

    @PostConstruct
    public void init() {
        events = new ArrayList<>();
        eventDAOService = new EventDAOService();
        events.addAll(eventDAOService.getEventsToCompany((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("company")));
    }

    @PreDestroy
    public void destroy() {
        events = null;
        eventDAOService.close();
    }
}