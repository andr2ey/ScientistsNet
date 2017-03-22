package controller.profile.education;

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
 * Created on 13.03.2017.
 */
@WebServlet("/main/education/delete")
public class DeleteUniversity extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int indexDeleted = new Integer(req.getParameter("button_delete_education"));
        HttpSession httpSession = req.getSession();
        //noinspection unchecked
        List<University> universityList = (List<University>) httpSession.getAttribute(Const.UNIVERSITIES_KEY);
        universityList.get(indexDeleted).setDeleted(true);

        req.getRequestDispatcher("/WEB-INF/main/education/index.jsp").forward(req, resp);
    }
}
