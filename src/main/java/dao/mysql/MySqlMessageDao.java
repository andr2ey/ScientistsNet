package dao.mysql;

import dao.MessageDao;
import model.Degree;
import model.Message;
import model.Scientist;
import model.University;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;


public class MySqlMessageDao implements MessageDao{

    //CREATE
    @SuppressWarnings("SqlResolve")
    private static final String SQL_CREATE_MESSAGE = "INSERT INTO messages (m_from, m_to, " +
            "m_text, m_date, m_time) " +
            "VALUES (?, ?, ?, ?, ?)";

    //READ
    @SuppressWarnings("SqlResolve")
    private static final String SQL_SELECT_MESSAGES_BY_FROM_OR_TO = "SELECT m_id, m_from, m_to, " +
            "m_text, m_date, m_time " +
            "FROM messages m " +
            "WHERE m_from = (?) OR m_to = (?)";

    private DataSource dataSource;

    public MySqlMessageDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Set<Message> getAll(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SQL_SELECT_MESSAGES_BY_FROM_OR_TO, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Set<Message> messagesSet = new TreeSet<>();
                while (resultSet.next()) {
                    System.err.println("HERE GET ALL");
                    Message message = new Message().builder()
                            .setId(resultSet.getInt("m_id"))
                            .setFrom(resultSet.getString("m_from"))
                            .setTo(resultSet.getString("m_to"))
                            .setTxt(resultSet.getString("m_text"))
                            .setLocalDate(resultSet.getDate("m_date").toLocalDate())
                            .setLocalTime(resultSet.getTime("m_time").toLocalTime())
                            .build();
                    messagesSet.add(message);
                }
                return messagesSet;
            }
        } catch (SQLException e) {
            return Collections.emptySet();
        }
    }

    @Override
    public boolean create(Message message) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement prepareStatement =
                     connection.prepareStatement(SQL_CREATE_MESSAGE)) {
            prepareStatement.setString(1, message.getFrom());
            prepareStatement.setString(2, message.getTo());
            prepareStatement.setString(3, message.getTxt());
            prepareStatement.setDate(4, Date.valueOf(message.getLocalDate()));
            prepareStatement.setTime(5, Time.valueOf(message.getLocalTime()));
            return prepareStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            return false;
        }
    }
}
