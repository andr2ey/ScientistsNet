package dao.mysql;

import dao.ScientistDao;
import model.Degree;
import model.Gender;
import model.Scientist;
import model.University;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created on 07.03.2017.
 */
public class MySqlScientistDao implements ScientistDao {

    private final static String SQL_CREATE_SCIENTIST = "INSERT INTO Scientist (s_first_name, s_second_name, s_middle_name, " +
            "s_email, s_password, s_birthday, s_university_id)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_SELECT_ALL_SCIENTISTS = "SELECT " +
            "s_id, s_first_name, s_second_name, s_middle_name, s_email, s_password, s_birthday, s_gender_id, s_university_id," +
            "u_country, u_city, u_full_name, u_degree_id," +
            "d_name, d_description," +
            "g_name \n" +
            "FROM scientist s, university u, degree d, gender g\n" +
            "WHERE s_university_id = u_id AND u_degree_id = d_id AND s_gender_id = g_id";
    private final static String SQL_SELECT_SCIENTIST_BY_EMAIL = "SELECT \n" +
            "s_id, s_first_name, s_second_name, s_middle_name, s_email, s_password, s_birthday, s_gender_id, s_university_id,\n" +
            "u_country, u_city, u_full_name, u_degree_id,\n" +
            "d_name, d_description,\n" +
            "g_name \n" +
            "FROM scientist s, university u, degree d, gender g\n" +
            "WHERE s_university_id = u_id AND u_degree_id = d_id AND s_gender_id = g_id AND s_email = ";
    private final static String SQL_SELECT_SCIENTIST_BY_ID = "SELECT * FROM Scientist WHERE id = (?)";
    private final static String SQL_REMOVE_SCEINTIST = "DELETE FROM Scientist";

    //TODO how does it work?
    private DataSource dataSource;

    public MySqlScientistDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int create(Scientist scientist) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SQL_CREATE_SCIENTIST, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, scientist.getS_first_name());
            preparedStatement.setString(2, scientist.getS_second_name());
            preparedStatement.setString(3, scientist.getS_middle_name());
            preparedStatement.setString(4, scientist.getS_email());
            preparedStatement.setString(5, scientist.getS_password());
            preparedStatement.setDate(6, Date.valueOf(scientist.getS_birthday()));
            preparedStatement.setInt(7, scientist.getS_university().getU_id());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    scientist.setS_id(generatedKeys.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("operation with DB is invalid");
        }
        return scientist.getS_id();
    }

    @Override
    public Scientist get(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SQL_SELECT_SCIENTIST_BY_ID)) {
             preparedStatement.setInt(1, id);
             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                 if (resultSet.next()) {
                     return new Scientist(
                             resultSet.getInt("s_id"),
                             resultSet.getString("password"), //TODO think about safety
                             resultSet.getString("email"),
                             resultSet.getString("first_name"),
                             resultSet.getString("second_name"),
                             resultSet.getString("middle_name"),
                             resultSet.getDate("birthday").toLocalDate(),
                             new University(
                                     resultSet.getInt("university_id"),
                                     resultSet.getString("country"),
                                     resultSet.getString("city"),
                                     resultSet.getString("full_name"),
                                     Degree.valueOf(resultSet.getString("d_name"))
                             ),
                             Gender.valueOf(resultSet.getString("g_name"))
                     );
                 }
             }
        } catch (SQLException e) {
            throw new RuntimeException("operation with DB is invalid");
        }
        return null;
    }

    @Override
    public Scientist get(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SQL_SELECT_SCIENTIST_BY_EMAIL + "\'" +email + "\'")) {
//            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Scientist(
                            resultSet.getInt("s_id"),
                            resultSet.getString("s_password"), //TODO think about safety
                            resultSet.getString("s_email"),
                            resultSet.getString("s_first_name"),
                            resultSet.getString("s_second_name"),
                            resultSet.getString("s_middle_name"),
                            resultSet.getDate("s_birthday").toLocalDate(),
                            new University(
                                    resultSet.getInt("s_university_id"),
                                    resultSet.getString("u_country"),
                                    resultSet.getString("u_city"),
                                    resultSet.getString("u_full_name"),
                                    Degree.valueOf(resultSet.getString("d_name"))
                            ),
                            Gender.valueOf(resultSet.getString("g_name"))
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    public void remove(Scientist scientist) {

    }

    @Override
    public List<Scientist> getAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SQL_SELECT_ALL_SCIENTISTS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            ArrayList<Scientist> list = new ArrayList<>(resultSet.getRow());
            while (resultSet.next()) {
                Scientist scientist = new Scientist(
                        resultSet.getInt("id"),
                        resultSet.getString("password"), //TODO think about safety
                        resultSet.getString("email"),
                        resultSet.getString("first_name"),
                        resultSet.getString("second_name"),
                        resultSet.getString("middle_name"),
                        resultSet.getDate("birthday").toLocalDate(),
                        new University(
                                resultSet.getInt("university_id"),
                                resultSet.getString("country"),
                                resultSet.getString("city"),
                                resultSet.getString("full_name"),
                                Degree.valueOf(resultSet.getString("name"))
                        ),
                        Gender.valueOf(resultSet.getString("g_name"))
                );
                list.add(scientist);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("operation with DB is invalid");
        }
    }
}
