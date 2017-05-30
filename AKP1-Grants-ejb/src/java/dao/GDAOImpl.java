
package dao;

import Ent.Grant;
import Ent.GrantUser;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class GDAOImpl implements GDAO, Serializable{
    @PersistenceContext(unitName = "AKP1-Grants-ejbPU")
    private EntityManager em2;
    
    @Override
    public List<GrantUser> getGrantDetails(String semcode) {
        Query query = em2.createQuery("SELECT gu FROM GrantUser gu JOIN gu.grant gr WHERE gr.grantSemestr=?1", GrantUser.class);
        query.setParameter(1, semcode);
        return (List<GrantUser>) query.getResultList();
    }
    
    @Override
    public Grant getGrantBySemestr(){
        Query query_grant = em2.createQuery("SELECT g FROM Grant g WHERE g.grantSemestr=?1", Grant.class);
        query_grant.setParameter(1, "SPRING2017");
        return (Grant) query_grant.getSingleResult();
    }
    
    @Override
    public List<Grant> getAllGrants() {
        Query query = em2.createQuery("SELECT g FROM Grant g", Grant.class);
        return (List<Grant>) query.getResultList();
    }
    
    @Override
    public void SetGrant(int id_user, int id_grant, boolean grant_status){
        Grant g = em2.find(Grant.class, id_grant);
        GrantUser gu = new GrantUser();
        
        gu.setIdUser(id_user); // REALLY STRANGE
        gu.setGrant(g);
        if (grant_status) {
        System.out.println("ПОЛУЧАЕТ - " + id_user);
        gu.setGrantStatus("ok");
        } else {
        System.out.println("НЕ ПОЛУЧАЕТ - " + id_user);
        gu.setGrantStatus("no");
        }
        em2.persist(gu);
    }
}
