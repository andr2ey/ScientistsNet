package listeners;

import dao.mysql.MySqlMessageDao;
import dao.mysql.MySqlScientistDao;
import dao.mysql.MySqlUniversityDao;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import service.MessageService;
import service.ScientistService;
import service.UniversityService;
import util.Const;
import util.connection.pool.MyConnectionPool;
import util.connection.pool.MyConnectionPoolException;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;


@WebListener
public class AppInitializer implements ServletContextListener {

    //    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;
    private MyConnectionPool connectionPool;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        //TODO think about logging
        initRootLogger(servletContext);
        initConnectionPool(servletContext.getInitParameter("ConnectionPool"));
        initDB(servletContext);
        initServices(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (sce.getServletContext().getInitParameter("ConnectionPool").equals("my"))
            connectionPool.destroy();
    }

    private void initConnectionPool(String id) {
        if (id.equals("my")) {
            try {
                connectionPool = new MyConnectionPool();
                connectionPool.init();
            } catch (MyConnectionPoolException e) {
                throw new RuntimeException("Error in opening my connection pool.");
                //TODO add log
            }
            dataSource = connectionPool;
        } else {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/TestDB");
            } catch (NamingException e) {
                throw new RuntimeException("Error in opening tomcat's connection pool.");
                //TODO add log
            }
        }
    }

    private void initRootLogger(ServletContext servletContext) {
        try (FileInputStream inputStream = new FileInputStream(
                servletContext.getRealPath(servletContext.getInitParameter("log4j-config-location")))) {
            PropertyConfigurator.configure(inputStream);
        } catch (IOException e) {
            BasicConfigurator.configure();
            //TODO - add log
        }
    }

    private void initServices(ServletContext servletContext) {
        servletContext.setAttribute(Const.SCIENTIST_SERVICE,
                new ScientistService(new MySqlScientistDao(dataSource)));
        servletContext.setAttribute(Const.UNIVERSITY_SERVICE,
                new UniversityService(new MySqlUniversityDao(dataSource)));
        servletContext.setAttribute(Const.MESSAGE_SERVICE,
                new MessageService(new MySqlMessageDao(dataSource)));
    }

    private void initDB(ServletContext servletContext) {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            File sqlDirPath = new File(servletContext.getRealPath("/WEB-INF/classes/sql"));
            Pattern pattern = Pattern.compile("^\\d+\\.sql$");
            File[] filesSql = sqlDirPath.listFiles((pathname) -> pattern.matcher((pathname.getName())).find());
            if (filesSql == null)
                return;
            for (File fileSql : filesSql)
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(fileSql), Charset.forName("utf-8")))) {
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        if (line.contains(";")) {
                            statement.addBatch(sb.toString());
                            sb.delete(0, sb.length());
                        }
                    }
                    statement.executeBatch();
                }
        } catch (SQLException | IOException e) {
            //TODO add log
        }
    }
}
