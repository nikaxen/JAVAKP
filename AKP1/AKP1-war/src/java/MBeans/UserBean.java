
package MBeans;

import Entities.Studgroup;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

@Named(value = "userBean")
@RequestScoped
public class UserBean implements Serializable{

    
    private int id_user;
    private String fio;
    private String rolename;
    private String password;
    private String email;
    private int studgroupid;
    private int stid;
    
    @EJB
    private DAO.UserDAO uDAO;
    
    public String getUserFIOByID(int id){
        return uDAO.getUserByID(id).getFio();
    }
    
    public List<Entities.User> getAllPreps() {
        return uDAO.getAllUsersPrep();
    }

    public List<Entities.User> getAllStuds() {
        return uDAO.getAllUsersStud();
    }

    public List<Entities.User> getAllStudsInGroup(int grid) {
        return uDAO.getAllStudsInGroup(grid);
    }
    
    public List<Entities.User> getAllUsers() {
        return uDAO.getAllUsersList();
    }
    
    public Entities.User getUserByID() {
        return uDAO.getUserByID(stid);
    }
    
    public List<Entities.Studgroup> getAllStudGroups() {
        return uDAO.getAllStudGroups();
    }
    
    public List<Studgroup> GetStudGroup(int studid) {
        return uDAO.getStudGroup(studid);
    }
    
    public String RemoveStudGroup(int stud_id, int group_id){
        uDAO.RemoveStudGroup(stud_id,group_id);
        return "students-list";
    }
    
    String rn;

    public String ChangeUR(int id_user, int role) {

        switch (role) {
            case 1: {
                rn = "stud";
            }
            ;
            break;
            case 2: {
                rn = "dek";
            }
            ;
            break;
            case 3: {
                rn = "prep";
            }
            ;
            break;
        }
        //DAO.UserDAO uDAO = new DAO.UserDAO();
        uDAO.ChangeUserRole(id_user, rn);
        return "users";
    }

    public String RegisterNewUser() {

        if (uDAO.RegisterUser(fio, email, password)) {
            return "index";
        } else {
            return "register";
        }
    }

    public String SetStudGroup(int uid) {
        if (uDAO.SetStudGroup(uid, studgroupid)) {
            return "students-list";
        } else {
            return "";
        }
    }
    
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStudgroupid() {
        return studgroupid;
    }

    public void setStudgroupid(int studgroupid) {
        this.studgroupid = studgroupid;
    }

    public int getStid() {
        return stid;
    }

    public void setStid(int stid) {
        this.stid = stid;
    }

}
