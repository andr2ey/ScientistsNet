package listeners;

import org.apache.log4j.Logger;
import util.Const;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@WebListener
public class ExecutorInitializer implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(ExecutorInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        final ExecutorService threadPool = Executors.newFixedThreadPool(Const.NUMBER_OF_THREADS);
        logger.info("Thread pool initialized");
        servletContext.setAttribute(Const.THREAD_POOL, threadPool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        final ExecutorService threadPool = (ExecutorService) sce.getServletContext().getAttribute(Const.THREAD_POOL);
        threadPool.shutdown();
    }
}