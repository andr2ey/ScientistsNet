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

    public Scientist get(String email) {
        return scientistDao.get(email);
    }

    public List<Scientist> getAll() {
        return scientistDao.getAll();
    }

    public boolean confirmPassword(int id, String password) {
        return scientistDao.confirmPassword(id, password);
    }

    public boolean updateInfo(Scientist scientistNew, String email) {
        return scientistDao.updateInfo(scientistNew, email);
    }
}
