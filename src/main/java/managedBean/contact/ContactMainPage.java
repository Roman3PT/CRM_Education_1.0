package managedBean.contact;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Contact;
import model.service.ContactDAOService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@ManagedBean
@RequestScoped
public class ContactMainPage implements Serializable {

    private List<Contact> contacts;
    private Long number;

    private ContactDAOService contactDAOService;

    @PostConstruct
    public void init() {
        contactDAOService = new ContactDAOService();
        contacts = new ArrayList<>();
        contacts.addAll(contactDAOService.getContacts());
    }

    public void add() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("contactAddPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        contactDAOService.remove(number);
        refresh();
    }

    public void update() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idContact", number);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("contactUpdatePage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refresh() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("contactMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}