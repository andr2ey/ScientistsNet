package controller.profile.messages;

import controller.profile.scientist.ScientistConst;
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
import java.time.LocalDateTime;

@WebServlet("/main/message/send")
public class SendMessage extends HttpServlet {

    private MessageService messageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        messageService = (MessageService) config.getServletContext().getAttribute(AppConst.MESSAGE_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailTo = req.getParameter(MessageConst.BUTTON_SEND_MESSAGE_KEY);
        if (emailTo == null) {
            resp.sendRedirect(UrlConst.MAIN);
            return;
        }
        String text = req.getParameter(MessageConst.TXT_OF_MESSAGE_KEY);
        String emailFrom = ((Scientist) req.getSession().getAttribute(SessionConst.EMAIL_KEY)).getEmail();
        Message message = create(emailTo, text, emailFrom);
        if (messageService.create(message)) {
            req.setAttribute(AppConst.SUCCESS, AppConst.SUCCESS);
        } else {
            req.setAttribute(AppConst.FAIL, AppConst.FAIL);
        }
        setAttributes(req, emailTo);
        req.getRequestDispatcher(UrlConst.WEB_INF_MAIN_MESSAGE_WRITE).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private Message create(String emailTo, String text, String emailFrom) {
        return new Message().builder()
                .setFrom(emailFrom)
                .setTo(emailTo)
                .setTxt(text)
                .setLocalDateTime(LocalDateTime.now()).build();
    }

    private void setAttributes(HttpServletRequest req, String emailTo) {
        req.setAttribute(ScientistConst.SCIENTIST_FIRST_NAME_KEY,
                req.getParameter(ScientistConst.SCIENTIST_FIRST_NAME_KEY));
        req.setAttribute(ScientistConst.SCIENTIST_SECOND_NAME_KEY,
                req.getParameter(ScientistConst.SCIENTIST_SECOND_NAME_KEY));
        req.setAttribute(MessageConst.EMAIL_TO_KEY, emailTo);
    }
}
