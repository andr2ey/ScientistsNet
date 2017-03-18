package dao.mysql;

import dao.ScientistDao;
import model.Gender;
import model.Scientist;
import org.apache.log4j.Logger;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created on 07.03.2017.
 */
public class MySqlScientistDao implements ScientistDao {

    //CREATE
    @SuppressWarnings("SqlResolve")
    private static final String SQL_CREATE_SCIENTIST = "INSERT INTO scientist (s_first_name, s_second_name, s_middle_name, " +
            "s_email, s_password, s_dob, s_gender_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    @SuppressWarnings("SqlResolve")
    private static final String SQL_ADD_TO_ROLE = "INSERT INTO roles (s_email, r_name) VALUES (?, ?)";

    //READ
    private static final String SQL_SELECT_ALL_SCIENTISTS = "SELECT " +
            "s_id, s_first_name, s_second_name, s_middle_name, s_email, s_dob, s_gender_id, " +
            "g_name " +
            "FROM scientist s, gender g " +
            "WHERE s_gender_id = g_id";
    private static final String SQL_SELECT_SCIENTIST_BY_EMAIL = "SELECT " +
            "s_id, s_first_name, s_second_name, s_middle_name, s_email, s_dob, s_gender_id, " +
            "g_name " +
            "FROM scientist s, gender g " +
            "WHERE s_gender_id = g_id AND s_email = (?)";
//    private static final String SQL_SELECT_EXISTING_SCIENTIST_BY_EMAIL = "SELECT s_email FROM scientist s WHERE s_email = (?)";
    private static final String SQL_SELECT_SCIENTIST_BY_ID = "SELECT " +
            "s_id, s_first_name, s_second_name, s_middle_name, s_email, s_dob, s_gender_id, " +
            "g_name " +
            "FROM scientist s, gender g " +
            "WHERE s_gender_id = g_id AND s_id = (?)";
    @SuppressWarnings("SqlResolve")
    private static final String SQL_VERIFY_PASSWORD_BY_ID= "SELECT s_password " +
            "FROM scientist s WHERE s_id = (?)";

    //UPDATE
    private static final String SQL_UPDATE_TEST= "INSERT INTO scientist(s_first_name , s_second_name," +
            "s_middle_name, s_gender_id, s_dob, s_email, s_password) VALUES (?,?,?,?,?,?,?)" +
            "ON DUPLICATE KEY UPDATE s_email = ?";
    @SuppressWarnings("SqlResolve")
    private static final String SQL_UPDATE_SCIENTIST_BY_ID = "UPDATE " +
            "scientist SET s_first_name = (?), s_second_name = (?), s_middle_name = (?), s_gender_id = (?), " +
            "s_dob = (?), s_email = (?), s_password = (?) " +
            "WHERE s_id = (?)";
    @SuppressWarnings("SqlResolve")
    private static final String SQL_UPDATE_ROLE_BY_EMAIL = "UPDATE roles SET s_email = (?) WHERE s_email = (?)";

    //DELETE
//    private final static String SQL_REMOVE_SCEINTIST = "DELETE FROM Scientist WHERE";

    //TODO how does it work?
    //TODO create own connection pool
    private DataSource dataSource;
    private final Logger logger = Logger.getRootLogger();

    public MySqlScientistDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean updateInfo(Scientist scientistNew, String email) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            System.err.println("in connection");

