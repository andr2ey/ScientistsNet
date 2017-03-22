package filters;

import model.University;
import util.Const;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class EducationProcessFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        if (!httpReq.getRequestURI().equals("/main/education")) {
            //TODO add clear buffer
            //noinspection unchecked
            List<University> universityList =
                    (List<University>) httpReq.getSession().getAttribute(Const.UNIVERSITIES_KEY);
            //noinspection unchecked
            List<University> unmodifiedList =
                    (List<University>) httpReq.getSession().getAttribute(Const.UNMODIFIED_UNIVERSITIES_KEY);
            processDeletedCreatedUniversities(universityList, unmodifiedList);
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    private void processDeletedCreatedUniversities(List<University> universityList, List<University> unmodifiedList) {
        Iterator<University> iterator = universityList.iterator();
        //noinspection Duplicates
        int index = 0;
        while (iterator.hasNext()) {
            University university = iterator.next();
            if (university.isCreated()) {
                iterator.remove();
                ++index;
                continue;
            }
            if (university.isUpdated()) {
                universityList.set(index, unmodifiedList.get(index));
                ++index;
                continue;
            }
            ++index;
            university.setDeleted(false);
        }
        for (int i = 0; i < unmodifiedList.size(); i++) {
            unmodifiedList.set(i, null);
        }
    }
}
