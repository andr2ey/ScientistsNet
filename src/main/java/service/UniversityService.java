package service;

import dao.UniversityDao;
import model.University;
import java.util.List;

/**
 * Created on 13.03.2017.
 */
public class UniversityService {

    private final UniversityDao universityDao;

    public UniversityService(UniversityDao universityDao) {
        this.universityDao = universityDao;
    }

    public List<University> allUniversities(int scientistId) {
        return universityDao.getAll(scientistId);
    }

}
