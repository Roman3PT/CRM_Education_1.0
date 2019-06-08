package managedBean.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.entity.Company;
import model.entity.Event;
import model.service.CompanyDAOService;
import model.service.EventDAOService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getEvents")
public class CRMMobileService extends HttpServlet {

    private Company company;
    private EventDAOService eventDAOService;
    private CompanyDAOService companyDAOService;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        eventDAOService = new EventDAOService();
        companyDAOService = new CompanyDAOService();
        company = new Company();
        List<Event> events = new ArrayList<>();
        company = companyDAOService.getCompany(login, password);
        events.addAll(eventDAOService.getEventsToCompany(company.getName()));
        new ObjectMapper().writeValue(new File("event.json"), events);
        response.setContentType("application/json;charset=UTF-8");
        String jsonString = new ObjectMapper().writeValueAsString(events);
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
    }

    private String getJsonEventToCompany(List<Event> events) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = gsonBuilder.create();
        return gson.toJson(events);
    }
}
