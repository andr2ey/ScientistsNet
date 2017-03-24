package service;

import dao.ScientistDao;
import model.Scientist;
import java.util.Set;

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

    public boolean confirmPassword(int id, String password) {
        return scientistDao.confirmPassword(id, password);
    }

    public boolean updateInfo(Scientist scientistNew) {
        return scientistDao.updateInfo(scientistNew);
    }

    public Set<Scientist> getAllByFullName(String firstName, String secondName) {
        return scientistDao.getAllByFullName(firstName, secondName);
    }

}
