package listeners;

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
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;


@WebListener
public class DBandLoggerInit implements ServletContextListener {

    private static Logger rootLogger;

    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        //TODO think about logging
        rootLogger = initRootLogger(servletContext);
        initDB(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private Logger initRootLogger(ServletContext servletContext) {
        Logger rootLogger = Logger.getRootLogger();
        try(FileInputStream inputStream = new FileInputStream(
                servletContext.getRealPath("/WEB-INF/classes/log4j.properties"))) {
            PropertyConfigurator.configure(inputStream);
            rootLogger.info("PropertyConfigurator worked successful");
        } catch (IOException e) {
            BasicConfigurator.configure();
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
        } catch (SQLException e) {
            rootLogger.fatal("SQL query in initDB is invalid", e);
        } catch (IOException e) {
            rootLogger.fatal("Connection to sql directory is invalid", e);
        }
    }
}
