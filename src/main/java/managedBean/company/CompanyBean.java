package managedBean.company;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Company;
import model.service.CompanyDAOService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Data
@ManagedBean
@ViewScoped
public class CompanyBean implements Serializable {

    private List<Company> companies;
    private Company selectedCompany;
    private CompanyDAOService companyDAOService;

    @PostConstruct
    public void init() {
        selectedCompany = new Company();
        companies = new ArrayList<>();
        companyDAOService = new CompanyDAOService();
        companies.addAll(companyDAOService.getListCompany());
    }

    public void goToAdd() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("createCompanyPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToUpdate() {
        if (Objects.isNull(selectedCompany))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Выберите компанию"));
        else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedCompany", selectedCompany);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("updateCompanyPage.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshPage() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("companyMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        if (Objects.isNull(selectedCompany))
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", "Выберите компанию"));
        else {
            companyDAOService.remove(selectedCompany);
            refreshPage();
        }
    }

    @PreDestroy
    public void destroy() {
        companies = null;
        companyDAOService.close();
    }
}