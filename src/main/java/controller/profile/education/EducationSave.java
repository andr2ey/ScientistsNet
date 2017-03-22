package controller.profile.education;

import model.University;
import security.UniversityValidator;
import service.UniversityService;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 13.03.2017.
 */
@WebServlet("/education_save")
public class EducationSave extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //noinspection unchecked
        List<University> universityList =
                (List<University>) req.getSession().getAttribute(Const.UNIVERSITIES_KEY);
        UniversityService service =
                (UniversityService) req.getServletContext().getAttribute(Const.UNIVERSITY_SERVICE);
        if (transactionCUDSuccess(service, universityList)) {
            req.setAttribute(Const.SUCCESS_OF_TRANSACTION, "success");
        } else {
            req.setAttribute(Const.SUCCESS_OF_TRANSACTION, "fail");
        }
        req.getRequestDispatcher("WEB-INF/main/education/index.jsp").forward(req, resp);
    }

    private boolean transactionCUDSuccess(UniversityService service, List<University> universityList) {
        List<University> listDeleted = new ArrayList<>();
        List<University> listCreated = new ArrayList<>();
        List<University> listUpdated = new ArrayList<>();
        Iterator<University> iterator = universityList.iterator();
        while (iterator.hasNext()) {
            University university = iterator.next();
            if (university.isDeleted()) {
                if (university.isCreated()) {
                    listDeleted.add(university);
                    iterator.remove();
                    continue;
                }
                listDeleted.add(university);
                iterator.remove();
                continue;
            }
            if (university.isCreated()) {
                if (university.isUpdated()) {
                    listCreated.add(university);
                    university.setUpdated(false);
                    university.setCreated(false);
                    continue;
                }
                listCreated.add(university);
                university.setCreated(false);
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
