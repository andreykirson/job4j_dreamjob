package servlet;

import com.google.gson.Gson;
import model.City;
import store.PsqlStore;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * @author Andrey
 * @date 01/11/2020
 */

public class CityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Collection<City> cities = PsqlStore.instOf().findAllCities();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String json = new Gson().toJson(cities);
        writer.println(json);
        writer.flush();
        writer.close();
    }
}
