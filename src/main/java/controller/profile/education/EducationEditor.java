package controller.profile.education;

import model.Scientist;
import model.University;
import service.UniversityService;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created on 13.03.2017.
 */

//work only after profile page
@WebServlet("/education")
public class EducationEditor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("EducationEditor");
        request.getRequestDispatcher("WEB-INF/main/education/index.jsp").forward(request, response);
    }


}