            try (PreparedStatement preparedStatementRoles =
                         connection.prepareStatement(SQL_UPDATE_ROLE_BY_EMAIL)) {
                preparedStatementRoles.setString(1, scientistNew.getEmail());
                preparedStatementRoles.setString(2, email);
                System.err.println("in preparedStatementRoles");
                System.err.println(preparedStatementRoles.executeUpdate());
                try (PreparedStatement preparedStatement =
                             connection.prepareStatement(SQL_UPDATE_SCIENTIST_BY_ID)) {
                    preparedStatement.setString(1, scientistNew.getFirstName());
                    preparedStatement.setString(2, scientistNew.getSecondName());
                    preparedStatement.setString(3, scientistNew.getMiddleName());
                    preparedStatement.setInt(4, scientistNew.getGender().ordinal() + 1);
                    preparedStatement.setDate(5, Date.valueOf(scientistNew.getDob()));
                    preparedStatement.setString(6, scientistNew.getEmail());
                    preparedStatement.setString(7, scientistNew.getPassword());
                    preparedStatement.setInt(8, scientistNew.getId());
                    System.err.println("in preparedStatement before");
                    System.err.println(preparedStatement.executeUpdate());
                    System.err.println("in preparedStatement after");
                }
                connection.commit();
                System.err.println("connection.commit()");
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException e) {
                System.err.println("SQLException");
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            System.err.println("SQLException");
            return false;
        }
    }

    @Override
    public boolean confirmPassword(int id, String password) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SQL_VERIFY_PASSWORD_BY_ID)) {
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() && password.equals(resultSet.getString("s_password"));
            }
        } catch (SQLException e) {
            logger.error(String.format("Confirm password by id = (%d) has been unsuccessful", id), e);
            return false;
        }
    }

    @Override
    public int create(Scientist scientist) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statementForScientist =
                     connection.prepareStatement(SQL_CREATE_SCIENTIST, Statement.RETURN_GENERATED_KEYS)) {
            statementForScientist.setString(1, scientist.getFirstName());
            statementForScientist.setString(2, scientist.getSecondName());
            statementForScientist.setString(3, scientist.getMiddleName());
            statementForScientist.setString(4, scientist.getEmail());
            statementForScientist.setString(5, scientist.getPassword());
            LocalDate localDate = scientist.getDob();
            statementForScientist.setDate(6, Date.valueOf(localDate));
            Gender gender = scientist.getGender();
            statementForScientist.setInt(7,
                    (gender == null ? Gender.NONE.ordinal()+1 : gender.ordinal()+1));
            int count = statementForScientist.executeUpdate();

            try (ResultSet generatedKeys = statementForScientist.getGeneratedKeys()) {
                if (generatedKeys.next())
                    scientist.setId(generatedKeys.getInt(1));
            }
            try(PreparedStatement statementForRole = connection.prepareStatement(SQL_ADD_TO_ROLE)) {
                statementForRole.setString(1, scientist.getEmail());
                statementForRole.setString(2, "user");
                statementForRole.executeUpdate();
            }
        } catch (SQLException e) {
            logger.fatal(String.format("User (%s) hasn't created", scientist), e);
            return 0;
        }
        return scientist.getId();
    }

    @Override
    public Scientist get(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SQL_SELECT_SCIENTIST_BY_ID)) {
             preparedStatement.setInt(1, id);
            return getScientist(preparedStatement);
        } catch (SQLException e) {
            logger.error(String.format("Getting user by id = (%d) has been unsuccessful", id), e);
        }
        return null;
    }

    @Override
    public Scientist get(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SQL_SELECT_SCIENTIST_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            return getScientist(preparedStatement);
        } catch (SQLException e) {
            logger.error(String.format("Getting user by email = (%s) has been unsuccessful", email), e);
        }
        return null;
    }

    @Override
    public List<Scientist> getAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SQL_SELECT_ALL_SCIENTISTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            ArrayList<Scientist> list = new ArrayList<>(resultSet.getRow());
            while (resultSet.next()) {
                Scientist.Builder builder = new Scientist().builder()
                        .setId(resultSet.getInt("s_id"))
//                        .setPassword(resultSet.getString("s_password")) //TODO think about safety
                        .setEmail( resultSet.getString("s_email"))
                        .setFirstName(resultSet.getString("s_first_name"))
                        .setSecondName(resultSet.getString("s_second_name"))
                        .setMiddleName(resultSet.getString("s_middle_name"));
                Date date = resultSet.getDate("s_dob");
                builder.setDob(date != null ? date.toLocalDate() : null);
                String gender = resultSet.getString("g_name");
                builder.setGender(gender != null ? Gender.valueOf(gender) : null);
                list.add(builder.build());
            }
            return list;
        } catch (SQLException e) {
            logger.error("Getting all users has been unsuccessful", e);
        }
        return Collections.emptyList();
    }

    private Scientist getScientist(PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                Scientist.Builder builder = new Scientist().builder()
                        .setId(resultSet.getInt("s_id"))
//                        .setPassword(resultSet.getString("s_password")) //TODO think about safety
                        .setEmail( resultSet.getString("s_email"))
                        .setFirstName(resultSet.getString("s_first_name"))
                        .setSecondName(resultSet.getString("s_second_name"))
                        .setMiddleName(resultSet.getString("s_middle_name"));
                Date date = resultSet.getDate("s_dob");
                builder.setDob(date != null ? date.toLocalDate() : null);
                String gender = resultSet.getString("g_name");
                builder.setGender(gender != null ? Gender.valueOf(gender) : null);
                return builder.build();
            }
        }
        logger.fatal("Scientist model hasn't created");
        return new Scientist();
    }
}
