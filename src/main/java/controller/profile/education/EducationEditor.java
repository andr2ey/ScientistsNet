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

    private static final UniversityValidator validator = new UniversityValidator();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //noinspection unchecked
        List<University> universityList = (List<University>) session.getAttribute(Const.UNIVERSITIES_KEY);
        if (req.getParameter("button_delete_education") != null) {
            deletedProcess(req, universityList);
        } else if (req.getParameter("button_update_education") != null) {
            updatedProcess(req, session, universityList);
        } else if (req.getParameter("button_add_education") != null) {
            createdProcess(req, session, universityList);
        }
        req.getRequestDispatcher("/WEB-INF/main/education/index.jsp").forward(req, resp);
    }


    private void deletedProcess(HttpServletRequest req, List<University> universityList) {
        int indexDeleted = new Integer(req.getParameter("button_delete_education"));
        universityList.get(indexDeleted).setDeleted(true);
    }

    private void updatedProcess(HttpServletRequest req, HttpSession session, List<University> universityList) {
        int indexUpdated = new Integer(req.getParameter("button_update_education"));
        if (validator.validateChangedFields(req)) {
            req.setAttribute(Const.UPDATED_STATUS, "success");
            University university = universityList.get(indexUpdated);
            //add not updated old values to buffer
            if (!university.isUpdated()) {
                //noinspection unchecked
                List<University> bufferUnmodified =
                        (List<University>) session.getAttribute(Const.UNMODIFIED_UNIVERSITIES_KEY);
                try {
                    bufferUnmodified.set(indexUpdated, (University) university.clone());
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }
            university.setUpdated(true);
            university.setCountry(validator.getValidCountry());
            university.setCity(validator.getValidCity());
            university.setFullName(validator.getValidFullName());
            university.setDegree(validator.getValidDegree());
            university.setGraduationYear(validator.getValidYear());
        } else {
            req.setAttribute(Const.UPDATED_STATUS, "fail");
        }
    }

    private void createdProcess(HttpServletRequest req, HttpSession session, List<University> universityList) {
        if (universityList.size() == Const.MAX_UNIVERSITIES) {
            req.setAttribute("maxUniversity", "You can't add more than 10 universities");
        } else {
            if (validator.validateChangedFields(req)) {
                Scientist scientist = (Scientist) session.getAttribute(Const.EMAIL_KEY);
                universityList.add(new University().builder()
                        .setCreated(true)
                        .setScientistId(scientist.getId())
                        .setCountry(validator.getValidCountry())
                        .setCity(validator.getValidCity())
                        .setFullName(validator.getValidFullName())
                        .setDegree(validator.getValidDegree())
                        .setGraduationYear(validator.getValidYear())
                        .build());
            }
        }
    }
}
