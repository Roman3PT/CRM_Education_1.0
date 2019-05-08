package managedBean.event;

import custom_enum.TreeItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Event;
import model.service.EventDAOService;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ManagedBean
@ViewScoped
public class mainPage implements Serializable {

    private TreeNode root;
    private TreeNode selectionNode;
    private List<Event> events;
    private Long number;

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("CRM_Education", null);
        TreeNode root1 = new DefaultTreeNode("Студент", root);
        TreeNode root2 = new DefaultTreeNode("Компания", root);
        TreeNode root3 = new DefaultTreeNode("Событие", root);
        TreeNode root4 = new DefaultTreeNode("Контакты", root);
        root1.getChildren().add(new DefaultTreeNode("Меню студентов"));
        root2.getChildren().add(new DefaultTreeNode("Меню компаний"));
        root3.getChildren().add(new DefaultTreeNode("Меню событий"));
        root4.getChildren().add(new DefaultTreeNode("Список контактов"));

        events = new ArrayList<>();
        events.addAll(new EventDAOService().getEvents());
    }

    public void goToPage() {
        try {
            if (selectionNode.toString().equals(TreeItems.ITEM1.getName()))
                FacesContext.getCurrentInstance().getExternalContext().redirect("eventMainPage.xhtml");
            else if (selectionNode.toString().equals(TreeItems.ITEM2.getName()))
                FacesContext.getCurrentInstance().getExternalContext().redirect("studentMainPage.xhtml");
            else if (selectionNode.toString().equals(TreeItems.ITEM3.getName()))
                FacesContext.getCurrentInstance().getExternalContext().redirect("companyMainPage.xhtml");
            else if (selectionNode.toString().equals(TreeItems.ITEM4.getName()))
                FacesContext.getCurrentInstance().getExternalContext().redirect("contactMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove() {
        new EventDAOService().deleteEvent(number);
        goToMainPageEvent();
    }

    public void goToAddEvent() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("eventCreatePage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToUpdateEvent() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("eventUpdatePage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("authorizationPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goToMainPageEvent() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("eventMainPage.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}