package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 10.03.2017.
 */
@WebServlet("/main")
public class MainPage extends HttpServlet {

    private static final String LOGOUT = "logout";
    private static final String MAIN_PAGE_FULL_PATH = "WEB-INF/main/index.jsp";
    private static final String MAIN_PAGE_PATH = "/main";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //did user log out
        if (accountLogout(request)) {
            //redirect to carry on an authorization
            response.sendRedirect(MAIN_PAGE_PATH);
        } else {
            //process page of account
            request.getRequestDispatcher(MAIN_PAGE_FULL_PATH).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //verifies logout button and invalidates session
    private boolean accountLogout(HttpServletRequest req) throws ServletException {
        if (req.getParameter(LOGOUT) == null)
            return false;
        req.getSession().invalidate();
        req.logout();
        return true;
    }
}
