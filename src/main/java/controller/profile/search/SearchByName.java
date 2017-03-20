package controller.profile.search;

import model.Scientist;
import model.University;
import security.ScientistValidator;
import service.ScientistService;
import service.UniversityService;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created on 19.03.2017.
 */
@WebServlet("/search_by_name")
public class SearchByName extends HttpServlet {
    private static final ScientistValidator VALIDATOR = new ScientistValidator();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScientistService serviceScientist =
                (ScientistService) req.getServletContext().getAttribute(Const.SCIENTIST_SERVICE);
        if (VALIDATOR.validateSearchByName(req)) {
            Set<Scientist> scientistSet = serviceScientist.
                    getAllByFullName(VALIDATOR.getValidFirstName(), VALIDATOR.getValidSecondName());
            req.setAttribute("scientistSet", scientistSet);
        }
        req.getRequestDispatcher("WEB-INF/main/search/index.jsp").forward(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
