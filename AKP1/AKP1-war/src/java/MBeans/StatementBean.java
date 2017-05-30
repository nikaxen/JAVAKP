
package MBeans;

import Entities.Statement;
import Entities.Studgroup;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


@Named(value = "statementBean")
@SessionScoped
public class StatementBean implements Serializable{

    
    private int id_statement;
    private int id_subject;
    private int id_user;
    private String status;
    private String title;
    private String search_query;
    private int sid;
    private int grid;
    private String semestr;

    @EJB
    private DAO.StatementDAO sDAO;
    
    @EJB
    private DAO.UserDAO uDAO;
    public String getStudGroup() {
        Statement st = sDAO.getStatementById(sid);
        Studgroup sg = uDAO.getStudgroupByID(st.getGrid());
        return sg.getSgname();
            //return sDAO.getStudGroup(sid).getTitle(); 
    }
    
    public int getStudGroupID() {
            return sDAO.getStudGroup(sid).getGrid();
    }
   public List<Entities.Statement> getStatementForPrep() {
       HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
       Entities.User currentuser = uDAO.getAccountByEmail(request.getUserPrincipal().getName());
        return sDAO.getStatementsForPrep(currentuser.getIdUser());
    }
   
    public Entities.Statement getStatementById() {
        Entities.Statement fst = new Entities.Statement();
        fst = sDAO.getStatementById(sid);
        return fst;
    }
    public String AddStatement() {
        sDAO.AddNewStatement(id_subject, id_user, title, grid, semestr);
        return "statements";
    }

    public String SetStatementStatus(int codestatus) {
        sDAO.SetStatementStatus(sid,codestatus);
        return "";
    }
    
    public List<Entities.Statement> getSearchResults() {
        if (search_query == null) {
            setSearch_query("");
        }
        return sDAO.getSearchResultsBy(getSearch_query());
    }

    public List<Entities.Statement> getReadyStatements() {
        return sDAO.getReadyStatementsList();
    }

    public int getId_statement() {
        return id_statement;
    }

    public void setId_statement(int id_statement) {
        this.id_statement = id_statement;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSearch_query() {
        return search_query;
    }

    public void setSearch_query(String search_query) {
        this.search_query = search_query;
    }
     public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getGrid() {
        return grid;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    public String getSemestr() {
        return semestr;
    }

    public void setSemestr(String semestr) {
        this.semestr = semestr;
    }
    
}
