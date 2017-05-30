
package Entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Mark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mark")
    private Integer idMark;

    @Column(name = "mark")
    private int mark;
    
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User user;
    
    @JoinColumn(name = "id_statement", referencedColumnName = "id_statement")
    @ManyToOne(optional = false)
    private Statement statement;

    public Mark() {
    }

    public Mark(Integer idMark) {
        this.idMark = idMark;
    }

    public Mark(Integer idMark, int mark) {
        this.idMark = idMark;
        this.mark = mark;
    }

    public Integer getIdMark() {
        return idMark;
    }

    public void setIdMark(Integer idMark) {
        this.idMark = idMark;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }


    @Override
    public String toString() {
        return "Entities.Mark[ idMark=" + idMark + " ]";
    }
    
}
