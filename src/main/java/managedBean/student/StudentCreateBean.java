package managedBean.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ManagedBean
@RequestScoped
public class StudentCreateBean {

    private String name;
    private String surname;
    private String secondName;
    private String specialty;
    private String date;
    private String ticketNumber;
    private String phoneNumber;
    private boolean study;
    private Integer rating;
    private String description;

    @PostConstruct
    public void init() {
    }

    public void add() {
        goToMainPage();
    }

    public void back() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("studentMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goToMainPage() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("studentMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}