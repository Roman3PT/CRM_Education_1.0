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
public class CompanyBean implements Serializable {

    public void goToAdd() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("createCompanyPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToUpdate() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("updateCompanyPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search() {

    }

    public void remove() {

    }
}