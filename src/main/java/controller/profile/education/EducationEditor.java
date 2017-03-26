package controller.profile.education;

import model.Scientist;
import model.University;
import security.UniversityValidator;
import service.UniversityService;
import util.constants.AppConst;
import util.constants.SessionConst;
import util.constants.UrlConst;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Processes changes of education page
 */
@WebServlet("/main/education")
public class EducationEditor extends HttpServlet {

    private UniversityService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        service = (UniversityService) config.getServletContext().getAttribute(AppConst.UNIVERSITY_SERVICE);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UniversityValidator validator = new UniversityValidator();
        List<University> universityList =
                (List<University>) req.getSession().getAttribute(SessionConst.UNIVERSITIES_KEY);
        if (req.getParameter(EducationConst.BUTTON_DELETE_EDUCATION) != null) {
            processDeleted(req, universityList);
        } else if (req.getParameter(EducationConst.BUTTON_UPDATE_EDUCATION) != null) {
            processUpdated(req, validator, universityList);
        } else if (req.getParameter(EducationConst.BUTTON_ADD_EDUCATION) != null) {
            processAdded(req, validator, universityList);
        } else if (req.getParameter(EducationConst.BUTTON_SAVE_EDUCATION) != null) {
            saveChanges(req, universityList);
        }
        restrictGraduationYear(req);
        req.getRequestDispatcher(UrlConst.WEB_INF_MAIN_EDUCATION).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void restrictGraduationYear(HttpServletRequest req) {
        LocalDate now = LocalDate.now();
        req.setAttribute(EducationConst.MAX_GRADUATION_YEAR_KEY, now.getYear() + EducationConst.MAX_EDUCATION_TIME);
        req.setAttribute(EducationConst.MIN_GRADUATION_YEAR_KEY, EducationConst.MIN_GRADUATION_YEAR);
    }

    private void processDeleted(HttpServletRequest req, List<University> universityList) {
        req.getSession().setAttribute(SessionConst.UNIVERSITIES_CHANGED_KEY, true);
        int indexDeleted = new Integer(req.getParameter(EducationConst.BUTTON_DELETE_EDUCATION));
        universityList.get(indexDeleted).setDeleted(true);
    }

    private void processUpdated(HttpServletRequest req, UniversityValidator validator, List<University> universityList) {
        req.getSession().setAttribute(SessionConst.UNIVERSITIES_CHANGED_KEY, true);
        int indexUpdated = Integer.parseInt(req.getParameter(EducationConst.BUTTON_UPDATE_EDUCATION));
        if (!validator.validateChangedFields(req, indexUpdated)) {
            req.setAttribute(EducationConst.UPDATED_STATUS, false);
            return;
        }
        req.setAttribute(EducationConst.UPDATED_STATUS, true);
        University university = universityList.get(indexUpdated);
        cloneToUnmodifiedList(req, indexUpdated, university);
        update(university, validator);
    }

    @SuppressWarnings("unchecked")
    private void cloneToUnmodifiedList(HttpServletRequest req, int indexUpdated, University university) {
        if (university.isUpdated() || university.isCreated())
            return;
        //add not updated old values to buffer
        List<University> bufferUnmodified =
                (List<University>) req.getSession().getAttribute(SessionConst.UNMODIFIED_UNIVERSITIES_KEY);
        try {
            bufferUnmodified.set(indexUpdated, (University) university.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(University university, UniversityValidator validator) {
        university.setUpdated(true);
        university.setCountry(validator.getValidCountry());
        university.setCity(validator.getValidCity());
        university.setFullName(validator.getValidFullName());
        university.setDegree(validator.getValidDegree());
        university.setGraduationYear(validator.getValidYear());
    }

    private void processAdded(HttpServletRequest req, UniversityValidator validator, List<University> universityList) {
        req.getSession().setAttribute(SessionConst.UNIVERSITIES_CHANGED_KEY, true);
        if (universityList.size() == EducationConst.MAX_UNIVERSITIES) {
            req.setAttribute(EducationConst.MAX_UNIVERSITIES_KEY, true);
            return;
        }
        if (!validator.validateAddedFields(req))
            return;
        Scientist scientist = (Scientist) req.getSession().getAttribute(SessionConst.EMAIL_KEY);
        universityList.add(new University().builder().setCreated(true)
                .setScientistId(scientist.getId())
                .setCountry(validator.getValidCountry())
                .setCity(validator.getValidCity())
                .setFullName(validator.getValidFullName())
                .setDegree(validator.getValidDegree())
                .setGraduationYear(validator.getValidYear()).build());
    }

    private void saveChanges(HttpServletRequest req, List<University> universityList) {
        if (req.getSession().getAttribute(SessionConst.UNIVERSITIES_CHANGED_KEY) == null)
            return;
        if (transactionCUD(universityList))
            req.setAttribute(EducationConst.SUCCESS_OF_TRANSACTION, true);
        else
            req.setAttribute(EducationConst.SUCCESS_OF_TRANSACTION, false);
    }

    private boolean transactionCUD(List<University> universityList) {
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
        return service.transactionCUD(listDeleted, listCreated, listUpdated);
    }
}
