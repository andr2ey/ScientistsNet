package controller;

import model.Scientist;
import model.University;
import org.apache.log4j.Logger;
import security.ScientistValidator;
import service.ScientistService;
import service.UniversityService;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created on 10.03.2017.
 */
@WebServlet("/signup")
public class SignUp extends HttpServlet {

    private static final String MAIN_PAGE_FULL_PATH = "WEB-INF/main/index.jsp";
    private static final String SIGN_UP_PAGE_FULL_PATH = "WEB-INF/signup/index.jsp";
    private static final String MAIN_PAGE = "/main";

    private static final Logger LOGGER = Logger.getRootLogger();
    private static final ScientistValidator VALIDATOR = new ScientistValidator();

    private static final int INITIAL_CAPACITY_UNMODIFIED_UNIVERSITIES = 10;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //avoids registration page if user is been already authorized
        if (authorized(req)) {
            req.getRequestDispatcher(MAIN_PAGE_FULL_PATH).forward(req, resp);
            return;
        }
        //verifies all filled fields and creates new user
        if (VALIDATOR.validateRegistrationFields(req) && createUser(req)) {
            resp.sendRedirect(MAIN_PAGE);
        } else {
            //TODO create information that such email has been already registered
            req.getRequestDispatcher(SIGN_UP_PAGE_FULL_PATH).forward(req, resp);
        }
    }

    private boolean createUser(HttpServletRequest req) throws ServletException {
        Scientist scientist = new Scientist().builder()
                .setEmail(VALIDATOR.getValidEmail()).setPassword(VALIDATOR.getValidPassword())
                .setFirstName(VALIDATOR.getValidFirstName()).setSecondName(VALIDATOR.getValidSecondName())
                .setMiddleName(VALIDATOR.getValidMiddleName()).setDob(VALIDATOR.getValidDate())
                .setGender(VALIDATOR.getValidGender()).build();
        ScientistService scientistService = (ScientistService)req
                .getServletContext().getAttribute(Const.SCIENTIST_SERVICE);
        if (scientistService.create(scientist) == 0) {
            req.setAttribute("email_exist", "Such email already exist");
            req.setAttribute("first_name", VALIDATOR.getValidFirstName());
            req.setAttribute("second_name", VALIDATOR.getValidSecondName());
            LocalDate localDate = VALIDATOR.getValidDate();
            req.setAttribute("month", localDate.getMonthValue());
            req.setAttribute("year", localDate.getYear());
            req.setAttribute("day", localDate.getDayOfMonth());
            req.setAttribute("m"+localDate.getMonthValue(), "selected");
            return false;
        }

        req.login(VALIDATOR.getValidEmail(), VALIDATOR.getValidPassword());

        UniversityService universityService = (UniversityService)req
                .getServletContext().getAttribute(Const.UNIVERSITY_SERVICE);
        loadUniversities(req.getSession(), universityService, scientist.getId());
        return true;
    }

    private boolean authorized(HttpServletRequest req) {
        return req.getSession().getAttribute(Const.EMAIL_KEY) != null;
    }

    @SuppressWarnings("Duplicates")
    private void loadUniversities(HttpSession session, UniversityService universityService, int scientistId) {
        //buffer for unmodified universities
        List<University> unmodifiedUniversities = new ArrayList<>(INITIAL_CAPACITY_UNMODIFIED_UNIVERSITIES);
        for (int i = 0; i < INITIAL_CAPACITY_UNMODIFIED_UNIVERSITIES; i++)
            unmodifiedUniversities.add(i, null);
        session.setAttribute(Const.UNMODIFIED_UNIVERSITIES_KEY, unmodifiedUniversities);

        List<University> universities = universityService.getAll(scientistId);
        session.setAttribute(Const.UNIVERSITIES_KEY, universities);
    }

}
