package controller.profile.education;

import model.Scientist;
import model.University;
import security.UniversityValidator;
import service.UniversityService;
import util.Const;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@WebServlet("/main/education")
public class EducationEditor extends HttpServlet {

    private UniversityService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (UniversityService) config.getServletContext().getAttribute(Const.UNIVERSITY_SERVICE);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UniversityValidator validator = new UniversityValidator();
        HttpSession session = req.getSession();
        List<University> universityList = (List<University>) session.getAttribute(Const.UNIVERSITIES_KEY);
        if (req.getParameter("button_delete_education") != null) {
            processDeleted(req, universityList);
        } else if (req.getParameter("button_update_education") != null) {
            processUpdated(req, validator, universityList);
        } else if (req.getParameter("button_add_education") != null) {
            processAdded(req, validator, universityList);
        } else if (req.getParameter("button_save_education") != null) {
            saveChanges(req, universityList);
        }
        restrictGraduationYear(req);
        req.getRequestDispatcher("/WEB-INF/main/education/index.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void restrictGraduationYear(HttpServletRequest req) {
        LocalDate now = LocalDate.now();
        req.setAttribute(Const.MAX_GRADUATION_YEAR_KEY, now.getYear() + Const.MAX_EDUCATION_TIME);
        req.setAttribute(Const.MIN_GRADUATION_YEAR_KEY, Const.MIN_GRADUATION_YEAR);
    }

    private void processDeleted(HttpServletRequest req, List<University> universityList) {
        req.getSession().setAttribute(Const.UNIVERSITIES_CHANGED, true);
        int indexDeleted = new Integer(req.getParameter("button_delete_education"));
        universityList.get(indexDeleted).setDeleted(true);
    }

    @SuppressWarnings("unchecked")
    private void processUpdated(HttpServletRequest req, UniversityValidator validator, List<University> universityList) {
        req.getSession().setAttribute(Const.UNIVERSITIES_CHANGED, true);
        int indexUpdated = new Integer(req.getParameter("button_update_education"));
        if (validator.validateChangedFields(req, indexUpdated)) {
            req.setAttribute(Const.UPDATED_STATUS, "success");
            University university = universityList.get(indexUpdated);
            //add not updated old values to buffer
            if (!university.isUpdated() && !university.isCreated()) {
                List<University> bufferUnmodified =
                        (List<University>) req.getSession().getAttribute(Const.UNMODIFIED_UNIVERSITIES_KEY);
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
            req.setAttribute(Const.UPDATED_STATUS, Const.FAIL);
        }
    }

    private void processAdded(HttpServletRequest req, UniversityValidator validator, List<University> universityList) {
        req.getSession().setAttribute(Const.UNIVERSITIES_CHANGED, true);
        if (universityList.size() == Const.MAX_UNIVERSITIES) {
            req.setAttribute("maxUniversity", "You can't add more than 10 universities");
        } else {
            if (validator.validateAddedFields(req)) {
                Scientist scientist = (Scientist) req.getSession().getAttribute(Const.EMAIL_KEY);
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

    private void saveChanges(HttpServletRequest req, List<University> universityList) {
        if (!(Boolean) req.getSession().getAttribute(Const.UNIVERSITIES_CHANGED)) {
            return;
        }
        if (transactionCUDSuccess(universityList)) {
            req.setAttribute(Const.SUCCESS_OF_TRANSACTION, Const.SUCCESS);
        } else {
            req.setAttribute(Const.SUCCESS_OF_TRANSACTION, Const.FAIL);
        }
    }

    private boolean transactionCUDSuccess(List<University> universityList) {
        List<University> listDeleted = new ArrayList<>();
        List<University> listCreated = new ArrayList<>();
        List<University> listUpdated = new ArrayList<>();
        Iterator<University> iterator = universityList.iterator();
        while (iterator.hasNext()) {
            University university = iterator.next();
            if (university.isDeleted()) {
                if (university.isCreated()) {
                    iterator.remove();
                    continue;
                }
                listDeleted.add(university);
                iterator.remove();
                continue;
            }
            if (university.isCreated()) {
                listCreated.add(university);
                university.setCreated(false);
                university.setUpdated(false);
                continue;
            }
            if (university.isUpdated()) {
                listUpdated.add(university);
                university.setUpdated(false);
            }
        }
        return (service.transactionCUD(listDeleted, listCreated, listUpdated));
    }
}
