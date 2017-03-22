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
import java.util.List;


@WebServlet("/main/education")
public class EducationEditor extends HttpServlet {

    private static final UniversityValidator VALIDATOR = new UniversityValidator();
    private static final String[] BUFFER = new String[4];
    private static final String UPDATED_STATUS = "updatedStatus";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("button_delete_education") != null) {
            int indexDeleted = new Integer(req.getParameter("button_delete_education"));
            HttpSession httpSession = req.getSession();
            //noinspection unchecked
            List<University> universityList = (List<University>) httpSession.getAttribute(Const.UNIVERSITIES_KEY);
            universityList.get(indexDeleted).setDeleted(true);
        } else if (req.getParameter("button_update_education") != null) {
            int indexUpdated = new Integer(req.getParameter("button_update_education"));
            HttpSession httpSession = req.getSession();
            //noinspection unchecked
            List<University> universityList = (List<University>) httpSession.getAttribute(Const.UNIVERSITIES_KEY);

            BUFFER[0] = req.getParameter("univ_country" + indexUpdated);
            BUFFER[1] = req.getParameter("univ_city" + indexUpdated);
            BUFFER[2] = req.getParameter("univ_full_name" + indexUpdated);
            BUFFER[3] = req.getParameter("univ_degree" + indexUpdated);
            System.err.println(req.getParameter("univ_country" + indexUpdated));
            System.err.println(BUFFER[1]);
            System.err.println(BUFFER[2]);
            System.err.println(BUFFER[3]);
            for (int i = 0; i < BUFFER.length; i++) {
                BUFFER[i] = (BUFFER[i] == null || BUFFER[i].equals("")) ? null : BUFFER[i].trim();
            }
            VALIDATOR.verifyAll(BUFFER);
            if (VALIDATOR.isValid()) {
                req.setAttribute(UPDATED_STATUS, "success");
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
                req.setAttribute(UPDATED_STATUS, "fail");
            }
        } else if (req.getParameter("button_add_education") != null) {
            System.err.println("AddUniversity");
            HttpSession httpSession = req.getSession();
            //noinspection unchecked
            List<University> universityList = (List<University>) httpSession.getAttribute(Const.UNIVERSITIES_KEY);
            if (universityList.size() == 10) {
                req.setAttribute("maxUniversity", "You can't add more than 10 universities");
            } else {
                BUFFER[0] = req.getParameter("education_country");
                BUFFER[1] = req.getParameter("education_city");
                BUFFER[2] = req.getParameter("education_fullName");
                BUFFER[3] = req.getParameter("education_degree");
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
            }
        }
        req.getRequestDispatcher("/WEB-INF/main/education/index.jsp").forward(req, resp);
    }


}
