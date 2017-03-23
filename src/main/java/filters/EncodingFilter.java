package filters;


import util.Const;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        resp.setCharacterEncoding(Const.APP_ENCODING);
        req.setCharacterEncoding(Const.APP_ENCODING);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
    }

}
