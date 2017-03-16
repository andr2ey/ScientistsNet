package controller.profile.education;

import model.Degree;
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
import java.util.List;

/**
 * Created on 14.03.2017.
 */
@WebServlet("/education_update")
public class UpdateUniversity extends HttpServlet {

    private static final UniversityValidator VALIDATOR = new UniversityValidator();
    private static final String[] BUFFER = new String[4];
    private static final String UPDATED_STATUS = "updatedStatus";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("UpdateUniversity");
        int indexUpdated = new Integer(request.getParameter("button_update_education"));
        System.err.println(indexUpdated);

        HttpSession httpSession = request.getSession();
        //noinspection unchecked
        List<University> universityList = (List<University>) httpSession.getAttribute(Const.UNIVERSITIES_KEY);

        BUFFER[0] = request.getParameter("univ_country" + indexUpdated);
        BUFFER[1] = request.getParameter("univ_city" + indexUpdated);
        BUFFER[2] = request.getParameter("univ_full_name" + indexUpdated);
        BUFFER[3] = request.getParameter("univ_degree" + indexUpdated);
        System.err.println(request.getParameter("univ_country" + indexUpdated));
        System.err.println(BUFFER[1]);
        System.err.println(BUFFER[2]);
        System.err.println(BUFFER[3]);
        for (int i = 0; i < BUFFER.length; i++) {
            BUFFER[i] = (BUFFER[i] == null || BUFFER[i].equals("")) ? null : BUFFER[i].trim();
        }
        VALIDATOR.verifyAll(BUFFER);
        if (VALIDATOR.isValid()) {
            request.setAttribute(UPDATED_STATUS, "success");
            University university = universityList.get(indexUpdated);
            //add not updated old values to buffer
            if (!university.isUpdated()) {
                //noinspection unchecked
                List<University> bufferUnmodified =
                        (List<University>) httpSession.getAttribute(Const.UNMODIFIED_UNIVERSITIES_KEY);
                try {
                    bufferUnmodified.set(indexUpdated, (University) university.clone());
                } catch (CloneNotSupportedException e) {
                    //TODO add logging
                    throw new RuntimeException(e);
                }
            }
            university.setUpdated(true);
            university.setCountry(BUFFER[0]);
            university.setCity(BUFFER[1]);
            university.setFullName(BUFFER[2]);
            university.setDegree(Degree.valueOf(BUFFER[3].toUpperCase()));
        } else {
            request.setAttribute(UPDATED_STATUS, "fail");
        }
        request.getRequestDispatcher("WEB-INF/main/education/index.jsp").forward(request, response);
    }
}
