package controller.profile.messages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 19.03.2017.
 */
@WebServlet("/write_message")
public class WriteMessage extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("emailTo", req.getParameter("button_write_message"));
        req.getRequestDispatcher("WEB-INF/main/message/write/index.jsp").forward(req, resp);
    }
}
