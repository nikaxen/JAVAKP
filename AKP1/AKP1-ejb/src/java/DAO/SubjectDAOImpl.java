
package DAO;

import Entities.Subject;
import Entities.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class SubjectDAOImpl implements SubjectDAO,Serializable {
    @PersistenceContext(unitName = "AKP1-ejbPU")
    private EntityManager em;
    
    @Override
    public List<Subject> getAllSubjectList() {
        Query query = em.createQuery("SELECT s FROM Subject s", Subject.class);
        return (List<Subject>) query.getResultList();
    }
    
}
