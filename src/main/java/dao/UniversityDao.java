package dao;

import model.University;
import java.util.List;

public interface UniversityDao {
    List<University> getAll(int scientistId);
    boolean transactionCUD(List<University> listDeleted,
                           List<University> listCreated,
                           List<University> listUpdated);
}
