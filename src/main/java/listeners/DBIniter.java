package listeners;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 07.03.2017.
 */

@WebListener
public class DBIniter implements ServletContextListener {

    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Pattern pattern = Pattern.compile("^\\d+\\.sql$");
        //TODO rewrite in older API
        Path sqlDirPath = Paths.get(sce.getServletContext().getContextPath(), "//sql");
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            DirectoryStream<Path> paths = Files.newDirectoryStream(sqlDirPath);
            for (Path filePath : paths) {
                Matcher matcher = pattern.matcher(filePath.toFile().getName());
                if(matcher.find()) {
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(filePath.toFile())))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            statement.addBatch(line);
                        }
                        statement.executeBatch();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("connection to DB is invalid");
            //TODO
        } catch (IOException e) {
            throw new RuntimeException("connection to sql directory is invalid");
            //TODO
        }
    }
}
