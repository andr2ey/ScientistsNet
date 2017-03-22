package filters;

import model.Scientist;
import model.University;
import org.apache.log4j.Logger;
import service.ScientistService;
import service.UniversityService;
import util.Const;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created on 08.03.2017.
 */

public class LogInFilter implements Filter {

    private ScientistService scientistService;
    private UniversityService universityService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        scientistService = (ScientistService) servletContext.getAttribute(Const.SCIENTIST_SERVICE);
        universityService = (UniversityService) servletContext.getAttribute(Const.UNIVERSITY_SERVICE);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        String email = request.getRemoteUser();
        // first-time login
        if (email != null && request.getSession().getAttribute(Const.EMAIL_KEY) == null) {
            Scientist scientist = scientistService.get(email, req.getLocale());
            session.setAttribute(Const.EMAIL_KEY, scientist);
            session.setAttribute(Const.UNIVERSITIES_CHANGED, false);
            loadUniversities(session, scientist.getId());
        }
        chain.doFilter(request, resp);
    }

    @Override
    public void destroy() {}

    @SuppressWarnings("Duplicates")
    private void loadUniversities(HttpSession session, int scientistId) {
        //buffer for unmodified universities
        List<University> unmodifiedUniversities = new ArrayList<>(Const.INITIAL_CAPACITY_UNMODIFIED_UNIVERSITIES);
        for (int i = 0; i < Const.INITIAL_CAPACITY_UNMODIFIED_UNIVERSITIES; i++)
            unmodifiedUniversities.add(i, null);
        session.setAttribute(Const.UNMODIFIED_UNIVERSITIES_KEY, unmodifiedUniversities);

        List<University> universities = universityService.getAll(scientistId);
        session.setAttribute(Const.UNIVERSITIES_KEY, universities);
    }

}
