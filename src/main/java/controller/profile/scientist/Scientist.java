package controller.profile.scientist;

import model.University;
import service.ScientistService;
import service.UniversityService;
import util.Const;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created on 19.03.2017.
 */
@WebServlet("/main/scientist")
public class Scientist extends HttpServlet {

    private ScientistService serviceScientist;
    private UniversityService serviceUniversity;

    @Override
    public void init(ServletConfig config) throws ServletException {
        serviceScientist = (ScientistService) config.getServletContext().getAttribute(Const.SCIENTIST_SERVICE);
        serviceUniversity = (UniversityService) config.getServletContext().getAttribute(Const.UNIVERSITY_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String scientistEmail = req.getParameter("button_scientist");
        model.Scientist currentScientist = (model.Scientist) req.getSession().getAttribute(Const.EMAIL_KEY);
        if (scientistEmail != null && !scientistEmail.equals(currentScientist.getEmail())) {
            model.Scientist scientist = serviceScientist.get(scientistEmail, req.getLocale());
            req.setAttribute("scientist", scientist);
            List<University> universities = serviceUniversity.getAll(scientist.getId());
            req.setAttribute("universities", universities);

            req.setAttribute("sciFirstName", req.getParameter("sciFirstName"));
            req.setAttribute("sciSecondName", req.getParameter("sciSecondName"));
            req.getRequestDispatcher("/WEB-INF/main/scientist/index.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/main");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
