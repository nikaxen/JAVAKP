
package DAO;

import Entities.Statement;
import java.util.List;
import javax.ejb.Local;

@Local
public interface StatementDAO {
    public Statement getStudGroup(int stid);
    public List<Statement> getStatementsForPrep(int prep_id);
    public Statement getStatementById(int sid);
    public void AddNewStatement(int id_subject, int id_user, String title, int grid,String semestr);
    public void SetStatementStatus(int id_statement, int codestatus);
    public List<Statement> getSearchResultsBy(String search_query);
    public List<Statement> getReadyStatementsList();
    public Statement getStatementByMarkId(int mid);
}
