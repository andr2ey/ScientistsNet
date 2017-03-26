package filters;

import controller.profile.education.EducationConst;
import model.Scientist;
import model.University;
import org.apache.log4j.Logger;
import service.ScientistService;
import service.UniversityService;
import util.constants.AppConst;
import util.constants.SessionConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Register scientist in session
 */
public class LogInFilter implements Filter {

    private ScientistService scientistService;
    private UniversityService universityService;
    private static final Logger LOGGER = Logger.getLogger(LogInFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        scientistService = (ScientistService) servletContext.getAttribute(AppConst.SCIENTIST_SERVICE);
        universityService = (UniversityService) servletContext.getAttribute(AppConst.UNIVERSITY_SERVICE);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        String email = request.getRemoteUser();
        // first-time login
        if (email != null && request.getSession().getAttribute(SessionConst.EMAIL_KEY) == null) {
            Scientist scientist = scientistService.get(email);
            session.setAttribute(SessionConst.EMAIL_KEY, scientist);
            loadUniversities(session, scientist.getId());
            LOGGER.info(String.format("User - (%s) is successfully authorized", scientist));
        }
        chain.doFilter(request, resp);
    }

    @Override
    public void destroy() {}

    @SuppressWarnings("Duplicates")
    private void loadUniversities(HttpSession session, int scientistId) {
        //buffer for unmodified universities
        List<University> unmodifiedUniversities = new ArrayList<>(EducationConst.MAX_UNIVERSITIES);
        for (int i = 0; i < EducationConst.MAX_UNIVERSITIES; i++)
            unmodifiedUniversities.add(i, null);
        session.setAttribute(SessionConst.UNMODIFIED_UNIVERSITIES_KEY, unmodifiedUniversities);
        session.setAttribute(SessionConst.UNIVERSITIES_CHANGED_KEY, null);

        List<University> universities = universityService.getAll(scientistId);
        session.setAttribute(SessionConst.UNIVERSITIES_KEY, universities);
    }

}
