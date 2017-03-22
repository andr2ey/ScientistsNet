package controller.registration;

import model.Scientist;
import model.University;
import security.ScientistValidator;
import service.ScientistService;
import service.UniversityService;
import util.Const;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RegistrationService implements Runnable {

    private AsyncContext asyncContext;
    private HttpServletRequest req;
    private ScientistValidator validator;
    private ScientistService scientistService;
    private UniversityService universityService;

    RegistrationService(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
        req = (HttpServletRequest) asyncContext.getRequest();
        validator = new ScientistValidator();
        ServletContext servletContext = req.getServletContext();
        scientistService = (ScientistService) servletContext.getAttribute(Const.SCIENTIST_SERVICE);
        universityService = (UniversityService) servletContext.getAttribute(Const.UNIVERSITY_SERVICE);
    }

    @Override
    public void run() {
        if (validator.validateRegistrationFields(req)) {
            Scientist validUser = new Scientist().builder()
                    .setEmail(validator.getValidEmail()).setPassword(validator.getValidPassword())
                    .setFirstName(validator.getValidFirstName()).setSecondName(validator.getValidSecondName())
                    .setMiddleName(validator.getValidMiddleName()).setDob(validator.getValidDate())
                    .setGender(validator.getValidGender()).setFieldOfScience(validator.getValidFieldOfScience()).build();
            req.setAttribute(Const.VALID_USER_KEY, validUser);
            if (scientistService.create(validUser) == 0) {
                req.setAttribute("email_exist", "Such email already exist");
                req.setAttribute("first_name", validUser.getFirstName());
                req.setAttribute("second_name", validUser.getSecondName());
                LocalDate localDate = validUser.getDob();
                req.setAttribute("month", localDate.getMonthValue());
                req.setAttribute("year", localDate.getYear());
                req.setAttribute("day", localDate.getDayOfMonth());
                req.setAttribute("m" + localDate.getMonthValue(), "selected");
                req.setAttribute("f" + validUser.getFieldOfScience().ordinal(), "selected");
            } else {
                HttpSession session = req.getSession();
                validUser.setFormattedDob(formatDate(req.getLocale(), validUser.getDob()));
                session.setAttribute(Const.EMAIL_KEY, validUser);
                session.setAttribute(Const.UNIVERSITIES_CHANGED, false);
                loadUniversities(session, validUser.getId());
                req.setAttribute(Const.CREATED_USER_KEY, validUser);
            }
        }
        asyncContext.complete();
    }

    @SuppressWarnings("Duplicates")
    private void loadUniversities(HttpSession session, int scientistId) {
        //buffer for unmodified universities
        List<University> unmodifiedUniversities = new ArrayList<>(Const.INITIAL_CAPACITY_UNMODIFIED_UNIVERSITIES);
        for (int i = 0; i < Const.INITIAL_CAPACITY_UNMODIFIED_UNIVERSITIES; i++)
            unmodifiedUniversities.add(i, null);
        session.setAttribute(Const.UNMODIFIED_UNIVERSITIES_KEY, unmodifiedUniversities);
        List<University> universities = universityService.getAll(scientistId);
        session.setAttribute(Const.UNIVERSITIES_KEY, universities);
    }

    private String formatDate(Locale locale, LocalDate localDate) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        return df.format(Date.valueOf(localDate));
    }
}
