
package DAO;

import Entities.Studgroup;
import Entities.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserDAOImpl implements UserDAO,Serializable {
    @PersistenceContext(unitName = "AKP1-ejbPU")
    private EntityManager em;
    
    @Override
    public User getAccountByEmail(String email) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.email=?1", User.class);
        query.setParameter(1, email);
        
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> getAllUsersPrep() {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.rolename=?1", User.class);
        query.setParameter(1, "prep");
        return (List<User>) query.getResultList();
    }

    @Override
    public List<User> getAllUsersStud() {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.rolename=?1", User.class);
        query.setParameter(1, "stud");
        return (List<User>) query.getResultList();
    }

    @Override
    public List<User> getAllStudsInGroup(int grid) { 
        Query query = em.createQuery("SELECT u FROM User u JOIN u.studgroup sig WHERE sig.sgid=?1", User.class);
        query.setParameter(1, grid);
        return (List<User>) query.getResultList();
    }

    @Override
    public List<User> getAllUsersList() {
        Query query = em.createQuery("SELECT u FROM User u", User.class);
        return (List<User>) query.getResultList();
    }

    @Override
    public User getUserByID(int uid) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.idUser=?1", User.class);
        query.setParameter(1, uid);
        return (User) query.getSingleResult();
    }

    @Override
    public List<Studgroup> getAllStudGroups() {
        Query query = em.createQuery("SELECT s FROM Studgroup s", Studgroup.class);
        return (List<Studgroup>) query.getResultList();
    }

    @Override
    public boolean SetStudGroup(int uid, int sgid) { 
        User u = em.find(User.class, uid);
        Studgroup sg = em.find(Studgroup.class, sgid);
        
        sg.addUser(u);
        return true;
    }

    @Override
    public boolean RegisterUser(String fio, String email, String password) {
        User user = new User();
        user.setFio(fio);
        user.setEmail(email);
        user.setPassword(password);
        user.setRolename("neizv");
        em.persist(user);
        return true;
    }

    @Override
    public void ChangeUserRole(int id_user, String rolename) { 
        User user = em.find(User.class, id_user);
        user.setRolename(rolename);
        em.persist(user);
    }

    @Override
    public List<Studgroup> getStudGroup(int studid) { 
        Query query = em.createQuery("SELECT s FROM Studgroup s JOIN s.studingroup gr WHERE gr.idUser=?1",Studgroup.class);
        query.setParameter(1, studid);
        return (List<Studgroup>) query.getResultList();
    }

    @Override
    public List<User> getStudInGroup() {
        Query query = em.createQuery("SELECT s FROM Studgroup s", User.class);
        return (List<User>) query.getResultList();
    }

    @Override
    public void RemoveStudGroup(int uid, int sgid) {
        User u = em.find(User.class, uid);
        Studgroup sg = em.find(Studgroup.class, sgid);
        sg.removeUser(u);
    }

    @Override
    public Studgroup getStudgroupByID(int sgid) {
        Query query = em.createQuery("SELECT s FROM Studgroup s WHERE s.sgid=?1", Studgroup.class);
        query.setParameter(1, sgid);
        return (Studgroup) query.getSingleResult();
    }
    
    
    
}
