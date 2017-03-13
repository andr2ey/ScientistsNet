package dao.mysql;

import dao.UniversityDao;
import model.Gender;
import model.Scientist;
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
    private final String SQL_CREATE_SCIENTIST = "INSERT INTO scientist (s_first_name, s_second_name, s_middle_name, " +
            "s_email, s_password, s_dob, s_gender_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_ADD_TO_ROLE = "INSERT INTO roles (s_email, r_name) VALUES (?, ?)";

    //READ
    private final String SQL_SELECT_ALL_UNIVERSITIES_BY_USER_ID = "SELECT " +
            "u_id, u_scientist_id, u_country, u_city, u_full_name, u_degree_id " +
            "d_name " +
            "FROM university u, degree d " +
            "WHERE u_degree_id = d_id AND u_scientist_id = (?)";

    //UPDATE

    //DELETE
    private final static String SQL_REMOVE_SCEINTIST = "DELETE FROM Scientist WHERE";

    private DataSource dataSource;

    public MySqlUniversityDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<University> getAll(int scientistId) {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_UNIVERSITIES_BY_USER_ID)) {
//            preparedStatement.setInt(1, scientistId);
//            try(ResultSet resultSet = preparedStatement.executeQuery()) {
//                ArrayList<Scientist> list = new ArrayList<>(resultSet.getRow());
//                while (resultSet.next()) {
//                    University.Builder builder = new University().builder()
//                            .setCity(resultSet.get)
//
//                }
//            }
//            return list;
//        } catch (SQLException e) {
//            logger.error("Getting all users has been unsuccessful", e);
//        }
        return Collections.emptyList();
    }
}
