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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class GrantDAOImpl implements GrantDAO, Serializable {

    @PersistenceContext(unitName = "AKP1-ejbPU")
    private EntityManager em;
    
    @EJB
    private dao.GDAO gDAO2;
    @Resource
    SessionContext sc;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void GrantCalculate() { // GRANT CALCULATE method

        Grant grant = gDAO2.getGrantBySemestr();
        
        Query query = em.createQuery("SELECT u FROM User u JOIN u.markList ml JOIN ml.statement st WHERE st.semestr='SPRING2017' GROUP BY u.idUser", User.class);
        List<User> uList = query.getResultList();
        boolean grant_status = true;
        for (User u : uList) {
            List<Mark> markList = u.getMarkList();
            for (Mark m : markList) {
                if (u.getIdUser() == m.getUser().getIdUser()) {
                    System.out.println(u.getFio() + " - " + m.getMark());
                    if (m.getMark() < 3) {
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
