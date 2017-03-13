package controller;

import dao.ScientistDao;
import dao.mysql.MySqlScientistDao;
import model.Gender;
import model.Scientist;
import org.apache.log4j.Logger;
import security.ScientistValidator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created on 10.03.2017.
 */
@WebServlet("/signup")
public class SignUp extends HttpServlet {

    private static final String EMAIL_KEY = "email";
    private static final String SCIENTIST_DAO = "ScientistDao";

    private static final String MAIN_PAGE_FULL_PATH = "WEB-INF/main/index.jsp";
    private static final String SIGN_UP_PAGE_FULL_PATH = "WEB-INF/signup/index.jsp";
    private static final String MAIN_PAGE = "/main";

    private final Logger LOGGER = Logger.getRootLogger();
    private final ScientistValidator VALIDATOR = new ScientistValidator(Logger.getRootLogger());

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("emailNew");
        if (session.getAttribute(EMAIL_KEY) != null) {
            request.getRequestDispatcher(MAIN_PAGE_FULL_PATH).forward(request, response);
        } else if (verifyAndCreateUser(request, response, email == null ) != null) {
            response.sendRedirect(MAIN_PAGE);
        } else {
            //TODO create information that such email has been already registered
            request.getRequestDispatcher(SIGN_UP_PAGE_FULL_PATH).forward(request, response);
        }
    }

    private Scientist verifyAndCreateUser(HttpServletRequest request, HttpServletResponse response, boolean emailNull)
            throws IOException, ServletException {
        if(emailNull)
            return null;

        System.err.println(request.getHeader("Content-Type"));

        request.setCharacterEncoding("utf-8");
        String email = request.getParameter("emailNew").trim();
        String firstName = request.getParameter("first_name");
        System.err.println(firstName);
        String secondName = request.getParameter("second_name");
        String middleName = request.getParameter("middle_name");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String password = request.getParameter("passwordNew");

        ScientistDao scientistDao = (ScientistDao) getServletContext().getAttribute(SCIENTIST_DAO);

//        if(!emailExist(email, scientistDao)) {
//            getServletContext().setAttribute();
//        }

        firstName = firstName == null ? null : firstName.trim();
        secondName = secondName == null ? null : secondName.trim();
        middleName = (middleName == null || middleName.isEmpty()) ? null : middleName.trim();
        gender = (gender == null || gender.isEmpty()) ? null : gender.trim();
        dob = (dob == null || dob.isEmpty()) ? null : dob.trim();
        password = password == null ? null : password.trim();

        VALIDATOR.firstName(firstName).secondName(secondName).middleName(middleName).gender(gender)
                .birthday(dob).email(email).password(password);
        if (VALIDATOR.isValid()) {
            LOGGER.info("User is waiting to be created");
            Scientist user = new Scientist().builder().setEmail(email).setPassword(password).setFirstName(firstName)
                    .setSecondName(secondName).setMiddleName(middleName).setDob(localeDateParse(dob))
                    .setGender((gender != null ?  Gender.valueOf(gender.toUpperCase()) : Gender.NONE)).build();
            if (scientistDao.create(user) == 0) {
                request.setAttribute("email_exist", "Such email already exist");
                request.setAttribute("first_name", firstName);
                request.setAttribute("second_name", secondName);
                request.setAttribute("middle_name", middleName);
                request.setAttribute("gender", gender);
                request.setAttribute("dob", dob);
                request.getRequestDispatcher(SIGN_UP_PAGE_FULL_PATH).forward(request, response);
            }
            request.getSession()
                    .setAttribute("signup", "You have been successfully signed up. Please log in!");
            LOGGER.info(String.format("User (%s) is successfully created", user));
            request.login(email, password);
            return user;
        }
        LOGGER.warn("User hasn't created");
        return null;
    }

//    private boolean emailExist(String email, ScientistDao scientistDao) {
//        if(!VALIDATOR.email(email).isValid()) {
//            return false;
//        }
//    }

    private LocalDate localeDateParse(String dob) {
        if (dob == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // TODO consider locale
        return LocalDate.parse(dob, formatter.withLocale(Locale.getDefault()));
    }

}
