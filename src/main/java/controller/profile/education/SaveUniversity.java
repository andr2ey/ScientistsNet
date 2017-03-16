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
public class SaveUniversity extends HttpServlet {

    private static final UniversityValidator VALIDATOR = new UniversityValidator();
    private static final String[] BUFFER = new String[4];
    public static final String SUCCESS_OF_TRANSACTION = "successOfTransaction";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("SaveUniversity");
        //noinspection unchecked
        List<University> universityList = (List<University>) request.getSession().getAttribute(Const.UNIVERSITIES_KEY);
        if (transactionCUDSuccess(request, universityList)) {
            request.setAttribute(SUCCESS_OF_TRANSACTION, "success");
        } else {
            request.setAttribute(SUCCESS_OF_TRANSACTION, "fail");
        }
        request.getRequestDispatcher("WEB-INF/main/education/index.jsp").forward(request, response);
    }

    private boolean transactionCUDSuccess(HttpServletRequest request, List<University> universityList) {
        UniversityService service = (UniversityService) request.getServletContext().getAttribute(Const.UNIVERSITY_SERVICE);
        Iterator<University> iterator = universityList.iterator();
        List<University> listDeleted = new ArrayList<>();
        List<University> listCreated = new ArrayList<>();
        List<University> listUpdated = new ArrayList<>();
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
