package controller.profile;

import model.Scientist;
import model.University;
import service.UniversityService;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 10.03.2017.
 */
@WebServlet("/main")
public class Profile extends HttpServlet {

    private static final String LOGOUT = "logout";
    private static final String MAIN_PAGE_FULL_PATH = "WEB-INF/main/index.jsp";
    private static final String MAIN_PAGE_PATH = "/main";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("Profile");
        //did user log out
        if (profileLogout(req)) {
            //redirect to carry on an authorization
            response.sendRedirect(MAIN_PAGE_PATH);
        } else {
            //to profile
            //noinspection unchecked
            List<University> universityList =
                    (List<University>) req.getSession().getAttribute(Const.UNIVERSITIES_KEY);
            //noinspection unchecked
            List<University> unmodifiedList =
                    (List<University>) req.getSession().getAttribute(Const.UNMODIFIED_UNIVERSITIES_KEY);
            processDeletedCreatedUniversities(universityList, unmodifiedList);
            req.getRequestDispatcher(MAIN_PAGE_FULL_PATH).forward(req, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void processDeletedCreatedUniversities(List<University> universityList, List<University> unmodifiedList) {
        Iterator<University> iterator = universityList.iterator();
        //noinspection Duplicates
        int index = 0;
        while (iterator.hasNext()) {
            University university = iterator.next();
            if (university.isCreated()) {
                iterator.remove();
                ++index;
                continue;
            }
            if (university.isUpdated()) {
                universityList.set(index, unmodifiedList.get(index));
                ++index;
                continue;
            }
            ++index;
            university.setDeleted(false);
        }
        for (int i = 0; i < unmodifiedList.size(); i++) {
            unmodifiedList.set(i, null);
        }
    }

    //verifies logout button and invalidates session
    private boolean profileLogout(HttpServletRequest req) throws ServletException {
        if (req.getParameter(LOGOUT) == null)
            return false;
        req.getSession().invalidate();
        req.logout();
        return true;
    }

}
