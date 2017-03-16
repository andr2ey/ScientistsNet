package service;

import dao.ScientistDao;
import model.Scientist;

import java.util.List;

public class ScientistService {

    private final ScientistDao scientistDao;

    public ScientistService(ScientistDao scientistDao) {
        this.scientistDao = scientistDao;
    }

    public int create(Scientist scientist) {
        return scientistDao.create(scientist);
    }

    public Scientist get(int id) {
        return scientistDao.get(id);
    }

    public boolean exist(String email) {
        return scientistDao.exist(email);
    }

    public Scientist get(String email) {
        return scientistDao.get(email);
    }

    public void remove(Scientist scientist) {
        scientistDao.remove(scientist);
    }

    public List<Scientist> getAll() {
        return scientistDao.getAll();
    }
}
