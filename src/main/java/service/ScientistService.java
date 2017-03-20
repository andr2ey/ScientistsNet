package service;

import dao.ScientistDao;
import model.Scientist;

import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ScientistService {

    private final ScientistDao scientistDao;

    public ScientistService(ScientistDao scientistDao) {
        this.scientistDao = scientistDao;
    }

    public int create(Scientist scientist) {
        return scientistDao.create(scientist);
    }

    public Scientist get(int id, Locale locale) {
        return scientistDao.get(id, locale);
    }

    public Scientist get(String email, Locale locale) {
        return scientistDao.get(email, locale);
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

    public Set<Scientist> getAllByFullName(String firstName, String secondName) {
        return scientistDao.getAllByFullName(firstName, secondName);
    }
}
