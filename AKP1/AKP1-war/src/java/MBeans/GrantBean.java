
package MBeans;

import Ent.Grant;
import Ent.GrantUser;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;


@Named(value = "grantBean")
@SessionScoped
public class GrantBean implements Serializable{

    private int id_grant;
    private String grant_semestr;
    private String grant_title;
    private int id_user;
    private String status;
    private String sem_code;

    @EJB
    private DAO.GrantDAO gDAO;
    
    @EJB 
    private dao.GDAO gDAO2;
    
    public String AddGrant() {
        gDAO2.AddNewGrant(grant_semestr,grant_title);
        return "grants.xhtml";
    }
    
    public List<Grant> getAllGrants(){
        return gDAO2.getAllGrants();
    }
   public List<GrantUser> getGrantDetails(){
        return gDAO2.getGrantDetails(sem_code);
    }
    public String GrantCalculate(){
        gDAO.GrantCalculate(sem_code);
        return "grants.xhtml";
    }
    
    public int getId_grant() {
        return id_grant;
    }

    public void setId_grant(int id_grant) {
        this.id_grant = id_grant;
    }

    public String getGrant_semestr() {
        return grant_semestr;
    }

    public void setGrant_semestr(String grant_semestr) {
        this.grant_semestr = grant_semestr;
    }

    public String getGrant_title() {
        return grant_title;
    }

    public void setGrant_title(String grant_title) {
        this.grant_title = grant_title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public GrantBean() {
    }

    public int getId_user() {
        return id_user;
    }
    
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getSem_code() {
        return sem_code;
    }

    public void setSem_code(String sem_code) {
        this.sem_code = sem_code;
    }
    
}
