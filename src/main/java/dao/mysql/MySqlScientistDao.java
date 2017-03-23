package dao.mysql;

import dao.ScientistDao;
import model.FieldOfScience;
import model.Gender;
import model.Scientist;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MySqlScientistDao implements ScientistDao {

    //CREATE
    @SuppressWarnings("SqlResolve")
    private static final String SQL_CREATE_SCIENTIST = "INSERT INTO scientist " +
            "(s_first_name, s_second_name, s_middle_name, " +
            "s_email, s_password, s_dob, s_gender_id, s_field_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    @SuppressWarnings("SqlResolve")
    private static final String SQL_ADD_TO_ROLE = "INSERT INTO roles (s_email, r_name) VALUES (?, ?)";

    //READ
    private static final String SQL_SELECT_SCIENTIST_BY_EMAIL = "SELECT " +
            "s_id, s_first_name, s_second_name, s_middle_name, s_email, s_dob, s_gender_id, s_field_id, " +
            "g_name, f_name " +
            "FROM scientist s, gender g, field f " +
            "WHERE s_gender_id = g_id AND s_field_id = f_id AND s_email = (?)";
    @SuppressWarnings("SqlResolve")
    private static final String SQL_SELECT_SCIENTIST_BY_ID = "SELECT " +
            "s_id, s_first_name, s_second_name, s_middle_name, s_email, s_dob, s_gender_id, " +
            "g_name " +
            "FROM scientist s, gender g " +
            "WHERE s_gender_id = g_id AND s_id = (?)";
    @SuppressWarnings("SqlResolve")
    private static final String SQL_SELECT_SCIENTISTS_BY_FULL_NAME = "SELECT " +
            "s_id, s_first_name, s_second_name, s_middle_name, s_email " +
            "FROM scientist s " +
            "WHERE s_first_name = (?) AND s_second_name = (?)";
    @SuppressWarnings("SqlResolve")
    private static final String SQL_VERIFY_PASSWORD_BY_ID = "SELECT s_password " +
            "FROM scientist s WHERE s_id = (?)";

    //UPDATE
    @SuppressWarnings("SqlResolve")
    private static final String SQL_UPDATE_SCIENTIST_BY_ID = "UPDATE " +
            "scientist SET s_first_name = (?), s_second_name = (?), s_middle_name = (?), s_gender_id = (?), " +
            "s_dob = (?), s_field_id = (?), s_password = (?) " +
            "WHERE s_id = (?)";

    private DataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(MySqlScientistDao.class);

    public MySqlScientistDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Set<Scientist> getAllByFullName(String firstName, String secondName) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    SQL_SELECT_SCIENTISTS_BY_FULL_NAME, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, secondName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    Set<Scientist> scientistSet = new TreeSet<>();
                    while (resultSet.next()) {
                        Scientist scientist = new Scientist().builder()
                                .setId(resultSet.getInt("s_id"))
                                .setEmail(resultSet.getString("s_email"))
                                .setFirstName(resultSet.getString("s_first_name"))
                                .setSecondName(resultSet.getString("s_second_name"))
                                .setMiddleName(resultSet.getString("s_middle_name"))
                                .build();
                        scientistSet.add(scientist);
                    }
                    return scientistSet;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("Getting all users by (%s %s) has been unsuccessful",
                    firstName, secondName), e);
        }
        return Collections.emptySet();
    }

    @Override
    public boolean updateInfo(Scientist scientistNew) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement(SQL_UPDATE_SCIENTIST_BY_ID)) {
                preparedStatement.setString(1, scientistNew.getFirstName());
                preparedStatement.setString(2, scientistNew.getSecondName());
                preparedStatement.setString(3, scientistNew.getMiddleName());
                preparedStatement.setInt(4, scientistNew.getGender().ordinal() + 1);
                preparedStatement.setDate(5, Date.valueOf(scientistNew.getDob()));
                preparedStatement.setInt(6, scientistNew.getFieldOfScience().ordinal() + 1);
                preparedStatement.setString(7, scientistNew.getPassword());
                preparedStatement.setInt(8, scientistNew.getId());
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("Update user - %s has been unsuccessful", scientistNew), e);
        }
        return false;
    }

    @Override
    public boolean confirmPassword(int id, String password) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    SQL_VERIFY_PASSWORD_BY_ID, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next() && password.equals(resultSet.getString("s_password"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("Password confirmation of user's id = %d has been unsuccessful", id), e);
        }
        return false;
    }

    @Override
    public int create(Scientist scientist) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            try (PreparedStatement statementForScientist =
                         connection.prepareStatement(SQL_CREATE_SCIENTIST, Statement.RETURN_GENERATED_KEYS)) {
                statementForScientist.setString(1, scientist.getFirstName());
                statementForScientist.setString(2, scientist.getSecondName());
                statementForScientist.setString(3, scientist.getMiddleName());
                statementForScientist.setString(4, scientist.getEmail());
                statementForScientist.setString(5, scientist.getPassword());
                statementForScientist.setDate(6, Date.valueOf(scientist.getDob()));
                statementForScientist.setInt(7, (scientist.getGender().ordinal() + 1));
                statementForScientist.setInt(8, scientist.getFieldOfScience().ordinal() + 1);
                statementForScientist.executeUpdate();
                try (ResultSet generatedKeys = statementForScientist.getGeneratedKeys()) {
                    if (generatedKeys.next())
                        scientist.setId(generatedKeys.getInt(1));
                }
                try (PreparedStatement statementForRole = connection.prepareStatement(SQL_ADD_TO_ROLE)) {
                    statementForRole.setString(1, scientist.getEmail());
                    statementForRole.setString(2, "user");
                    statementForRole.executeUpdate();
                }
                connection.commit();
                connection.setAutoCommit(true);
                LOGGER.info(String.format("User %s registered", scientist));
                return scientist.getId();
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.error(String.format("Creation user - %s has been unsuccessful", scientist), e);
            }
        } catch (SQLException e) {
            LOGGER.error("Getting connection from data source failed", e);
        }
        return 0;
    }

    @Override
    public Scientist get(int id) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    SQL_SELECT_SCIENTIST_BY_ID)) {
                preparedStatement.setInt(1, id);
                return getScientist(preparedStatement);
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("Getting user by id = (%d) has been unsuccessful", id), e);
        }
        return null;
    }

    @Override
    public Scientist get(String email) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                     SQL_SELECT_SCIENTIST_BY_EMAIL, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)){
                preparedStatement.setString(1, email);
                return getScientist(preparedStatement);
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("Getting user by email = (%s) has been unsuccessful", email), e);
        }
        return null;
    }

    private Scientist getScientist(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                Scientist.Builder builder = new Scientist().builder()
                        .setId(resultSet.getInt("s_id"))
                        .setEmail(resultSet.getString("s_email"))
                        .setFirstName(resultSet.getString("s_first_name"))
                        .setSecondName(resultSet.getString("s_second_name"))
                        .setMiddleName(resultSet.getString("s_middle_name"))
                        .setDob(resultSet.getDate("s_dob").toLocalDate())
                        .setFieldOfScience(FieldOfScience.valueOf(resultSet.getString("f_name")));
                String gender = resultSet.getString("g_name");
                builder.setGender(gender != null ? Gender.valueOf(gender) : null);
                return builder.build();
            }
        }
        return new Scientist();
    }
}
