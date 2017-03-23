package controller.profile.messages;

import model.Message;
import model.Scientist;
import service.MessageService;
import service.ScientistService;
import util.Const;

import javax.servlet.ServletConfig;
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
@WebServlet("/main/messages")
public class Messages extends HttpServlet {

    private MessageService messageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        messageService = (MessageService)config.getServletContext().getAttribute(Const.MESSAGE_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Scientist scientist = (Scientist)req.getSession().getAttribute(Const.EMAIL_KEY);

        Set<Message> messageSet = messageService.getAll(scientist.getEmail());

        req.setAttribute("messageSet", messageSet);
        req.getRequestDispatcher("/WEB-INF/main/message/index.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
