package dao;

import model.Scientist;

import java.util.Set;

public interface ScientistDao {
    int create(Scientist scientist);
    Scientist get(int id);
    Scientist get(String email);
    boolean confirmPassword(int id, String password);
    boolean updateInfo(Scientist scientistNew);
    Set<Scientist> getAllByFullName(String firstName, String secondName);
}

