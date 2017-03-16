package controller.profile.info;

import dao.ScientistDao;
import model.Gender;
import model.Scientist;
import security.ScientistValidator;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 15.03.2017.
 */
@WebServlet("/info_save")
public class UpdateBasicInfo extends HttpServlet {
    private final ScientistValidator VALIDATOR = new ScientistValidator();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("UpdateBasicInfo");
        String passwordOld = request.getParameter("passwordOld").trim();
        String emailNew = request.getParameter("emailNew").trim();
        if (!passwordOld.isEmpty() && !emailNew.isEmpty()) {
            System.err.println("passwordOld isEmpty " + passwordOld.isEmpty());
            System.err.println("emailNew isEmpty" + emailNew.isEmpty());
            System.err.println(request.getParameter("first_name"));
            System.err.println(request.getParameter("second_name"));
            System.err.println(request.getParameter("middle_name"));
            System.err.println(request.getParameter("gender"));
            System.err.println(request.getParameter("dob"));
            System.err.println(request.getParameter("passwordNew"));
            System.err.println("passwordOld isEmpty");
            System.err.println("passwordOld isEmpty");
            System.err.println("passwordOld isEmpty");
        }
        request.getRequestDispatcher("WEB-INF/main/baseinfo/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

//    private Scientist verifyAndCreateUser(HttpServletRequest request, HttpServletResponse response, boolean passwordOld)
//            throws IOException, ServletException {
//        if(passwordOld)
//            return null;
//
//        String email = request.getParameter("emailNew").trim();
//        String firstName = request.getParameter("first_name");
//        System.err.println(firstName);
//        String secondName = request.getParameter("second_name");
//        String middleName = request.getParameter("middle_name");
//        String gender = request.getParameter("gender");
//        String dob = request.getParameter("dob");
//        String password = request.getParameter("passwordNew");
//
//        ScientistDao scientistDao = (ScientistDao) getServletContext().getAttribute(Const.SCIENTIST_DAO);
//
////        if(!emailExist(email, scientistDao)) {
////            getServletContext().setAttribute();
////        }
//
//        firstName = firstName == null ? null : firstName.trim();
//        secondName = secondName == null ? null : secondName.trim();
//        middleName = (middleName == null || middleName.isEmpty()) ? null : middleName.trim();
//        gender = (gender == null || gender.isEmpty()) ? null : gender.trim();
//        dob = (dob == null || dob.isEmpty()) ? null : dob.trim();
//        password = password == null ? null : password.trim();
//
//        VALIDATOR.firstName(firstName).secondName(secondName).middleName(middleName).gender(gender)
//                .birthday(dob).email(email).password(password);
//        if (VALIDATOR.isValid()) {
//            LOGGER.info("User is waiting to be created");
//            Scientist scientist = new Scientist().builder().setEmail(email).setPassword(password).setFirstName(firstName)
//                    .setSecondName(secondName).setMiddleName(middleName).setDob(localeDateParse(dob))
//                    .setGender((gender != null ?  Gender.valueOf(gender.toUpperCase()) : Gender.NONE)).build();
//            if (scientistDao.create(scientist) == 0) {
//                request.setAttribute("email_exist", "Such email already exist");
//                request.setAttribute("first_name", firstName);
//                request.setAttribute("second_name", secondName);
//                request.setAttribute("middle_name", middleName);
//                request.setAttribute("gender", gender);
//                request.setAttribute("dob", dob);
//                request.getRequestDispatcher(SIGN_UP_PAGE_FULL_PATH).forward(request, response);
//            }
//
//            request.getSession()
//                    .setAttribute("signup", "You have been successfully signed up. Please log in!");
//            LOGGER.info(String.format("User (%s) is successfully created", scientist));
//
//            request.login(email, password);
//            loadUniversities(request, scientist.getId());
//            return scientist;
//        }
//        LOGGER.warn("User hasn't created");
//        return null;
//    }
}
