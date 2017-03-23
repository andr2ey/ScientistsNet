package util.connection.pool;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * Created on 23.03.2017.
 */
public class MyConnectionPool implements DataSource{

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    public MyConnectionPool() {
        ResourceBundle bundle = ResourceBundle.getBundle("db.mysql");
        driverName = bundle.getString("mysql.driver");
        url = bundle.getString("mysql.url");
        user = bundle.getString("mysql.user");
        password = bundle.getString("mysql.password");
        poolSize = Integer.parseInt(bundle.getString("mysql.poolSize"));
    }

    public void initPoolData() throws MyConnectionPoolException {
        try{
            Class.forName(driverName);
            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                PooledConnection pooledConnection = new PooledConnection(
                        connection, connectionQueue, givenAwayConQueue);
                connectionQueue.add(pooledConnection);
            }
        } catch (ClassNotFoundException e) {
            throw new MyConnectionPoolException("Loading db driver is fail", e);
        } catch (SQLException e) {
            throw new MyConnectionPoolException("SQLException in MyConnectionPool", e);
        }
    }

    public void dispose() {
        try {
            clearConnectionQueue(givenAwayConQueue);
            clearConnectionQueue(connectionQueue);
        } catch (SQLException e) {
            //TODO add log
        }
    }

    private void clearConnectionQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).ensureClose();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            Connection connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
            return connection;
        } catch (InterruptedException e) {
            throw new SQLException("Error connecting to the data source.", e);
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException();
    }
}
