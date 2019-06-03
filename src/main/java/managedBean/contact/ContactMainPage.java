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
    private Contact selectedContact;
    private ContactDAOService contactDAOService;

    @PostConstruct
    public void init() {
        selectedContact = new Contact();
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
        if (Objects.isNull(selectedContact))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Введите корректный номер"));
        else {
            contactDAOService.remove(selectedContact);
            refresh();
        }
    }

    public void update() {
        if (Objects.isNull(selectedContact))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Введите корректный номер"));
        else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedContact", selectedContact);
                FacesContext.getCurrentInstance().getExternalContext().redirect("contactUpdatePage.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
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