package controller.registration;

import controller.profile.education.EducationConst;
import model.Scientist;
import model.University;
import org.apache.catalina.realm.RealmBase;
import security.ScientistValidator;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates scientist in db and login his
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {

    private ScientistService scientistService;
    private UniversityService universityService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        scientistService = (ScientistService) config.getServletContext().getAttribute(AppConst.SCIENTIST_SERVICE);
        universityService = (UniversityService) config.getServletContext().getAttribute(AppConst.UNIVERSITY_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScientistValidator validator = (ScientistValidator) req.getAttribute(RegistrationConst.SCIENTIST_VALIDATOR_KEY);
        Scientist scientist = create(validator);
        if (!scientistService.create(scientist)) {
            fillVerifiedFields(req, scientist);
            setYearRestriction(req); //TODO add it to view
            req.getRequestDispatcher(UrlConst.WEB_INF_REGISTRATION).forward(req, resp);
            return;
        }
        login(req, scientist, validator);
        resp.sendRedirect(UrlConst.MAIN);
    }

    private Scientist create(ScientistValidator validator) {
        return new Scientist().builder().
                setEmail(validator.getValidEmail()).
                setPassword(RealmBase.Digest(validator.getValidPassword(), AppConst.MD5, AppConst.UTF_8)).
                setFirstName(validator.getValidFirstName()).setSecondName(validator.getValidSecondName()).
                setMiddleName(validator.getValidMiddleName()).setDob(validator.getValidDate()).
                setGender(validator.getValidGender()).setFieldOfScience(validator.getValidFieldOfScience()).build();
    }

    private void login(HttpServletRequest req, Scientist scientist, ScientistValidator validator) throws ServletException {
        HttpSession session = req.getSession();
        session.setAttribute(SessionConst.EMAIL_KEY, scientist);
        loadUniversities(session, scientist.getId());
        req.login(scientist.getEmail(), validator.getValidPassword());
        clearPasswordTrack(req, scientist);
    }

    private void clearPasswordTrack(HttpServletRequest req, Scientist scientist) {
        scientist.setPassword(null);
        req.setAttribute(RegistrationConst.SCIENTIST_VALIDATOR_KEY, null);
    }

    @SuppressWarnings("Duplicates")
    private void loadUniversities(HttpSession session, int scientistId) {
        //buffer for unmodified universities
        List<University> unmodifiedUniversities = new ArrayList<>(EducationConst.MAX_UNIVERSITIES);
        session.setAttribute(SessionConst.UNMODIFIED_UNIVERSITIES_KEY, unmodifiedUniversities);
        session.setAttribute(SessionConst.UNIVERSITIES_CHANGED_KEY, null);
        for (int i = 0; i < EducationConst.MAX_UNIVERSITIES; i++)
            unmodifiedUniversities.add(i, null);

        List<University> universities = universityService.getAll(scientistId);
        session.setAttribute(SessionConst.UNIVERSITIES_KEY, universities);
    }

    private void setYearRestriction(HttpServletRequest req) {
        LocalDate localDate = LocalDate.now();
        req.setAttribute(RegistrationConst.MAX_YEAR_KEY, (localDate.getYear() - RegistrationConst.BOTTOM_EDGE_OF_AGE));
        req.setAttribute(RegistrationConst.MIN_YEAR_KEY, (localDate.getYear() - RegistrationConst.TOP_EDGE_OF_AGE));
    }

    private void fillVerifiedFields(HttpServletRequest req, Scientist validUser) {
        req.setAttribute(RegistrationConst.EMAIL_EXIST_KEY, true);
        req.setAttribute(RegistrationConst.FIRST_NAME_KEY, validUser.getFirstName());
        req.setAttribute(RegistrationConst.SECOND_NAME_KEY, validUser.getSecondName());
        LocalDate localDate = validUser.getDob();
        req.setAttribute(RegistrationConst.MONTH_VALUE_KEY, localDate.getMonthValue());
        req.setAttribute(RegistrationConst.YEAR_VALUE_KEY, localDate.getYear());
        req.setAttribute(RegistrationConst.DAY_VALUE_KEY, localDate.getDayOfMonth());
        req.setAttribute("m" + localDate.getMonthValue(), RegistrationConst.SELECTED);
        req.setAttribute("f" + validUser.getFieldOfScience().ordinal(), RegistrationConst.SELECTED);
    }
}
