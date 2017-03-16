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
@WebServlet("/education_delete")
public class DeleteUniversity extends HttpServlet {
    private static final UniversityValidator VALIDATOR = new UniversityValidator();
    private static final String[] BUFFER = new String[4];

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("DeleteUniversity");
        int indexDeleted = new Integer(request.getParameter("button_delete_education"));

        HttpSession httpSession = request.getSession();
        //noinspection unchecked
        List<University> universityList = (List<University>) httpSession.getAttribute(Const.UNIVERSITIES_KEY);



        universityList.get(indexDeleted).setDeleted(true);

        request.getRequestDispatcher("WEB-INF/main/education/index.jsp").forward(request, response);
    }
}
