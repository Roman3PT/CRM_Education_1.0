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
public class ContactAddPage implements Serializable {

    private List<Company> companies;
    private String companyName;
    private String phoneNumber;
    private String description;
    private Contact contact;

    private CompanyDAOService companyDAOService;
    private ContactDAOService contactDAOService;

    @PostConstruct
    public void init() {
        contact = new Contact();
        companies = new ArrayList<>();
        companyDAOService = new CompanyDAOService();
        contactDAOService = new ContactDAOService();
        companies.addAll(companyDAOService.getListCompanyToContact());
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("contactMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add() {
        contact.setCompany(companyDAOService.getCompany(companyName));
        contact.setPhoneNumber(phoneNumber);
        contact.setDescription(description);
        contactDAOService.save(contact);
        back();
    }

    @PreDestroy
    public void destroy() {
        companies = null;
        companyDAOService.close();
        contactDAOService.close();
    }
}