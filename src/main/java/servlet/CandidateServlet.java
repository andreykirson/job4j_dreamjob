package servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import model.Candidate;
import store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


public class CandidateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        try (BufferedReader reader = req.getReader()) {
            StringBuilder fullLine = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fullLine.append(line);
            }
            JsonElement jsonElement =  new JsonParser().parse(String.valueOf(fullLine));
            JsonArray jsonArray = jsonElement.getAsJsonArray();

           int candidateId = Integer.parseInt(jsonArray.get(0).getAsJsonObject().get("value").toString().replaceAll("\"", ""));
           String candidateName = jsonArray.get(1).getAsJsonObject().get("value").toString().replaceAll("\"", "");
           String candidatePhotoSource = jsonArray.get(2).getAsJsonObject().get("value").toString().replaceAll("\"", "");
           int candidateCityId = Integer.parseInt(jsonArray.get(3).getAsJsonObject().get("value").toString().replaceAll("\"", ""));
           Candidate candidate = new Candidate(candidateId, candidateName);
           candidate.setPhotoSrc(candidatePhotoSource);
           candidate.setCityId(candidateCityId);
           PsqlStore.instOf().saveCandidate(candidate);
           resp.sendRedirect(req.getContextPath() + "/candidates.do");

        }  catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }
}