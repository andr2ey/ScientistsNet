package controller.profile.education;

import model.University;
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
        } else if ()
        req.getRequestDispatcher("/WEB-INF/main/education/index.jsp").forward(req, resp);
    }


}
