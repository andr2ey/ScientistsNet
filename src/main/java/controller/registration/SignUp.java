package controller.registration;

import org.apache.log4j.Logger;
import util.Const;
import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

@WebServlet(urlPatterns = "/registration", asyncSupported = true)
public class SignUp extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SignUp.class);
    private ExecutorService threadPool;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        threadPool = (ExecutorService) config.getServletContext().getAttribute(Const.THREAD_POOL);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //creates new user
        AsyncContext asyncContext = req.startAsync();
        asyncContext.addListener(new RegistrationListener());
        threadPool.execute(new RegistrationService(asyncContext));
    }
}
