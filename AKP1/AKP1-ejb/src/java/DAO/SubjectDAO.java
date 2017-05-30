
package DAO;

import Entities.Subject;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SubjectDAO {
    public List<Subject> getAllSubjectList();
}
