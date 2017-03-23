package controller.registration;

import model.Scientist;
import util.AsyncListenerAdapter;
import util.Const;

import javax.servlet.AsyncEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class RegistrationListener extends AsyncListenerAdapter {

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        HttpServletRequest req = (HttpServletRequest) event.getSuppliedRequest();
        HttpServletResponse resp = (HttpServletResponse) event.getSuppliedResponse();
        try {
            if (req.getAttribute(Const.VALID_USER_KEY) == null || req.getAttribute(Const.CREATED_USER_KEY) == null) {
                setYearsLimits(req);
                req.getRequestDispatcher(Const.REGISTRATION_PAGE_FULL_PATH).forward(req, resp);
            } else {
                Scientist scientist = (Scientist) req.getAttribute(Const.VALID_USER_KEY);
                req.login(scientist.getEmail(), (String) req.getAttribute("clearPassword"));
                req.setAttribute("clearPassword", null);
                resp.sendRedirect(Const.MAIN_PAGE);
            }
        } catch (ServletException e) {
            //TODO add log
        }
    }

    private void setYearsLimits(HttpServletRequest req) {
        LocalDate localDate = LocalDate.now();
        req.setAttribute(Const.MAX_YEAR_KEY, (localDate.getYear() - Const.BOTTOM_EDGE_OF_AGE));
        req.setAttribute(Const.MIN_YEAR_KEY, (localDate.getYear() - Const.TOP_EDGE_OF_AGE));
    }
}
