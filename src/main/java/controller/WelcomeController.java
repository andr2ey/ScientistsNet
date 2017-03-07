package controller;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Created on 07.03.2017.
 */
@WebServlet("/")
public class WelcomeController extends HttpServlet {

    public static String WELCOME_KEY = "welcome";

    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = (HttpSession) request.getSession().getAttribute("FIRST_NAME_KEY");
        String welcome = Optional.ofNullable(request.getSession().getAttribute("FIRST_NAME_KEY"))
                .map(o -> String.format("Hello, %s!", o))
                .orElse("Hello!");
        request.setAttribute(WELCOME_KEY, welcome);



        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
}