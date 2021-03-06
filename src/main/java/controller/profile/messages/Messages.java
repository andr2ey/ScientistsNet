package controller.profile.messages;

import model.Message;
import model.Scientist;
import service.MessageService;
import util.constants.AppConst;
import util.constants.SessionConst;
import util.constants.UrlConst;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Show all messages of scientist
 */

@WebServlet("/main/messages")
public class Messages extends HttpServlet {

    private MessageService messageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        messageService = (MessageService)config.getServletContext().getAttribute(AppConst.MESSAGE_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Scientist scientist = (Scientist)req.getSession().getAttribute(SessionConst.EMAIL_KEY);

        Set<Message> messageSet = messageService.getAll(scientist.getEmail());

        req.setAttribute(MessageConst.MESSAGE_SET_KEY, messageSet);
        req.getRequestDispatcher(UrlConst.WEB_INF_MAIN_MESSAGE).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
