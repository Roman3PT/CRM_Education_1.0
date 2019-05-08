package managedBean.company;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Company;
import model.service.CompanyDAOService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@NoArgsConstructor
@Data
@ManagedBean
@RequestScoped
public class CompanyCreateBean implements Serializable {

    private String name;
    private String region;
    private String city;
    private String street;
    private String numberHouse;
    private boolean isBool;
    private String description;
    private Company company;
    private CompanyDAOService companyDAOService;

    @PostConstruct
    public void init() {
        company = new Company();
        companyDAOService = new CompanyDAOService();
    }

    public void add() {
        company.setName(name);
        company.setAddress(region + " " + city + " " + street + " " + numberHouse);
        company.setBool(isBool);
        company.setDescription(description);
        companyDAOService.save(company);
        back();
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("companyMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}