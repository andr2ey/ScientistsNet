package controller.profile.info;

import model.Scientist;
import util.Const;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Created on 15.03.2017.
 */
@WebServlet("/baseinfo")
public class ProfileEditor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("ProfileEditor");
        LocalDate localDate = ((Scientist)req.getSession().getAttribute(Const.EMAIL_KEY)).getDob();
        req.setAttribute("month", localDate.getMonthValue());
        req.setAttribute("year", localDate.getYear());
        req.setAttribute("day", localDate.getDayOfMonth());
        req.setAttribute("m"+localDate.getMonthValue(), "selected");
        setYearsLimits(req);
        req.getRequestDispatcher("WEB-INF/main/baseinfo/index.jsp").forward(req, resp);
    }

    private void setYearsLimits(HttpServletRequest req) {
        LocalDate localDate = LocalDate.now();
        req.setAttribute(Const.MAX_YEAR_KEY, (localDate.getYear() - Const.BOTTOM_EDGE_OF_AGE) );
        req.setAttribute(Const.MIN_YEAR_KEY, (localDate.getYear() - Const.TOP_EDGE_OF_AGE));
    }
}
