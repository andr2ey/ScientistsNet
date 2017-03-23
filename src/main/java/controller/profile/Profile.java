package controller.profile;

import util.Const;

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
public class Profile extends HttpServlet {

    private static final String LOGOUT = "logout";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //did user log out
        if (profileLogout(req)) {
            //redirect to carry on an authorization
            resp.sendRedirect(Const.MAIN_PAGE);
        } else {
            //to profile
            req.getRequestDispatcher(Const.MAIN_PAGE_FULL_PATH).forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
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
