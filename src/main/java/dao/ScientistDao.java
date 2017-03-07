package dao;

import model.Scientist;

import java.util.List;

/**
 * Created on 07.03.2017.
 */
public interface ScientistDao {

    int create(Scientist scientist);
    Scientist get(int id);
    //void update()
    void remove(Scientist scientist);
    List<Scientist> getAll();
}
