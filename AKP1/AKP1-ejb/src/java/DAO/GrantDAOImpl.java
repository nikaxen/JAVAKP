package DAO;

import Ent.Grant;
import Entities.Mark;
import Entities.User;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GrantDAOImpl implements GrantDAO, Serializable {

    @PersistenceContext(unitName = "AKP1-ejbPU")
    private EntityManager em;
    
    @EJB
    private dao.GDAO gDAO2;
    @Resource
    SessionContext sc;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @Override
    public void GrantCalculate(String semcode) { // GRANT CALCULATE method
        
        gDAO2.CleanGrants(semcode);
        
        Grant grant = gDAO2.getGrantBySemestr(semcode);
        
        Query query = em.createQuery("SELECT u FROM User u JOIN u.markList ml JOIN ml.statement st WHERE st.semestr=?1 GROUP BY u.idUser", User.class);
        query.setParameter(1, semcode);
        List<User> uList = query.getResultList();
        boolean grant_status = true;
        for (User u : uList) {
            List<Mark> markList = u.getMarkList();
            for (Mark m : markList) {
                if (u.getIdUser() == m.getUser().getIdUser()) {
                    System.out.println(u.getFio() + " - " + m.getMark());
                    if (m.getMark() < 4) {
                        grant_status = false;
                    }
                }
            }
            
            gDAO2.SetGrant(u.getIdUser(), grant.getIdGrant(), grant_status);
            grant_status = true;
        }
    }

    @Override
    public List<Grant> getAllGrants() {
        return gDAO2.getAllGrants();
    }

}
