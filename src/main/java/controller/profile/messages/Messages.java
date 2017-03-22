package controller.profile.messages;

import model.Message;
import model.Scientist;
import service.MessageService;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created on 20.03.2017.
 */
@WebServlet("/messages")
public class Messages extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MessageService messageService = (MessageService)req.getServletContext().getAttribute(Const.MESSAGE_SERVICE);
        Scientist scientist = (Scientist)req.getSession().getAttribute(Const.EMAIL_KEY);
        Set<Message> messageSet = messageService.getAll(scientist.getEmail());
        System.err.println(messageSet.size());
        req.setAttribute("messageSet", messageSet);
        req.getRequestDispatcher("WEB-INF/main/message/index.jsp").forward(req, resp);
    }
}
