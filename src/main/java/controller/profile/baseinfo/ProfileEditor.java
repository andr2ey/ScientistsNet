package controller.profile.baseinfo;

import model.Scientist;
import org.apache.catalina.realm.RealmBase;
import org.apache.log4j.Logger;
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
import java.time.LocalDate;


@WebServlet("/main/baseinfo")
public class ProfileEditor extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProfileEditor.class);

    private ScientistService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (ScientistService) config.getServletContext().getAttribute(Const.SCIENTIST_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScientistValidator validator = new ScientistValidator();
        if (req.getParameter("button_update_scientist") != null) {
            if (validator.validateBaseInfoFields(req)) {
                updateInfo(req, validator);
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

    private void updateInfo(HttpServletRequest req, ScientistValidator validator) {
        Scientist scientistOld = (Scientist)req.getSession().getAttribute(Const.EMAIL_KEY);
        String digestedOldPassword = RealmBase.Digest(validator.getValidPassword(), "MD5", "utf-8");
        if (!service.confirmPassword(scientistOld.getId(), digestedOldPassword)) {
            req.setAttribute(Const.CONFIRM_PASSWORD_ERROR, "Confirmation of password failed");
            req.setAttribute(Const.FAIL, Const.FAIL);
            LOGGER.info("Confirmation of password failed");
        } else {
            String passwordNew = validator.getValidNewPassword();
            passwordNew = passwordNew == null || passwordNew.isEmpty() ? validator.getValidPassword() : passwordNew;
            Scientist scientistNew = new Scientist().builder()
                    .setFirstName(validator.getValidFirstName())
                    .setSecondName(validator.getValidSecondName())
                    .setMiddleName(validator.getValidMiddleName())
                    .setGender(validator.getValidGender())
                    .setDob(validator.getValidDate())
                    .setEmail(validator.getValidNewEmail())
                    .setPassword(RealmBase.Digest(passwordNew, "MD5", "utf-8"))
                    .setFieldOfScience(validator.getValidFieldOfScience())
                    .setId(scientistOld.getId())
                    .build();
            if (service.updateInfo(scientistNew)) {
                req.setAttribute(Const.SUCCESS, Const.SUCCESS);
                scientistNew.setPassword(null);
                scientistNew.setEmail(scientistOld.getEmail());
                req.getSession().setAttribute(Const.EMAIL_KEY, scientistNew);
            } else {
                req.setAttribute(Const.FAIL, Const.FAIL);
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
