package managedBean.serviceJson;

import lombok.Data;
import model.entity.Event;
import model.service.EventDAOService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ManagedBean
@ViewScoped
public class JsonService implements Serializable {

    private EventDAOService eventDAOService;
    private List<Event> events;

    @PostConstruct
    public void init() {
        eventDAOService = new EventDAOService();
        events = new ArrayList<>();
        events.addAll(eventDAOService.getEvents());
    }

    public void getJson() {
        System.out.println("Здарова братан)");
    }
}
