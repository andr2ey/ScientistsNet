package controller.profile.messages;

import model.Message;
import model.Scientist;
import service.MessageService;
import util.Const;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/main/message/send")
public class SendMessage extends HttpServlet {

    private MessageService messageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        messageService = (MessageService) config.getServletContext().getAttribute(Const.MESSAGE_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailTo = req.getParameter("button_send_message");
        if (emailTo != null) {
            String text = req.getParameter("txt_of_message");
            Scientist scientist = (Scientist) req.getSession().getAttribute(Const.EMAIL_KEY);
            String emailFrom = scientist.getEmail();
            Message message = new Message().builder()
                    .setFrom(emailFrom)
                    .setTo(emailTo)
                    .setTxt(text)
                    .setLocalDateTime(LocalDateTime.now()).build();
            if (messageService.create(message)) {
                req.setAttribute(Const.SUCCESS, Const.SUCCESS);
            } else {
                req.setAttribute(Const.FAIL, Const.FAIL);
            }
            setAttributes(req, emailTo);
            req.getRequestDispatcher("/WEB-INF/main/message/write/index.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/main");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void setAttributes(HttpServletRequest req, String emailTo) {
        req.setAttribute("sciFirstName", req.getParameter("sciFirstName"));
        req.setAttribute("sciSecondName", req.getParameter("sciSecondName"));
        req.setAttribute("emailTo", emailTo);
    }
}
