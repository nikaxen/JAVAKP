
package DAO;

import Entities.Mark;
import Entities.Statement;
import Entities.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class MarkDAOImpl implements MarkDAO,Serializable {
    @PersistenceContext(unitName = "AKP1-ejbPU")
    private EntityManager em;
     
    @Override
    public List<Mark> getMarksInStatement(int sid) {
       //String sql = "SELECT * FROM Statement AS a INNER JOIN Mark AS c ON (a.id_statement = c.id_statement) INNER JOIN User AS b ON (c.id_user = b.id_user) INNER JOIN Subject AS d ON (a.id_subject = d.id_subject) WHERE a.id_statement=" + sid + "";
       
       Query query = em.createQuery("Select m FROM Mark m JOIN m.statement st WHERE st.idStatement=?1", Mark.class);
       query.setParameter(1, sid);
       return (List<Mark>) query.getResultList();
    }

    @Override
    public List<Mark> getMarksForUser(int uid, String marktype) {
        Query query;
        if (marktype == "bad") {
            query = em.createQuery("SELECT m FROM Mark m JOIN m.user u WHERE m.mark<3 AND u.idUser=?1", Mark.class);
        } else {
            query = em.createQuery("SELECT m FROM Mark m JOIN m.user u WHERE u.idUser=?1", Mark.class);
        }
       query.setParameter(1, uid);
       return (List<Mark>) query.getResultList();
    }

    @Override
    public Mark getMarkById(int mid) {
        //String sql = "SELECT * FROM Statement AS a INNER JOIN Mark AS d ON (a.id_statement = d.id_statement) INNER JOIN User AS b ON (d.id_user = b.id_user) INNER JOIN Subject AS c ON (a.id_subject = c.id_subject) WHERE id_mark=?";
       Query query = em.createQuery("SELECT m FROM Mark m WHERE m.idMark=?1", Mark.class);
       query.setParameter(1, mid);
       return (Mark) query.getResultList();
    }

    @Override
    public void AddNewMark(int id_statement, int id_user, int mark) {
        Mark m = new Mark();
        Statement statement = new Statement();
        User user = new User();
        statement.setIdStatement(id_statement);
        user.setIdUser(id_user);
        m.setStatement(statement);
        m.setUser(user);
        m.setMark(mark);
        em.persist(m);
    }

    @Override
    public void RemoveMark(int id_mark) {
      //String sql = "DELETE FROM mark WHERE id_mark=?";
       //Mark m = em.find(Mark.class, id_mark);
       //Query query = em.createQuery("DELETE FROM Mark m WHERE m.idMark=?1", Mark.class);
       //query.setParameter(1, id_mark);
       Mark m = em.find(Mark.class, id_mark);
       em.remove(m);
       System.out.println("id_mark="+id_mark);
    }
    
}
