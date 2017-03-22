package controller.profile.baseinfo;

import model.Scientist;
import security.ScientistValidator;
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
import java.time.LocalDate;


@WebServlet("/main/baseinfo")
public class ProfileEditor extends HttpServlet {

    private final ScientistValidator validator = new ScientistValidator();

    private ScientistService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (ScientistService) config.getServletContext().getAttribute(Const.SCIENTIST_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("button_update_scientist") != null) {
            if (validator.validateBaseInfoFields(req)) {
                updateInfo(req);
            } else {
                req.getRequestDispatcher("/WEB-INF/main/baseinfo/index.jsp").forward(req, resp);
                return;
            }
        }
        setDobAndFieldOfScience(req);
        setYearLimits(req);
        req.getRequestDispatcher("/WEB-INF/main/baseinfo/index.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void updateInfo(HttpServletRequest req) {
        Scientist scientistOld = (Scientist)req.getSession().getAttribute(Const.EMAIL_KEY);
        if (!service.confirmPassword(scientistOld.getId(), validator.getValidPassword())) {
            req.setAttribute(Const.CONFIRM_PASSWORD_ERROR, "Confirmation of password is invalid");
            req.setAttribute("fail", "fail");
        } else {
            String passwordNew = validator.getValidNewPassword();
            passwordNew = passwordNew == null || passwordNew.isEmpty() ?
                    validator.getValidPassword() : passwordNew;
            Scientist scientistNew = new Scientist().builder()
                    .setFirstName(validator.getValidFirstName())
                    .setSecondName(validator.getValidSecondName())
                    .setMiddleName(validator.getValidMiddleName())
                    .setGender(validator.getValidGender())
                    .setDob(validator.getValidDate())
                    .setEmail(validator.getValidNewEmail())
                    .setPassword(passwordNew)
                    .setFieldOfScience(validator.getValidFieldOfScience())
                    .setId(scientistOld.getId())
                    .build();
            if (service.updateInfo(scientistNew, req.getLocale())) {
                req.setAttribute("success", "success");
                scientistNew.setPassword(null);
                scientistNew.setEmail(scientistOld.getEmail());
                req.getSession().setAttribute(Const.EMAIL_KEY, scientistNew);
            } else {
                req.setAttribute("fail", "fail");
            }
        }
    }

    private void setDobAndFieldOfScience(HttpServletRequest req) {
        Scientist scientist = (Scientist)req.getSession().getAttribute(Const.EMAIL_KEY);
        LocalDate localDate = scientist.getDob();
        req.setAttribute("month", localDate.getMonthValue());
        req.setAttribute("year", localDate.getYear());
        req.setAttribute("day", localDate.getDayOfMonth());
        req.setAttribute("m"+localDate.getMonthValue(), "selected");
        req.setAttribute("f"+scientist.getFieldOfScience().ordinal(), "selected");
    }

    private void setYearLimits(HttpServletRequest req) {
        LocalDate localDate = LocalDate.now();
        req.setAttribute(Const.MAX_YEAR_KEY, (localDate.getYear() - Const.BOTTOM_EDGE_OF_AGE) );
        req.setAttribute(Const.MIN_YEAR_KEY, (localDate.getYear() - Const.TOP_EDGE_OF_AGE));
    }
}
