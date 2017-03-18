package controller.profile.info;

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
import java.time.LocalDate;

/**
 * Created on 15.03.2017.
 */
@WebServlet("/info_save")
public class UpdateBasicInfo extends HttpServlet {

    private static final ScientistValidator VALIDATOR = new ScientistValidator();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (VALIDATOR.validateBaseInfoFields(req)) {
            ScientistService service = (ScientistService) req.getServletContext().getAttribute(Const.SCIENTIST_SERVICE);
            Scientist scientistOld = (Scientist)req.getSession().getAttribute(Const.EMAIL_KEY);
            if (!service.confirmPassword(scientistOld.getId(), req.getParameter("passwordOld"))) {
                req.setAttribute(Const.CONFIRM_PASSWORD_ERROR, "Confirmation of password is invalid");
                System.err.println("Confirmation of password is invalid");
                req.setAttribute("fail", "fail");
            } else {
                Scientist scientistNew = new Scientist().builder()
                        .setFirstName(VALIDATOR.getValidFirstName())
                        .setSecondName(VALIDATOR.getValidSecondName())
                        .setMiddleName(VALIDATOR.getValidMiddleName())
                        .setGender(VALIDATOR.getValidGender())
                        .setDob(VALIDATOR.getValidDate())
                        .setEmail(VALIDATOR.getValidNewEmail())
                        .setPassword(VALIDATOR.getValidNewPassword()).build();
                scientistNew.setId(scientistOld.getId());
                String emailNew = scientistNew.getEmail();
                scientistNew.setEmail(emailNew == null || emailNew.isEmpty() ?
                        scientistOld.getEmail() : emailNew);
                String passwordNew = scientistNew.getPassword();
                scientistNew.setPassword(passwordNew == null || passwordNew.isEmpty() ?
                        req.getParameter("passwordOld") : passwordNew);

                System.err.println();
                System.err.println(scientistNew);
                System.err.println();
                if (service.updateInfo(scientistNew, scientistOld.getEmail())) {
                    req.setAttribute("success", "success");
                    req.getSession().setAttribute(Const.EMAIL_KEY, scientistNew);
                } else {
                    req.setAttribute("exist_email", "Such email already exist");
                    req.setAttribute("fail", "fail");
                }
            }
        }
        dobSettings(req);
        req.getRequestDispatcher("WEB-INF/main/baseinfo/index.jsp").forward(req, resp);
    }

    private void dobSettings(HttpServletRequest req) {
        LocalDate localDate = ((Scientist)req.getSession().getAttribute(Const.EMAIL_KEY)).getDob();
        req.setAttribute("month", localDate.getMonthValue());
        req.setAttribute("year", localDate.getYear());
        req.setAttribute("day", localDate.getDayOfMonth());
        req.setAttribute("m"+localDate.getMonthValue(), "selected");
        LocalDate localDateNow = LocalDate.now();
        req.setAttribute(Const.MAX_YEAR_KEY, (localDateNow.getYear() - Const.BOTTOM_EDGE_OF_AGE) );
        req.setAttribute(Const.MIN_YEAR_KEY, (localDateNow.getYear() - Const.TOP_EDGE_OF_AGE));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
