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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created on 19.03.2017.
 */
@WebServlet("/send_message")
public class SendMessage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailTo = req.getParameter("button_send_message");
        String txt =  req.getParameter("txt_of_message");
        Scientist scientist = (Scientist)req.getSession().getAttribute(Const.EMAIL_KEY);
        String emailFrom = scientist.getEmail();
        Message message = new Message().builder()
                .setFrom(emailFrom)
                .setTo(emailTo)
                .setTxt(txt)
                .setLocalDate(LocalDate.now())
                .setLocalTime(LocalDateTime.now().toLocalTime()).build();
        MessageService messageService = (MessageService) req.getServletContext().getAttribute(Const.MESSAGE_SERVICE);
        if (messageService.create(message)) {
            req.setAttribute("success", "success");
        } else {
            req.setAttribute("fail", "fail");
        }
        req.setAttribute("emailTo", req.getParameter(emailTo));
        req.getRequestDispatcher("WEB-INF/main/message/write/index.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
