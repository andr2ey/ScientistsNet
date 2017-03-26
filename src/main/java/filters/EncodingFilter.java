package filters;


import util.constants.AppConst;

import javax.servlet.*;
import java.io.IOException;

/**
 * Sets encoding for all requests and response
 */

public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        resp.setCharacterEncoding(AppConst.UTF_8);
        req.setCharacterEncoding(AppConst.UTF_8);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }

}
