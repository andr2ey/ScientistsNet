package controller.profile.messages;

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
        req.setAttribute("emailTo", req.getParameter("button_write_message"));
        req.setAttribute("sciFirstName", req.getParameter("sciFirstName"));
        req.setAttribute("sciSecondName", req.getParameter("sciSecondName"));
        req.getRequestDispatcher("/WEB-INF/main/message/write/index.jsp").forward(req, resp);
    }
}
