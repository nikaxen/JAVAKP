
package DAO;

import Entities.Studgroup;
import Entities.User;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UserDAO {
    public User getAccountByEmail(String email);
    public List<User> getAllUsersPrep(); 
    public List<User> getAllUsersStud();
    public List<User> getAllStudsInGroup(int grid);
    public List<User> getAllUsersList();
    public User getUserByID(int uid);
    public List<Studgroup> getAllStudGroups();
    public boolean SetStudGroup(int uid, int sgid);
    public boolean RegisterUser(String fio, String email, String password);
    public void ChangeUserRole(int id_user, String rolename);
    public List<Studgroup> getStudGroup(int studid);
    public List<User> getStudInGroup();
    public void RemoveStudGroup(int uid, int sgid);
    public Studgroup getStudgroupByID(int sgid);
}
