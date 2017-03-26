package controller.profile.scientist;

import model.University;
import service.ScientistService;
import service.UniversityService;
import util.constants.AppConst;
import util.constants.SessionConst;
import util.constants.UrlConst;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main/scientist")
public class Scientist extends HttpServlet {

    private ScientistService serviceScientist;
    private UniversityService serviceUniversity;

    @Override
    public void init(ServletConfig config) throws ServletException {
        serviceScientist = (ScientistService) config.getServletContext().getAttribute(AppConst.SCIENTIST_SERVICE);
        serviceUniversity = (UniversityService) config.getServletContext().getAttribute(AppConst.UNIVERSITY_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String scientistEmail = req.getParameter(ScientistConst.BUTTON_SCIENTIST);
        model.Scientist currentScientist = (model.Scientist) req.getSession().getAttribute(SessionConst.EMAIL_KEY);
        if (scientistEmail == null || scientistEmail.equals(currentScientist.getEmail())) {
            resp.sendRedirect(UrlConst.WEB_INF_MAIN);
            return;
        }
        model.Scientist scientist = serviceScientist.get(scientistEmail);
        req.setAttribute(ScientistConst.SCIENTIST_KEY, serviceScientist.get(scientistEmail));
        req.setAttribute(ScientistConst.UNIVERSITIES_KEY, serviceUniversity.getAll(scientist.getId()));
        req.setAttribute(ScientistConst.SCIENTIST_FIRST_NAME_KEY,
                req.getParameter(ScientistConst.SCIENTIST_FIRST_NAME_KEY));
        req.setAttribute(ScientistConst.SCIENTIST_SECOND_NAME_KEY,
                req.getParameter(ScientistConst.SCIENTIST_SECOND_NAME_KEY));
        req.getRequestDispatcher(UrlConst.WEB_INF_MAIN_SCIENTIST).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
