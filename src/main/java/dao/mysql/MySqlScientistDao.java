package dao.mysql;

import dao.ScientistDao;
import model.Degree;
import model.Scientist;
import model.University;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created on 07.03.2017.
 */
public class MySqlScientistDao implements ScientistDao {

    private final static String SQL_CREATE_SCIENTIST = "INSERT INTO Scientist (first_name, second_name, middle_name, " +
            "email, password, birthday, university_id)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_SELECT_ALL_SCIENTISTS = "SELECT " +
            "s.id, s.first_name, s.second_name, s.middle_name, s.email, s.password, s.birthday, university_id, " +
            "u.country, u.city, u.full_name, u.degree_id, " +
            "d.name, d.description " +
            "FROM Scientist s, University u, Degree d " +
            "WHERE s.university_id = u.id AND u.degree_id = d.id";
    private final static String SQL_SELECT_SCIENTIST_BY_ID = "SELECT * FROM Scientist WHERE id = (?)";
    private final static String SQL_REMOVE_SCEINTIST = "DELETE FROM Scientist";

    //TODO how does it work?
    @Resource(name = "jdbc/TestDB")
    private DataSource dataSource;

    @Override
    public int create(Scientist scientist) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SQL_CREATE_SCIENTIST, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, scientist.getFirst_name());
            preparedStatement.setString(2, scientist.getSecond_name());
            preparedStatement.setString(3, scientist.getMiddle_name());
            preparedStatement.setString(4, scientist.getEmail());
            preparedStatement.setString(5, scientist.getPassword());
            preparedStatement.setDate(6, Date.valueOf(scientist.getBirthday()));
            preparedStatement.setInt(7, scientist.getUniversity().getId());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    scientist.setId(generatedKeys.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("operation with DB is invalid");
        }
        return scientist.getId();
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
                             )
                     );
                 }
             }
        } catch (SQLException e) {
            throw new RuntimeException("operation with DB is invalid");
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
                        )
                );
                list.add(scientist);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("operation with DB is invalid");
        }
    }
}
