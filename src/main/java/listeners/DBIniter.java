package listeners;

import dao.mysql.MySqlScientistDao;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Created on 07.03.2017.
 */

@WebListener
public class DBIniter implements ServletContextListener {

    private static final String SCIENTIST_DAO = "ScientistDao";
    private static final String ROOT_LOGGER = "rootLogger";
    private static Logger rootLogger;

    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        //TODO think about logging
        rootLogger = initRootLogger(servletContext);
        servletContext.setAttribute(ROOT_LOGGER, rootLogger);
        servletContext.setAttribute(SCIENTIST_DAO,
                new MySqlScientistDao(dataSource, rootLogger));
        initDB(sce);
    }

    private Logger initRootLogger(ServletContext servletContext) {
        Logger rootLogger = Logger.getRootLogger();
        try(FileInputStream inputStream = new FileInputStream(
                servletContext.getRealPath("/WEB-INF/classes/log4j.properties"))) {
            PropertyConfigurator.configure(inputStream);
            rootLogger.setAdditivity(true);
            rootLogger.info("PropertyConfigurator worked successful");
        } catch (IOException e) {
            BasicConfigurator.configure();
            rootLogger.setAdditivity(true);
            rootLogger.info("BasicConfigurator worked successful", e);
        }
        rootLogger.setLevel(Level.INFO);
        return rootLogger;
    }

    private void initDB(ServletContextEvent sce) {
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            File sqlDirPath = new File(sce.getServletContext().getRealPath("/WEB-INF/classes/sql"));
            Pattern pattern = Pattern.compile("^\\d+\\.sql$");
            File[] filesSql = sqlDirPath.listFiles((pathname) -> pattern.matcher((pathname.getName())).find());
            if (filesSql == null)
                return;
            for (File fileSql : filesSql)
                try (BufferedReader reader = new BufferedReader(new FileReader(fileSql))) {
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
        } catch (SQLException e) {
            rootLogger.warn("SQL query in initDB is invalid", e);
        } catch (IOException e) {
            rootLogger.warn("Connection to sql directory is invalid", e);
        }
    }
}
