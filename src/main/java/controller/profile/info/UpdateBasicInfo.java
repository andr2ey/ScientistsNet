package controller.profile.info;

import dao.ScientistDao;
import model.Gender;
import model.Scientist;
import security.ScientistValidator;
import service.ScientistService;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 15.03.2017.
 */
@WebServlet("/info_save")
public class UpdateBasicInfo extends HttpServlet {

    private static final ScientistValidator VALIDATOR = new ScientistValidator();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (VALIDATOR.validateBaseInfoFields(req)) {
            ScientistService service = (ScientistService) req.getServletContext().getAttribute(Const.SCIENTIST_SERVICE);
            Scientist scientistOld = (Scientist)req.getSession().getAttribute(Const.EMAIL_KEY);
            //verifies confirm password
            if (!service.verifyPassword(scientistOld.getEmail(), req.getAttribute("passwordNew"))) {
                req.setAttribute(Const.CONFIRM_PASSWORD_ERROR, "Confirmation of password is invalid");
                req.getRequestDispatcher("WEB-INF/main/baseinfo/index.jsp").forward(req, resp);
            }
            Scientist scientistNew = new Scientist().builder().setFirstName()
            if (service.update(scientistOld)) {

            }
        }
        req.getRequestDispatcher("WEB-INF/main/baseinfo/index.jsp").forward(req, resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
