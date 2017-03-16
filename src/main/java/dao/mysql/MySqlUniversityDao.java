package dao.mysql;

import dao.UniversityDao;
import model.Degree;
import model.University;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created on 13.03.2017.
 */
public class MySqlUniversityDao implements UniversityDao {

    //CREATE
    private static final String SQL_CREATE_UNIVERSITY = "INSERT INTO university (u_scientist_id, u_country, u_city, " +
            "u_full_name, u_degree_id)" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_ADD_TO_ROLE = "INSERT INTO roles (s_email, r_name) VALUES (?, ?)";

    //READ
    private static final String SQL_SELECT_ALL_UNIVERSITIES_BY_USER_ID = "SELECT " +
            "u_id, u_scientist_id, u_country, u_city, u_full_name, u_degree_id, d_id, d_name " +
            "FROM university u, degree d " +
            "WHERE u_degree_id = d_id AND u_scientist_id = (?)";
    private static final String SQL_SELECT_SCIENTIST_BY_ID = "SELECT " +
            "s_id, s_first_name, s_second_name, s_middle_name, s_email, s_dob, s_gender_id, " +
            "g_name \n" +
            "FROM scientist s, gender g\n" +
            "WHERE s_gender_id = g_id AND s_id = (?)";

    //UPDATE
    private static final String SQL_UPDATE_UNIVERSITY = "UPDATE " +
            "university SET u_country = (?), u_city = (?), " +
            "u_full_name = (?), u_degree_id = (?)" +
            "WHERE u_id = (?)";

    //DELETE
    private static final String SQL_REMOVE_UNIVERSITY = "DELETE FROM university WHERE u_id = (?)";

    private DataSource dataSource;
    private Logger logger = Logger.getRootLogger();

    public MySqlUniversityDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<University> getAll(int scientistId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_UNIVERSITIES_BY_USER_ID)) {
            preparedStatement.setInt(1, scientistId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                ArrayList<University> list = new ArrayList<>(resultSet.getRow());
                while (resultSet.next()) {
                    University.Builder builder = new University().builder()
                            .setCity(resultSet.getString("u_city"))
                            .setId(resultSet.getInt("u_id"))
                            .setScientistId(scientistId)
                            .setCountry(resultSet.getString("u_country"))
                            .setFullName(resultSet.getString("u_full_name"))
                            .setDegree(Degree.values()[resultSet.getInt("u_degree_id") - 1]);
                    list.add(builder.build());

                }
                return list;
            }
        } catch (SQLException e) {
            logger.error("Getting all university by scientistId has been unsuccessful", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteAll(List<University> listToDelete) {
        try (Connection connection = dataSource.getConnection()) {
            int transactionLevel = connection.getTransactionIsolation();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    SQL_REMOVE_UNIVERSITY)) {
                for (University university : listToDelete) {
                    preparedStatement.setInt(1, university.getId());
                    preparedStatement.executeUpdate();
                }
                connection.commit();
            }
            connection.setAutoCommit(true);
            connection.setTransactionIsolation(transactionLevel);
        } catch (SQLException e) {
            logger.error("Deleting all universities by id has been unsuccessful", e);
        }
    }

    @Override
    public boolean transactionCUD(List<University> listDeleted,
                                  List<University> listCreated,
                                  List<University> listUpdated) {
        try (Connection connection = dataSource.getConnection()) {
            int transactionLevel = connection.getTransactionIsolation();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    SQL_REMOVE_UNIVERSITY)) {
                for (University university : listDeleted) {
                        preparedStatement.setInt(1, university.getId());
                        preparedStatement.executeUpdate();
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    SQL_CREATE_UNIVERSITY, Statement.RETURN_GENERATED_KEYS)) {
                for (University university : listCreated) {
                        preparedStatement.setInt(1, university.getScientistId());
                        preparedStatement.setString(2, university.getCountry());
                        preparedStatement.setString(3, university.getCity());
                        preparedStatement.setString(4, university.getFullName());
                        preparedStatement.setInt(5, university.getDegree().ordinal()+1);
                        preparedStatement.executeUpdate();
                        university.setCreated(false);
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next())
                            university.setId(generatedKeys.getInt(1));
                    }
                }
            }
            /*"UPDATE " +
            "university SET u_country = (?), u_city = (?), " +
            "u_full_name = (?), u_degree_id = (?)" +
            "WHERE u_id = (?)";*/
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    SQL_UPDATE_UNIVERSITY)) {
                for (University university : listUpdated) {
                    preparedStatement.setString(1, university.getCountry());
                    preparedStatement.setString(2, university.getCity());
                    preparedStatement.setString(3, university.getFullName());
                    preparedStatement.setInt(4, university.getDegree().ordinal()+1);
                    preparedStatement.setInt(5, university.getId());
                    preparedStatement.executeUpdate();
                    university.setUpdated(false);
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            connection.setTransactionIsolation(transactionLevel);
            return true;
        } catch (SQLException e) {
            logger.error("Deleting all universities by id has been unsuccessful", e);
            return false;
        }
    }
}
