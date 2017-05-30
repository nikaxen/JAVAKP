
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Studgroup implements Serializable {

    public List<User> getStudingroup() {
        return studingroup;
    }

    public void setStudingroup(List<User> studingroup) {
        this.studingroup = studingroup;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sgid")
    private Integer sgid;

    @Column(name = "sgname")
    private String sgname;

        
    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name="studingroup", 
            joinColumns = @JoinColumn(name="groupid"), 
            inverseJoinColumns = @JoinColumn(name="studid"))
    private List<User> studingroup;
    
    public void removeUser(User user){
        this.studingroup.remove(user);
    }
    
    public void addUser(User user){ // for MANYtoMANY
        this.studingroup.add(user);
    }
    
    
    public Studgroup() {
    }

    public Studgroup(Integer sgid) {
        this.sgid = sgid;
    }

    public Studgroup(Integer sgid, String sgname) {
        this.sgid = sgid;
        this.sgname = sgname;
    }

    public Integer getSgid() {
        return sgid;
    }

    public void setSgid(Integer sgid) {
        this.sgid = sgid;
    }

    public String getSgname() {
        return sgname;
    }

    public void setSgname(String sgname) {
        this.sgname = sgname;
    }

    @Override
    public String toString() {
        return "Entities.Studgroup[ sgid=" + sgid + " ]";
    }
    
}
