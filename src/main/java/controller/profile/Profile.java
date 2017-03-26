package controller.profile;

import org.apache.log4j.Logger;
import util.constants.AppConst;
import util.constants.SessionConst;
import util.constants.UrlConst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Process main page of profile.
 */
@WebServlet("/main")
public class Profile extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Profile.class);
    private static final String BUTTON_SHOW_FULL_UNIVERSITIES = "button_show_full";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //did scientist log out
        if (scientistLogout(req)) {
            LOGGER.info(String.format("User - (%s) logged out", req.getSession().getAttribute(SessionConst.EMAIL_KEY)));
            resp.sendRedirect(UrlConst.MAIN);
            return;
        }
        if (req.getParameter(BUTTON_SHOW_FULL_UNIVERSITIES) != null)
            req.setAttribute(BUTTON_SHOW_FULL_UNIVERSITIES, true);
        req.getRequestDispatcher(UrlConst.WEB_INF_MAIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    //verifies logout button and invalidates session
    private boolean scientistLogout(HttpServletRequest req) throws ServletException {
        if (req.getParameter(AppConst.LOGOUT) == null)
            return false;
        req.getSession().invalidate();
        req.logout();
        return true;
    }

}
