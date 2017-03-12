package controller;

import dao.ScientistDao;
import model.Gender;
import model.Scientist;
import org.apache.log4j.Logger;
import security.ScientistValidator;

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

    private static final Logger LOGGER = Logger.getLogger(SignUp.class);
    private static final ScientistValidator VALIDATOR = new ScientistValidator();



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute(EMAIL_KEY) != null) {
            request.getRequestDispatcher(MAIN_PAGE_FULL_PATH).forward(request, response);
        }
        if (verifyAndCreateUser(request, response) != null) {
            response.sendRedirect(MAIN_PAGE);
        } else {
            //TODO create information that such email has been already registered
            request.getRequestDispatcher(SIGN_UP_PAGE_FULL_PATH).forward(request, response);
        }
    }

    private Scientist verifyAndCreateUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String firstName = request.getParameter("first_name");
        firstName = firstName == null ? null : firstName.trim();
        String secondName = request.getParameter("second_name");
        secondName = secondName == null ? null : secondName.trim();
        String middleName = request.getParameter("middle_name");
        middleName = middleName == null ? null : middleName.trim();
        String gender = request.getParameter("gender");
        gender = gender == null ? null : gender.trim();
        String dob = request.getParameter("dob");
        dob = dob == null ? null : dob.trim();
        String email = request.getParameter("emailNew");
        email = email == null ? null : email.trim();
        String password = request.getParameter("passwordNew");
        password = password == null ? null : password.trim();

        VALIDATOR.firstName(firstName).secondName(secondName).middleName(middleName).gender(gender)
                .birthday(dob).email(email).password(password);
        if (VALIDATOR.isValid()) {
            Scientist user = new Scientist().builder().setEmail(email).setPassword(password).setFirstName(firstName)
                    .setSecondName(secondName).setMiddleName(middleName).setDob(localeDateParse(dob))
                    .setGender((gender != null ?  Gender.valueOf(gender.toUpperCase()) : null)).build();
            ((ScientistDao) getServletContext().getAttribute(SCIENTIST_DAO)).create(user);
            LOGGER.info(String.format("User (%s) is successfully created", user));
            return user;
        }
        return null;
    }

    private LocalDate localeDateParse(String dob) {
        if (dob == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // TODO consider locale
        return LocalDate.parse(dob, formatter.withLocale(Locale.getDefault()));
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
