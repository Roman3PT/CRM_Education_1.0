package managedBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Company;
import model.hibernateUtil.HibernateUtil;
import model.service.CompanyDAOService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@RequestScoped
@ManagedBean
public class Authorization implements Serializable {

    private String login;
    private String password;

    private String defaultLogin = "admin";
    private String defaultPassword = "admin";

    private CompanyDAOService companyDAOService;
    private Company company;

    @PostConstruct
    public void init() {
        HibernateUtil.getSessionFactory();
        companyDAOService = new CompanyDAOService();
        company = new Company();
    }

    public void signIn() {
        if (login.equals(defaultLogin) && password.equals(defaultPassword))
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("eventMainPage.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        else {
            company = companyDAOService.getCompany(login, password);
            if (Objects.isNull(company))
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка", "Введите существующий логин и пароль"));
            else
                try {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("company", company.getName());
                    FacesContext.getCurrentInstance().getExternalContext().redirect("companyListStudentsPage.xhtml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}