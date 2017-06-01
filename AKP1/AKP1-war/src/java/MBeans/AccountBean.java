
package MBeans;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Named(value = "accountBean")
@RequestScoped
public class AccountBean implements Serializable {

    @EJB
    private DAO.UserDAO uDAO;
    
    
        private Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        private String email;
        private String IndexPage;
        public String getIndexPage() throws SQLException, NamingException{
            switch(getRolename()){
            case"dek":{}
            case"prep":{
                IndexPage="./statements.xhtml";
            }
            break;
            case"stud":{
                IndexPage="./marks.xhtml";
            }
            break;
            default:IndexPage="./403.xhtml";
            }
            return IndexPage;
        }
    public String getRolename() throws SQLException, NamingException {
        if (principal != null) {
            email = principal.getName();
        } else {
            return "";
        }
        if (email != null) {
            return uDAO.getAccountByEmail(email).getRolename();
        } else {
            return "";
        }
    }
    public boolean getUserLoggedIn() {
    if (principal != null) {
            return true;
        } else {
            return false;
        }
    }
    public void logout()
    {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            request.logout();
            FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
        } catch (ServletException ex) {
            Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AccountBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
