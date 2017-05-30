
package DAO;

import Entities.Mark;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MarkDAO {
    public List<Mark> getMarksInStatement(int sid);
    public List<Mark> getMarksForUser(int uid, String marktype);
    public Mark getMarkById(int mid);
    public void AddNewMark(int id_statement, int id_user, int mark);
    public void RemoveMark(int id_mark);
}
