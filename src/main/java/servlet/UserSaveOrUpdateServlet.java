package servlet;

import model.User;
import store.MemStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Andrey
 * @date 25/10/2020
 * @version 1
 */

public class UserSaveOrUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        MemStore.instOf().saveUser(new User(Integer.parseInt(req.getParameter("id")), (req.getParameter("name")), (req.getParameter("e-mail")), (req.getParameter("password"))));
        this.doGet(req, resp);
    }

}
