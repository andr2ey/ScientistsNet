package filters;

import controller.registration.RegistrationConst;
import security.ScientistValidator;
import util.constants.AppConst;
import util.constants.SessionConst;
import util.constants.UrlConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;


/**
 * Validates filled fields and verifies already authorized users
 */
public class RegistrationValidation implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        //verifies authorization
        if (httpReq.getSession().getAttribute(SessionConst.EMAIL_KEY) != null) {
            req.getRequestDispatcher(UrlConst.WEB_INF_MAIN).forward(req, resp);
        }
        ScientistValidator validator = new ScientistValidator();
        //validate filled fields
        if (!validator.validateRegistrationFields(httpReq)) {
            setYearRestriction(httpReq); //TODO add it to view
            req.getRequestDispatcher(UrlConst.WEB_INF_REGISTRATION).forward(req, resp);
            return;
        }
        httpReq.setAttribute(RegistrationConst.SCIENTIST_VALIDATOR_KEY, validator);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {}

    private void setYearRestriction(HttpServletRequest req) {
        LocalDate localDate = LocalDate.now();
        req.setAttribute(RegistrationConst.MAX_YEAR_KEY, (localDate.getYear() - RegistrationConst.BOTTOM_EDGE_OF_AGE));
        req.setAttribute(RegistrationConst.MIN_YEAR_KEY, (localDate.getYear() - RegistrationConst.TOP_EDGE_OF_AGE));
    }
}
