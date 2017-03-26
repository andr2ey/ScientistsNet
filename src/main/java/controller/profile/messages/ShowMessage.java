package controller.profile.messages;

import util.constants.UrlConst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Show concrete message
 */
@WebServlet("/main/message/show")
public class ShowMessage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String messageNumber = req.getParameter(MessageConst.BUTTON_SHOW_MESSAGE_KEY);

        String email = req.getParameter(MessageConst.EMAIL + messageNumber);
        String text = req.getParameter(MessageConst.MESSAGE_KEY + messageNumber);
        String direction = req.getParameter(MessageConst.DIRECTION_KEY + messageNumber);

        req.setAttribute(MessageConst.EMAIL, email);
        req.setAttribute(MessageConst.TEXT_KEY, text);
        req.setAttribute(MessageConst.DIRECTION_KEY, direction);
        req.getRequestDispatcher(UrlConst.WEB_INF_MAIN_MESSAGE_SHOW).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
