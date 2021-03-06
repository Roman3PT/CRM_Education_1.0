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
public class CompanyUpdateBean implements Serializable {

    private String region;
    private String city;
    private String street;
    private String numberHouse;
    private Company company;
    private CompanyDAOService companyDAOService;

    @PostConstruct
    public void init() {
        companyDAOService = new CompanyDAOService();
        company = (Company) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedCompany");
        splitCompany(company.getAddress());
    }

    public void update() {
        company.setAddress(region + " обл., г. " + city + " ул. " + street + " д " + numberHouse);
        companyDAOService.update(company);
        back();
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("companyMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void splitCompany(String address) {
        String[] _address = address.split(" ");
        region = _address[0];
        city = _address[3];
        street = _address[5];
        numberHouse = _address[7];
    }
}