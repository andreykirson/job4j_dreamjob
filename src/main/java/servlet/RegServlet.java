package servlet;

import model.User;
import store.PsqlStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Andrey
 * @date 01/11/2020
 */


public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        PsqlStore.instOf().saveUser(new User(0, (req.getParameter("Name")), req.getParameter("Email Address"), req.getParameter("Password")));
        System.out.println(req.getParameter("Name") + " " + req.getParameter("Email Address")+ " " + req.getParameter("Password"));
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
