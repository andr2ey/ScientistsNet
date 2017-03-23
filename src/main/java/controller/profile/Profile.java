package controller.profile;

import org.apache.log4j.Logger;
import util.Const;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class Profile extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Profile.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //did user log out
        if (profileLogout(req)) {
            //redirect to carry on an authorization
            LOGGER.info(String.format("User - (%s) logged out", req.getSession().getAttribute(Const.EMAIL_KEY)));
            resp.sendRedirect(Const.MAIN_PAGE);
        } else {
            //to profile
            if (req.getParameter("button_show_full") != null) {
                req.setAttribute("button_show_full", true);
            }
            req.getRequestDispatcher(Const.MAIN_PAGE_FULL_PATH).forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    //verifies logout button and invalidates session
    private boolean profileLogout(HttpServletRequest req) throws ServletException {
        if (req.getParameter(Const.LOGOUT) == null)
            return false;
        req.getSession().invalidate();
        req.logout();
        return true;
    }

}
