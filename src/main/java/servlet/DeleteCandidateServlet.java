package servlet;

import model.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import store.PsqlStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class DeleteCandidateServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteCandidateServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Candidate candidate = PsqlStore.instOf().findCandidateById(Integer.parseInt(req.getParameter("id")));
        try {
            Files.deleteIfExists(Paths.get(candidate.getPhotoSrc()));
        } catch (NoSuchFileException e) {
            LOG.debug("No such file/directory exists");
        } catch (DirectoryNotEmptyException e) {
            LOG.debug("Directory is not empty.");
        } catch (IOException e) {
            System.out.println("Invalid permissions.");
            LOG.debug("Invalid permissions.");
        }
        LOG.debug("Deletion successful.");
        PsqlStore.instOf().deleteCandidate(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }
}
