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

    public List<University> getAll(int scientistId) {
        return universityDao.getAll(scientistId);
    }

    public void deleteUniversities(List<University> listToDelete) {
        universityDao.deleteAll(listToDelete);
    }

    public boolean transactionCUD(List<University> listDeleted,
                                  List<University> listCreated,
                                  List<University> listUpdated) {
        return universityDao.transactionCUD(listDeleted, listCreated, listUpdated);
    }
}
