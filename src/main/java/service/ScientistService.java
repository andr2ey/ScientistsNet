package service;

import dao.ScientistDao;
import model.Scientist;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
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
        Scientist scientist = scientistDao.get(id);
        scientist.setFormattedDob(formatDate(locale, scientist.getDob()));
        return scientist;
    }

    public Scientist get(String email, Locale locale) {
        Scientist scientist = scientistDao.get(email);
        scientist.setFormattedDob(formatDate(locale, scientist.getDob()));
        return scientist;
    }

    public boolean confirmPassword(int id, String password) {
        return scientistDao.confirmPassword(id, password);
    }

    public boolean updateInfo(Scientist scientistNew, Locale locale) {
        scientistNew.setFormattedDob(formatDate(locale, scientistNew.getDob()));
        return scientistDao.updateInfo(scientistNew);
    }

    public Set<Scientist> getAllByFullName(String firstName, String secondName) {
        return scientistDao.getAllByFullName(firstName, secondName);
    }

    private String formatDate(Locale locale, LocalDate localDate) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        return df.format(Date.valueOf(localDate));
    }
}
