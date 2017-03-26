package filters;

import model.University;
import util.constants.SessionConst;
import util.constants.UrlConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/**
 * Rollbacks all modified universities if
 * <code>SessionConst.UNIVERSITIES_CHANGED_KEY != null</code> and
 * request uri isn't <code>UrlConst.MAIN_EDUCATION</code>
 */
public class RollbackModifiedUniversities implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    @SuppressWarnings("unchecked")
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession();
        if (session.getAttribute(SessionConst.UNIVERSITIES_CHANGED_KEY) != null &&
                !httpReq.getRequestURI().equals(UrlConst.MAIN_EDUCATION)) {
            rollbackModifiedUniversities(
                    (List<University>) session.getAttribute(SessionConst.UNIVERSITIES_KEY),
                    (List<University>) session.getAttribute(SessionConst.UNMODIFIED_UNIVERSITIES_KEY));
            session.setAttribute(SessionConst.UNIVERSITIES_CHANGED_KEY, null);
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {}

    private void rollbackModifiedUniversities(List<University> modified, List<University> unmodified) {
        Iterator<University> iterator = modified.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            University university = iterator.next();
            if (university.isCreated()) {
                iterator.remove();
            } else if (university.isUpdated()) {
                modified.set(index, unmodified.get(index));
            }
            university.setDeleted(false);
            ++index;
        }
        Collections.fill(unmodified, null);
    }
}
