
package MBeans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;


@Named(value = "subjectBean")
@SessionScoped
public class SubjectBean implements Serializable{
    @EJB
    private DAO.SubjectDAO subDAO;
    public List<Entities.Subject> getAllSubjects(){
       return subDAO.getAllSubjectList();
    }
}
