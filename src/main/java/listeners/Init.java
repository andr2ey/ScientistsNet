package listeners;

import dao.mysql.MySqlScientistDao;
import dao.mysql.MySqlUniversityDao;
import service.ScientistService;
import service.UniversityService;
import util.Const;

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

    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute(Const.SCIENTIST_SERVICE, new ScientistService(new MySqlScientistDao(dataSource)));
        servletContext.setAttribute(Const.UNIVERSITY_SERVICE, new UniversityService(new MySqlUniversityDao(dataSource)));


    }
}