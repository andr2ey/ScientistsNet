package filters.registration;

import util.Const;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class EntranceFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        if (userAuthorized(httpReq)) {
            req.getRequestDispatcher(Const.MAIN_PAGE_FULL_PATH).forward(req, resp);
        } else if (fieldsFilled(httpReq)) {
            req.getRequestDispatcher(Const.REGISTRATION_PAGE_FULL_PATH).forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {}

    private boolean fieldsFilled(HttpServletRequest req) {
        String emailNew = req.getParameter("emailNew");
        return emailNew == null || emailNew.isEmpty();
    }

    private boolean userAuthorized(HttpServletRequest req) {
        return req.getSession().getAttribute(Const.EMAIL_KEY) != null;
    }
}
