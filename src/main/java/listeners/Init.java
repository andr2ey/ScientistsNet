package listeners;

import dao.mysql.MySqlScientistDao;
import dao.mysql.MySqlUniversityDao;
import service.UniversityService;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

/**
 * Created on 13.03.2017.
 */

@WebListener
public class Init implements ServletContextListener {

    private static final String SCIENTIST_DAO = "ScientistDao";
    private static final String UNIVERSITY_SERVICE = "UniversityService";

    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute(SCIENTIST_DAO, new MySqlScientistDao(dataSource));
        servletContext.setAttribute(UNIVERSITY_SERVICE, new UniversityService(new MySqlUniversityDao(dataSource)));
    }
}
