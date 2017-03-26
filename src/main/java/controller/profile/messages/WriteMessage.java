package controller.profile.messages;

import controller.profile.scientist.ScientistConst;
import util.constants.UrlConst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main/message/write")
public class WriteMessage extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter(MessageConst.BUTTON_WRITE_MESSAGE_KEY) != null) {
            req.setAttribute(MessageConst.EMAIL_TO_KEY, req.getParameter(MessageConst.BUTTON_WRITE_MESSAGE_KEY));
            setAttributes(req);
            req.getRequestDispatcher(UrlConst.WEB_INF_MAIN_MESSAGE_WRITE).forward(req, resp);
        } else {
            resp.sendRedirect(UrlConst.MAIN);
        }
    }

    private void setAttributes(HttpServletRequest req) {
        req.setAttribute(MessageConst.EMAIL_TO_KEY, req.getParameter(MessageConst.BUTTON_WRITE_MESSAGE_KEY));
        req.setAttribute(ScientistConst.SCIENTIST_FIRST_NAME_KEY,
                req.getParameter(ScientistConst.SCIENTIST_FIRST_NAME_KEY));
        req.setAttribute(ScientistConst.SCIENTIST_SECOND_NAME_KEY,
                req.getParameter(ScientistConst.SCIENTIST_SECOND_NAME_KEY));
    }
}
