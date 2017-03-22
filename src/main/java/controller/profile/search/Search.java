package controller.profile.search;

import model.Scientist;
import security.ScientistValidator;
import service.ScientistService;
import util.Const;

import javax.servlet.ServletConfig;
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
@WebServlet("/main/search")
public class Search extends HttpServlet {

    private static final ScientistValidator validator = new ScientistValidator();
    private ScientistService serviceScientist;

    @Override
    public void init(ServletConfig config) throws ServletException {
        serviceScientist = (ScientistService) config.getServletContext().getAttribute(Const.SCIENTIST_SERVICE);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (validator.validateSearchByName(req)) {
            Set<Scientist> scientistSet = serviceScientist.
                    getAllByFullName(validator.getValidFirstName(), validator.getValidSecondName());
            req.setAttribute("scientistFirstName", validator.getValidFirstName());
            req.setAttribute("scientistSecondName", validator.getValidSecondName());
            req.setAttribute("scientistSet", scientistSet);
        }
        req.getRequestDispatcher("/WEB-INF/main/search/index.jsp").forward(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
