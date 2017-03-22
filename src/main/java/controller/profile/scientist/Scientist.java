package controller.profile.scientist;

import model.University;
import service.ScientistService;
import service.UniversityService;
import util.Const;

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
@WebServlet("/scientist")
public class Scientist extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScientistService serviceScientist =
                (ScientistService) req.getServletContext().getAttribute(Const.SCIENTIST_SERVICE);
        String scientistEmail = req.getParameter("button_scientist");
        if (scientistEmail != null) {
            model.Scientist scientist = serviceScientist.get(scientistEmail, req.getLocale());
            req.setAttribute("scientist", scientist);

            UniversityService serviceUniversity =
                    (UniversityService) req.getServletContext().getAttribute(Const.UNIVERSITY_SERVICE);
            List<University> universities = serviceUniversity.getAll(scientist.getId());
            req.setAttribute("universities", universities);
            req.getRequestDispatcher("WEB-INF/main/scientist/index.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/main");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
