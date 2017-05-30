
package Ent;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "grant", catalog = "uchet1", schema = "")

public class Grant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grant")
    private Integer idGrant;
    
    @Column(name = "grant_semestr")
    private String grantSemestr;
    
    @Column(name = "grant_title")
    private String grantTitle;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grant")
    private List<GrantUser> grantUserList;

    public Grant() {
    }

    public Grant(Integer idGrant) {
        this.idGrant = idGrant;
    }

    public Grant(Integer idGrant, String grantSemestr, String grantTitle) {
        this.idGrant = idGrant;
        this.grantSemestr = grantSemestr;
        this.grantTitle = grantTitle;
    }

    public Integer getIdGrant() {
        return idGrant;
    }

    public void setIdGrant(Integer idGrant) {
        this.idGrant = idGrant;
    }

    public String getGrantSemestr() {
        return grantSemestr;
    }

    public void setGrantSemestr(String grantSemestr) {
        this.grantSemestr = grantSemestr;
    }

    public String getGrantTitle() {
        return grantTitle;
    }

    public void setGrantTitle(String grantTitle) {
        this.grantTitle = grantTitle;
    }

    @XmlTransient
    public List<GrantUser> getGrantUserList() {
        return grantUserList;
    }

    public void setGrantUserList(List<GrantUser> grantUserList) {
        this.grantUserList = grantUserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrant != null ? idGrant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grant)) {
            return false;
        }
        Grant other = (Grant) object;
        if ((this.idGrant == null && other.idGrant != null) || (this.idGrant != null && !this.idGrant.equals(other.idGrant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ent.Grant[ idGrant=" + idGrant + " ]";
    }
    
}
