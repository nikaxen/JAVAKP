
package DAO;

import Entities.Statement;
import Entities.Subject;
import Entities.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class StatementDAOImpl implements StatementDAO,Serializable {
    @PersistenceContext(unitName = "AKP1-ejbPU")
    private EntityManager em;
    
    @Override
    public Statement getStudGroup(int stid) { 
        Query query = em.createQuery("SELECT s FROM Statement s WHERE s.idStatement=?1", Statement.class);
        query.setParameter(1, stid);
        return (Statement) query.getSingleResult();
    }

    @Override
    public List<Statement> getStatementsForPrep(int prep_id) {
        Query query = em.createQuery("SELECT s FROM Statement s JOIN s.user u WHERE u.idUser=?1 AND s.status<>?2 AND s.status<>?3", Statement.class);
        query.setParameter(1, prep_id);
        query.setParameter(2, "ready");
        query.setParameter(3, "close");
        return (List<Statement>) query.getResultList();
    }

    @Override
    public Statement getStatementById(int sid) {
        Query query = em.createQuery("SELECT s FROM Statement s WHERE s.idStatement=?1", Statement.class);
        query.setParameter(1, sid);
        return (Statement) query.getSingleResult();
    }

    @Override
    public void AddNewStatement(int id_subject, int id_user, String title, int grid, String semestr) { 
        Statement statement = new Statement();
        Subject subject = em.find(Subject.class, id_subject);
        User user = em.find(User.class, id_user);
        statement.setSubject(subject);
        statement.setUser(user);
        statement.setTitle(title);
        statement.setGrid(grid);
        statement.setSemestr(semestr);
        statement.setStatus("open");
        System.out.println("id_subject "+id_subject);
        System.out.println("id_user "+id_user);
        System.out.println("title "+title);
        System.out.println("grid "+grid);
        System.out.println("semestr "+semestr);
        System.out.println("status "+statement.getStatus());
        em.persist(statement);
    }

    @Override
    public void SetStatementStatus(int id_statement, int codestatus) {
        Statement statement = em.find(Statement.class, id_statement);
        String status;
        switch(codestatus){
            case 1:{
                status="open";
            }
            break;
            case 2:{
                status="ready";
            }
            break;
            case 3:{
                status="close";
            }
            break;
            default:status="open";
        }
        statement.setStatus(status);
        em.persist(statement);
    }

    @Override
    public List<Statement> getSearchResultsBy(String search_query) {
         Query query = em.createQuery("SELECT s FROM Statement s WHERE s.title LIKE ?1", Statement.class);
         query.setParameter(1, "%"+search_query+"%");
        return (List<Statement>) query.getResultList();
    }

    @Override
    public List<Statement> getReadyStatementsList() {
        Query query = em.createQuery("SELECT s FROM Statement s WHERE s.status=?1", Statement.class);
        query.setParameter(1, "ready");
        return (List<Statement>) query.getResultList();
    }

    @Override
    public Statement getStatementByMarkId(int mid) {
        Query query = em.createQuery("SELECT s FROM Statement s JOIN s.markList m WHERE m.idMark=?1", Statement.class);
        query.setParameter(1, mid);
        return (Statement) query.getResultList();
    }
    
}
