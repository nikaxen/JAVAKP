
package Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.xml.bind.annotation.XmlTransient;


@Entity

public class Statement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statement")
    private Integer idStatement;

    @Column(name = "status")
    private String status;

    @Column(name = "title")
    private String title;

    @Column(name = "grid")
    private int grid;
  
    @Column(name = "semestr")
    private String semestr;
    
    @JoinColumn(name = "id_subject", referencedColumnName = "id_subject")
    @ManyToOne(optional = false)
    private Subject subject;
    
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User user;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statement")
    private List<Mark> markList;

    public Statement() {
    }

    public Statement(Integer idStatement) {
        this.idStatement = idStatement;
    }

    public Statement(Integer idStatement, String status, String title, int grid, String semestr) {
        this.idStatement = idStatement;
        this.status = status;
        this.title = title;
        this.grid = grid;
        this.semestr = semestr;
    }

    public Integer getIdStatement() {
        return idStatement;
    }

    public void setIdStatement(Integer idStatement) {
        this.idStatement = idStatement;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlTransient
    public List<Mark> getMarkList() {
        return markList;
    }

    public void setMarkList(List<Mark> markList) {
        this.markList = markList;
    }
    

    @Override
    public String toString() {
        return "Entities.Statement[ idStatement=" + idStatement + " ]";
    }
    
    @PrePersist
    public void PrePersisten(){
        System.out.println("PRE-PERSIST event " + idStatement);
    }
    
    @PostPersist
    public void PostPersisten(){
        System.out.println("POST-PERSIST event" + idStatement);
    }
            
    
}
