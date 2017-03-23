package service;

import dao.UniversityDao;
import model.University;
import java.util.List;

public class UniversityService {

    private final UniversityDao universityDao;

    public UniversityService(UniversityDao universityDao) {
        this.universityDao = universityDao;
    }

    public List<University> getAll(int scientistId) {
        return universityDao.getAll(scientistId);
    }

    public boolean transactionCUD(List<University> listDeleted,
                                  List<University> listCreated,
                                  List<University> listUpdated) {
        return universityDao.transactionCUD(listDeleted, listCreated, listUpdated);
    }


}
