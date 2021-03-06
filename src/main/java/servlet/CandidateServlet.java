package servlet;

import model.Candidate;
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


public class CandidateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Candidate candidate = new Candidate(Integer.parseInt(req.getParameter("id")), req.getParameter("candidate-name"));
        candidate.setCityId(Integer.parseInt(req.getParameter("select")));
        candidate.setPhotoSrc(req.getParameter("name-photo"));
        PsqlStore.instOf().saveCandidate(candidate);
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }
}