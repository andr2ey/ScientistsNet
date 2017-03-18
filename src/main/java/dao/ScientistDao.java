package dao;

import model.Scientist;

import java.util.List;

/**
 * Created on 07.03.2017.
 */
public interface ScientistDao {

    int create(Scientist scientist);
    Scientist get(int id);
    Scientist get(String email);
    List<Scientist> getAll();
    boolean confirmPassword(int id, String password);
    boolean updateInfo(Scientist scientistNew, String email);
}
