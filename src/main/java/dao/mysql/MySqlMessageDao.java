package dao.mysql;

import dao.MessageDao;
import model.Message;
import org.apache.log4j.Logger;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;


public class MySqlMessageDao implements MessageDao {

    //CREATE
    @SuppressWarnings("SqlResolve")
    private static final String SQL_CREATE_MESSAGE = "INSERT INTO messages (m_from, m_to, " +
            "m_text, m_datetime) " +
            "VALUES (?, ?, ?, ?)";

    //READ
    @SuppressWarnings("SqlResolve")
    private static final String SQL_SELECT_MESSAGES_BY_FROM_OR_TO = "SELECT m_id, m_from, m_to, " +
            "m_text, m_datetime " +
            "FROM messages m " +
            "WHERE m_from = (?) OR m_to = (?)";

    private DataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(MySqlMessageDao.class);

    public MySqlMessageDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Set<Message> getAll(String email) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    SQL_SELECT_MESSAGES_BY_FROM_OR_TO, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    Set<Message> messagesSet = new TreeSet<>();
                    while (resultSet.next()) {
                        Message message = new Message().builder()
                                .setId(resultSet.getInt("m_id"))
                                .setFrom(resultSet.getString("m_from"))
                                .setTo(resultSet.getString("m_to"))
                                .setTxt(resultSet.getString("m_text"))
                                .setLocalDateTime(resultSet.getTimestamp("m_datetime").toLocalDateTime())
                                .build();
                        messagesSet.add(message);
                    }
                    return messagesSet;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("Getting all message by email (%s)", email), e);
        }
        return Collections.emptySet();
    }

    @Override
    public boolean create(Message message) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement prepareStatement =
                     connection.prepareStatement(SQL_CREATE_MESSAGE)) {
            prepareStatement.setString(1, message.getFrom());
            prepareStatement.setString(2, message.getTo());
            prepareStatement.setString(3, message.getTxt());
            prepareStatement.setTimestamp(4, Timestamp.valueOf(message.getLocalDateTime()));
            return prepareStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.error(String.format("Creating message - (%s) failed", message), e);
        }
        return false;
    }
}
