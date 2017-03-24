package controller.registration;

import model.Scientist;
import model.University;
import org.apache.catalina.realm.RealmBase;
import security.ScientistValidator;
import service.ScientistService;
import service.UniversityService;
import util.Const;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@WebServlet("/registration")
public class SignUp extends HttpServlet {

    private ScientistService scientistService;
    private UniversityService universityService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        scientistService = (ScientistService) config.getServletContext().getAttribute(Const.SCIENTIST_SERVICE);
        universityService = (UniversityService) config.getServletContext().getAttribute(Const.UNIVERSITY_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ScientistValidator validator = new ScientistValidator();
        if (validator.validateRegistrationFields(req)) {
            Scientist validUser = new Scientist().builder().
                    setEmail(validator.getValidEmail()).
                    setPassword(RealmBase.Digest(validator.getValidPassword(), "MD5", "utf-8")).
                    setFirstName(validator.getValidFirstName()).setSecondName(validator.getValidSecondName()).
                    setMiddleName(validator.getValidMiddleName()).setDob(validator.getValidDate()).
                    setGender(validator.getValidGender()).setFieldOfScience(validator.getValidFieldOfScience()).build();
            req.setAttribute(Const.VALID_USER_KEY, validUser);
            req.setAttribute(Const.CLEARTEXT_PASSWORD, validator.getValidPassword());
            if (scientistService.create(validUser) == 0) {
                setAttributes(req, validUser);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute(Const.EMAIL_KEY, validUser);
                loadUniversities(session, validUser.getId());
                req.setAttribute(Const.CREATED_USER_KEY, validUser);
            }
        }
        if (req.getAttribute(Const.VALID_USER_KEY) == null || req.getAttribute(Const.CREATED_USER_KEY) == null) {
            setYearsLimits(req);
            req.getRequestDispatcher(Const.REGISTRATION_PAGE_FULL_PATH).forward(req, resp);
        } else {
            Scientist scientist = (Scientist) req.getAttribute(Const.VALID_USER_KEY);
            req.login(scientist.getEmail(), (String) req.getAttribute(Const.CLEARTEXT_PASSWORD));
            req.setAttribute(Const.CLEARTEXT_PASSWORD, null);
            resp.sendRedirect(Const.MAIN_PAGE);
        }
    }

    private void setYearsLimits(HttpServletRequest req) {
        LocalDate localDate = LocalDate.now();
        req.setAttribute(Const.MAX_YEAR_KEY, (localDate.getYear() - Const.BOTTOM_EDGE_OF_AGE));
        req.setAttribute(Const.MIN_YEAR_KEY, (localDate.getYear() - Const.TOP_EDGE_OF_AGE));
    }

    private void setAttributes(HttpServletRequest req, Scientist validUser) {
        req.setAttribute("email_exist", "Such email already exist");
        req.setAttribute("first_name", validUser.getFirstName());
        req.setAttribute("second_name", validUser.getSecondName());
        LocalDate localDate = validUser.getDob();
        req.setAttribute("month", localDate.getMonthValue());
        req.setAttribute("year", localDate.getYear());
        req.setAttribute("day", localDate.getDayOfMonth());
        req.setAttribute("m" + localDate.getMonthValue(), "selected");
        req.setAttribute("f" + validUser.getFieldOfScience().ordinal(), "selected");
    }

    private void loadUniversities(HttpSession session, int scientistId) {
        //buffer for unmodified universities
        List<University> unmodifiedUniversities = new ArrayList<>(Const.MAX_UNIVERSITIES);
        session.setAttribute(Const.UNMODIFIED_UNIVERSITIES_KEY, unmodifiedUniversities);
        for (int i = 0; i < Const.MAX_UNIVERSITIES; i++)
            unmodifiedUniversities.add(i, null);
        session.setAttribute(Const.UNIVERSITIES_CHANGED, null);

        List<University> universities = universityService.getAll(scientistId);
        session.setAttribute(Const.UNIVERSITIES_KEY, universities);
    }

}
