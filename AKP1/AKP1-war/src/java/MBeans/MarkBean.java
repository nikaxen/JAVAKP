
package MBeans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;


@Named(value = "markBean")
@RequestScoped
public class MarkBean implements Serializable{
private int id_mark;
    private int id_statement;
    private int id_user;
    private int mark;
    private String stud_fio;
    private String subj_title;
    private String statement_title;
    private String param_view;
    private int param_mid;
    
    @EJB
    private DAO.UserDAO uDAO;
    
    @EJB
    private DAO.MarkDAO mDAO;
    
    @EJB
    private DAO.StatementDAO sDAO;
    
    public List<Entities.Mark> getMarksForUser() {
       HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
       Entities.User currentuser = uDAO.getAccountByEmail(request.getUserPrincipal().getName());
       return mDAO.getMarksForUser(currentuser.getIdUser(), "");
    }
    public List<Entities.Mark> getBadMarksForUser() {
       HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
       Entities.User currentuser = uDAO.getAccountByEmail(request.getUserPrincipal().getName());
       return mDAO.getMarksForUser(currentuser.getIdUser(), "bad");
    }
    public String AddMark(int sid) {
        mDAO.AddNewMark(sid, id_user, mark);
        return "";
    }
    public String DeleteMark(int id_mark) {
        mDAO.RemoveMark(id_mark);
        return "";
    }
    public List<Entities.Mark> getMarksInStatement(int sid)  {
        return mDAO.getMarksInStatement(sid);
    }
    public Entities.Mark getMarkById() {
        Entities.Mark mark = new Entities.Mark();
        mark = mDAO.getMarkById(param_mid);
        return mark;
    }
    public Entities.Statement getStatementByMarkId() {
        return sDAO.getStatementByMarkId(param_mid);
    }
    public int getId_mark() {
        return id_mark;
    }

    public void setId_mark(int id_mark) {
        this.id_mark = id_mark;
    }

    public int getId_statement() {
        return id_statement;
    }

    public void setId_statement(int id_statement) {
        this.id_statement = id_statement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getStud_fio() {
        return stud_fio;
    }

    public void setStud_fio(String stud_fio) {
        this.stud_fio = stud_fio;
    }

    public String getSubj_title() {
        return subj_title;
    }

    public void setSubj_title(String subj_title) {
        this.subj_title = subj_title;
    }

    public String getStatement_title() {
        return statement_title;
    }

    public void setStatement_title(String statement_title) {
        this.statement_title = statement_title;
    }

    public String getParam_view() {
        return param_view;
    }

    public void setParam_view(String param_view) {
        this.param_view = param_view;
    }

    public int getParam_mid() {
        return param_mid;
    }

    public void setParam_mid(int param_mid) {
        this.param_mid = param_mid;
    }
}
