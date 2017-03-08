package controller;

import dao.ScientistDao;
import model.Scientist;

import javax.annotation.Resource;
import javax.security.sasl.RealmCallback;
import javax.servlet.ServletConfig;
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
public class Welcome extends HttpServlet {

    public static final String WELCOME_KEY = "welcome";
    private final String EMAIL_KEY = "email";

    private ScientistDao scientistDao;

    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;


    @Override
    public void init(ServletConfig config) throws ServletException{
        scientistDao = (ScientistDao) config.getServletContext().getAttribute("ScientistDao");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Scientist user = (Scientist)session.getAttribute(EMAIL_KEY);
        if (user == null) {
            request.getRequestDispatcher("WEB-INF/signup/index.jsp").forward(request, response);
        } else {
            //TODO go to database with FIRST_NAME_KEY and create main page
            request.getRequestDispatcher("WEB-INF/main/index.jsp").forward(request, response);
        }

//        String welcome = Optional.ofNullable(request.getSession().getAttribute("FIRST_NAME_KEY"))
//                .map(o -> String.format("Hello, %s!", o))
//                .orElse("Hello!");
//        request.setAttribute(WELCOME_KEY, welcome);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
