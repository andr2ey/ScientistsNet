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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("Profile");
        //did user log out
        if (profileLogout(request)) {
            //redirect to carry on an authorization
            response.sendRedirect(MAIN_PAGE_PATH);
        } else {
            //to profile
            //noinspection unchecked
            List<University> universityList =
                    (List<University>) request.getSession().getAttribute(Const.UNIVERSITIES_KEY);
            List<University> unmodifiedList = (List<University>) request.getSession().getAttribute(Const.UNMODIFIED_UNIVERSITIES_KEY);
            processDeletedCreatedUniversities(universityList, unmodifiedList);
            request.getRequestDispatcher(MAIN_PAGE_FULL_PATH).forward(request, response);
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
                System.err.println("isCreated");
                ++index;
                continue;
            }
            if (university.isUpdated()) {
                universityList.set(index, unmodifiedList.get(index));
                System.err.println("isUpdated");
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
