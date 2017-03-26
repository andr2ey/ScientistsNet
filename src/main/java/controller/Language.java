package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/language")
public class Language extends HttpServlet {

    private static final String PATH_FROM_KEY = "pathFrom";
    private static final String LANGUAGE_KEY = "language";
    private static final String LANG_PARAM = "lang";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute(LANGUAGE_KEY, req.getParameter(LANG_PARAM));
        resp.sendRedirect(req.getParameter(PATH_FROM_KEY));
    }
}
