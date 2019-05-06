package managedBean.company;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String name;
    private String region;
    private String city;
    private String street;
    private String numberHouse;
    private String description;

    public void update() {
        goToCompanyMainPage();
    }

    private void goToCompanyMainPage() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("companyMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("companyMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}