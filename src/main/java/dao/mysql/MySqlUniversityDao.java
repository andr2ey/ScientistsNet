package dao.mysql;

import dao.UniversityDao;
import model.Degree;
import model.University;

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
    @SuppressWarnings("SqlResolve")
    private static final String SQL_CREATE_UNIVERSITY = "INSERT INTO university (u_scientist_id, u_country, u_city, " +
            "u_full_name, u_degree_id, u_graduation_year)" +
            "VALUES (?, ?, ?, ?, ?, ?)";

    //READ
    private static final String SQL_SELECT_ALL_UNIVERSITIES_BY_USER_ID = "SELECT " +
            "u_id, u_scientist_id, u_country, u_city, u_full_name, u_degree_id, u_graduation_year, d_id, d_name " +
            "FROM university u, degree d " +
            "WHERE u_degree_id = d_id AND u_scientist_id = (?)";

    //UPDATE
    @SuppressWarnings("SqlResolve")
    private static final String SQL_UPDATE_UNIVERSITY = "UPDATE " +
            "university SET u_country = (?), u_city = (?), " +
            "u_full_name = (?), u_degree_id = (?), u_graduation_year = (?)" +
            "WHERE u_id = (?)";

    //DELETE
    @SuppressWarnings("SqlResolve")
    private static final String SQL_REMOVE_UNIVERSITY = "DELETE FROM university WHERE u_id = (?)";

    private DataSource dataSource;

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
                            .setDegree(Degree.values()[resultSet.getInt("u_degree_id") - 1])
                            .setGraduationYear(resultSet.getInt("u_graduation_year"));
                    list.add(builder.build());
                }
                return list;
            }
        } catch (SQLException e) {
            //TODO add log
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
            //TODO add log
        }
    }

    @Override
    public boolean transactionCUD(List<University> listDeleted,
                                  List<University> listCreated,
                                  List<University> listUpdated) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            try {
                processDeleted(connection, listDeleted);
                processCreated(connection, listCreated);
                processUpdated(connection, listUpdated);
                connection.commit();
                connection.setAutoCommit(true);
                return true;
            } catch (SQLException e) {
                connection.rollback();
                //TODO add log
                return false;
            }
        } catch (SQLException e) {
            //TODO add log
            return false;
        }
    }

    private void processDeleted(Connection connection, List<University> listDeleted) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_UNIVERSITY)) {
            for (University university : listDeleted) {
                preparedStatement.setInt(1, university.getId());
                preparedStatement.executeUpdate();
            }
        }
    }

    private void processCreated(Connection connection, List<University> listCreated) throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(SQL_CREATE_UNIVERSITY, Statement.RETURN_GENERATED_KEYS)) {
            for (University university : listCreated) {
                preparedStatement.setInt(1, university.getScientistId());
                preparedStatement.setString(2, university.getCountry());
                preparedStatement.setString(3, university.getCity());
                preparedStatement.setString(4, university.getFullName());
                preparedStatement.setInt(5, university.getDegree().ordinal() + 1);
                preparedStatement.setInt(6, university.getGraduationYear());
                preparedStatement.executeUpdate();
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next())
                        university.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    private void processUpdated(Connection connection, List<University> listUpdated) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                SQL_UPDATE_UNIVERSITY)) {
            for (University university : listUpdated) {
                preparedStatement.setString(1, university.getCountry());
                preparedStatement.setString(2, university.getCity());
                preparedStatement.setString(3, university.getFullName());
                preparedStatement.setInt(4, university.getDegree().ordinal() + 1);
                preparedStatement.setInt(5, university.getGraduationYear());
                preparedStatement.setInt(6, university.getId());
                preparedStatement.executeUpdate();
            }
        }
    }
}
