package managedBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@RequestScoped
@ManagedBean
public class Authorization implements Serializable {

    private String login;
    private String password;

    public void signIn() {
        if (login.equals("admin") && password.equals("admin"))
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("eventMainPage.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}