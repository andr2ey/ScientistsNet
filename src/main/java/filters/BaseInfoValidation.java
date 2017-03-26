package filters;

import controller.profile.baseinfo.BaseInfoConst;
import controller.registration.RegistrationConst;
import model.Scientist;
import security.ScientistValidator;
import util.constants.SessionConst;
import util.constants.UrlConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Validates filled fields
 */
public class BaseInfoValidation implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        ScientistValidator validator = new ScientistValidator();
        if (req.getParameter(BaseInfoConst.BUTTON_UPDATE_SCIENTIST) == null ||
                !validator.validateBaseInfoFields(httpReq)) {
            setDobAndFieldOfScience(httpReq);
            setYearLimits(httpReq);
            req.getRequestDispatcher(UrlConst.WEB_INF_MAIN_BASEINFO).forward(req, resp);
            return;
        }
        httpReq.setAttribute(BaseInfoConst.SCIENTIST_VALIDATOR_KEY, validator);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }

    @SuppressWarnings("Duplicates")
    private void setDobAndFieldOfScience(HttpServletRequest req) {
        Scientist scientist = (Scientist)req.getSession().getAttribute(SessionConst.EMAIL_KEY);
        LocalDate localDate = scientist.getDob();
        req.setAttribute(BaseInfoConst.MONTH_VALUE, localDate.getMonthValue());
        req.setAttribute(BaseInfoConst.YEAR_VALUE, localDate.getYear());
        req.setAttribute(BaseInfoConst.DAY_VALUE, localDate.getDayOfMonth());
        req.setAttribute("m"+localDate.getMonthValue(), BaseInfoConst.SELECTED);
        req.setAttribute("f"+scientist.getFieldOfScience().ordinal(), BaseInfoConst.SELECTED);
    }

    private void setYearLimits(HttpServletRequest req) {
        LocalDate localDate = LocalDate.now();
        req.setAttribute(RegistrationConst.MAX_YEAR_KEY, (localDate.getYear() - RegistrationConst.BOTTOM_EDGE_OF_AGE) );
        req.setAttribute(RegistrationConst.MIN_YEAR_KEY, (localDate.getYear() - RegistrationConst.TOP_EDGE_OF_AGE));
    }

}
