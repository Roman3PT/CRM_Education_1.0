package managedBean.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ManagedBean
@SessionScoped
public class EventCreatePage implements Serializable {

    private String eventType;
    private String student;
    private String company;
    private String description;

    @PostConstruct
    public void init() {
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("eventMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add() {
        back();
    }
}