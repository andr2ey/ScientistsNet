package controller.profile.baseinfo;

import controller.registration.RegistrationConst;
import model.Scientist;
import org.apache.catalina.realm.RealmBase;
import org.apache.log4j.Logger;
import security.ScientistValidator;
import service.ScientistService;
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
import java.time.LocalDate;


@WebServlet("/main/baseinfo")
public class BaseInfoEditor extends HttpServlet {


    private static final Logger LOGGER = Logger.getLogger(BaseInfoEditor.class);

    private ScientistService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (ScientistService) config.getServletContext().getAttribute(AppConst.SCIENTIST_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScientistValidator validator = (ScientistValidator) req.getAttribute(BaseInfoConst.SCIENTIST_VALIDATOR_KEY);
        updateInfo(req, validator);
        setDobAndFieldOfScience(req);
        setYearLimits(req);
        req.getRequestDispatcher(UrlConst.WEB_INF_MAIN_BASEINFO).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void updateInfo(HttpServletRequest req, ScientistValidator validator) {
        Scientist scientistOld = (Scientist) req.getSession().getAttribute(SessionConst.EMAIL_KEY);
        String passwordOld = validator.getValidPassword();
        String digestedOldPassword = RealmBase.Digest(passwordOld, AppConst.MD5, AppConst.UTF_8);
        if (!service.confirmPassword(scientistOld.getId(), digestedOldPassword)) {
            req.setAttribute(AppConst.CONFIRM_PASSWORD_ERROR, true);
            req.setAttribute(AppConst.FAIL, true);
            LOGGER.info(String.format("Confirmation of password failed (%s)", scientistOld));
            return;
        }
        String passwordNew = validator.getValidNewPassword();
        passwordNew = passwordNew == null || passwordNew.isEmpty() ? passwordOld : passwordNew;
        Scientist scientistNew = create(validator, scientistOld, passwordNew);
        if (!service.updateInfo(scientistNew)) {
            req.setAttribute(AppConst.FAIL, true);
            return;
        }
        req.setAttribute(AppConst.SUCCESS, true);
        clearPasswordTrack(req, scientistNew);
        req.getSession().setAttribute(SessionConst.EMAIL_KEY, scientistNew);

    }

    private void clearPasswordTrack(HttpServletRequest req, Scientist scientist) {
        scientist.setPassword(null);
        req.setAttribute(BaseInfoConst.SCIENTIST_VALIDATOR_KEY, null);
    }

    private Scientist create(ScientistValidator validator, Scientist scientistOld, String passwordNew) {
        return new Scientist().builder()
                .setFirstName(validator.getValidFirstName())
                .setSecondName(validator.getValidSecondName())
                .setMiddleName(validator.getValidMiddleName())
                .setGender(validator.getValidGender())
                .setDob(validator.getValidDate())
                .setEmail(scientistOld.getEmail())
                .setPassword(RealmBase.Digest(passwordNew, AppConst.MD5, AppConst.UTF_8))
                .setFieldOfScience(validator.getValidFieldOfScience())
                .setId(scientistOld.getId())
                .build();
    }


    @SuppressWarnings("Duplicates")
    private void setDobAndFieldOfScience(HttpServletRequest req) {
        Scientist scientist = (Scientist) req.getSession().getAttribute(SessionConst.EMAIL_KEY);
        LocalDate localDate = scientist.getDob();
        req.setAttribute(BaseInfoConst.MONTH_VALUE, localDate.getMonthValue());
        req.setAttribute(BaseInfoConst.YEAR_VALUE, localDate.getYear());
        req.setAttribute(BaseInfoConst.DAY_VALUE, localDate.getDayOfMonth());
        req.setAttribute("m" + localDate.getMonthValue(), BaseInfoConst.SELECTED);
        req.setAttribute("f" + scientist.getFieldOfScience().ordinal(), BaseInfoConst.SELECTED);
    }

    private void setYearLimits(HttpServletRequest req) {
        LocalDate localDate = LocalDate.now();
        req.setAttribute(RegistrationConst.MAX_YEAR_KEY, (localDate.getYear() - RegistrationConst.BOTTOM_EDGE_OF_AGE));
        req.setAttribute(RegistrationConst.MIN_YEAR_KEY, (localDate.getYear() - RegistrationConst.TOP_EDGE_OF_AGE));
    }
}
