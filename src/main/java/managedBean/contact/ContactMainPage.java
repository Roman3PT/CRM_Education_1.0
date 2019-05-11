package managedBean.contact;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Contact;
import model.service.ContactDAOService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        if (Objects.isNull(number))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Введите корректный номер"));
        else {
            if (!contactDAOService.remove(number))
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Введите существующий контакт"));
            else
                refresh();
        }
    }

    public void update() {
        if (Objects.isNull(number))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Введите корректный номер"));
        else {
            if (Objects.isNull(contactDAOService.getContact(number)))
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Введите корректный номер"));
            else {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idContact", number);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("contactUpdatePage.xhtml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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