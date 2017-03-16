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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 08.03.2017.
 */

public class LogInFilter implements Filter {

    private static final Logger logger = Logger.getRootLogger();
    private static final int INITIAL_CAPACITY_UNMODIFIED_UNIVERSITIES = 10;

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
        System.err.println("LogInFilter");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        String email = request.getRemoteUser();
        // first-time login
        if (email != null && request.getSession().getAttribute(Const.EMAIL_KEY) == null) {
            Scientist scientist = scientistService.get(email);
            session.setAttribute(Const.EMAIL_KEY, scientist);

            logger.info(String.format("Authorization of User (%s) ", scientist));

            loadUniversities(session, scientist.getId());
        }
        chain.doFilter(request, resp); // Just continue chain.
    }

    private void loadUniversities(HttpSession session, int scientistId) {
        //buffer for unmodified universities
        List<University> unmodifiedUniversities = new ArrayList<>(INITIAL_CAPACITY_UNMODIFIED_UNIVERSITIES);
        for (int i = 0; i < INITIAL_CAPACITY_UNMODIFIED_UNIVERSITIES; i++)
            unmodifiedUniversities.add(i, null);
        session.setAttribute(Const.UNMODIFIED_UNIVERSITIES_KEY, unmodifiedUniversities);

        List<University> universities = universityService.getAll(scientistId);
        session.setAttribute(Const.UNIVERSITIES_KEY, universities);
    }

}
