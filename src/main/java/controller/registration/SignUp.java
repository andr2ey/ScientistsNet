package controller.registration;

import util.Const;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

@WebServlet(urlPatterns = {"/registration"}, asyncSupported = true)
public class SignUp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //creates new user
        AsyncContext asyncContext = req.startAsync();
        asyncContext.addListener(new RegistrationListener());
        final ExecutorService threadPool = (ExecutorService) req.getServletContext().getAttribute(Const.THREAD_POOL);
        threadPool.execute(new RegistrationService(asyncContext));
    }
}
