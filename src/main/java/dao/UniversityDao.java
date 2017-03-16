package dao;

import model.University;

import java.util.List;

/**
 * Created on 07.03.2017.
 */
public interface UniversityDao {
    List<University> getAll(int scientistId);
    void deleteAll(List<University> listToDelete);
    boolean transactionCUD(List<University> listDeleted,
                   List<University> listCreated,
                   List<University> listUpdated);
}
