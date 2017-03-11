package security;

import dao.ScientistDao;
import model.Scientist;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created on 08.03.2017.
 */

public class LogInFilter implements Filter {

    private static final String EMAIL_KEY = "email";
    private static final String UNREGISTERED_EMAIL_KEY = "unregistered";

    private static final String UNREGISTERED_EMAIL = "Unable to log in. Check email and password.";

    private ScientistDao scientistDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        scientistDao = (ScientistDao) filterConfig.getServletContext().getAttribute("ScientistDao");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String username = request.getRemoteUser();

        if (username != null && request.getSession().getAttribute(EMAIL_KEY) == null) {
            // first-time login
            Scientist user = scientistDao.get(username);
            if (user == null)
                request.setAttribute(UNREGISTERED_EMAIL_KEY, UNREGISTERED_EMAIL);
            else
                request.setAttribute(UNREGISTERED_EMAIL_KEY, "");
            request.getSession().setAttribute(EMAIL_KEY, user);
        }
        chain.doFilter(req, resp);
    }


    @Override
    public void destroy() {

    }

}
