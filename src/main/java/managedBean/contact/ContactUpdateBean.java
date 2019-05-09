package managedBean.contact;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Company;
import model.entity.Contact;
import model.service.CompanyDAOService;
import model.service.ContactDAOService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@ManagedBean
@ViewScoped
public class ContactUpdateBean implements Serializable {

    private String companyName;
    private String phoneNumber;
    private String description;
    private Contact contact;
    private List<Company> companies;

    private ContactDAOService contactDAOService;
    private CompanyDAOService companyDAOService;

    @PostConstruct
    public void init() {
        Long id = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idContact");
        companyDAOService = new CompanyDAOService();
        contactDAOService = new ContactDAOService();
        contact = contactDAOService.getContact(id);
        companies = new ArrayList<>();
        companies.addAll(companyDAOService.getListCompanyToContact());
        companies.add(contact.getCompany());
        companyName = contact.getCompany().getName();
        phoneNumber = contact.getPhoneNumber();
        description = contact.getDescription();
    }

    public void update() {
        contact.setCompany(companyDAOService.getCompany(companyName));
        contact.setPhoneNumber(phoneNumber);
        contact.setDescription(description);
        contactDAOService.update(contact);
        back();
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("contactMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {

    }
}