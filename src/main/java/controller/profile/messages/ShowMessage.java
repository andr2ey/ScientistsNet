package controller.profile.messages;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main/message/show")
public class ShowMessage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("button_show_message");
        String email = req.getParameter("email" + index);
        String text = req.getParameter("message" + index);
        String direction = req.getParameter("direction" + index);
        req.setAttribute("email", email);
        req.setAttribute("text", text);
        req.setAttribute("direction", direction);
        req.getRequestDispatcher("/WEB-INF/main/message/show/index.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
