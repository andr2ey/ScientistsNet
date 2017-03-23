package util.connection.pool;

public class MyConnectionPoolException extends Exception {
    MyConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
