
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject")
    private Integer idSubject;

    @Column(name = "subject_title")
    private String subjectTitle;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
    private List<Statement> statementList;

    public Subject() {
    }

    public Subject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public Subject(Integer idSubject, String subjectTitle) {
        this.idSubject = idSubject;
        this.subjectTitle = subjectTitle;
    }

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    @XmlTransient
    public List<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
    }

    @Override
    public String toString() {
        return "Entities.Subject[ idSubject=" + idSubject + " ]";
    }
    
}
