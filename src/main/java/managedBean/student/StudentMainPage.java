package managedBean.student;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@AllArgsConstructor
@Data
@RequestScoped
@ManagedBean
public class StudentMainPage implements Serializable {

    public void goToAddStudent() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("createStudentPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}