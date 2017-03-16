package controller.profile.education;

import model.Degree;
import model.Scientist;
import model.University;
import security.UniversityValidator;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created on 13.03.2017.
 */
@WebServlet("/education_add")
public class AddUniversity extends HttpServlet {

    private static final UniversityValidator VALIDATOR = new UniversityValidator();
    private static final String[] BUFFER = new String[4];

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("AddUniversity");
        HttpSession httpSession = request.getSession();
        //noinspection unchecked
        List<University> universityList = (List<University>) httpSession.getAttribute(Const.UNIVERSITIES_KEY);
        if (universityList.size() == 10) {
            request.setAttribute("maxUniversity", "You can't add more than 10 universities");
            request.getRequestDispatcher("WEB-INF/main/education/index.jsp").forward(request, response);
        } else {
            BUFFER[0] = request.getParameter("education_country");
            BUFFER[1] = request.getParameter("education_city");
            BUFFER[2] = request.getParameter("education_fullName");
            BUFFER[3] = request.getParameter("education_degree");
            for (int i = 0; i < BUFFER.length; i++)
                BUFFER[i] = (BUFFER[i] == null || BUFFER[i].equals("")) ? null : BUFFER[i].trim();
            VALIDATOR.verifyAll(BUFFER);
            if (VALIDATOR.isValid()) {
                Scientist scientist = (Scientist) httpSession.getAttribute(Const.EMAIL_KEY);
                universityList.add(new University().builder().setCreated(true)
                        .setScientistId(scientist.getId())
                        .setCountry(BUFFER[0]).setCity(BUFFER[1]).setFullName(BUFFER[2])
                        .setDegree(Degree.valueOf(BUFFER[3].toUpperCase())).build());

            }
            request.getRequestDispatcher("WEB-INF/main/education/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
