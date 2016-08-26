package ub.edu.bi;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "tbl_role")
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "select r from Role r")
})
public class Role implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleMembre;
    private String description;

    public Role() {
    }

    public Role(String roleMembre, String description) {
        this.roleMembre = roleMembre;
        this.description = description;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleMembre() {
        return roleMembre;
    }

    public void setRoleMembre(String roleMembre) {
        this.roleMembre = roleMembre;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", roleMembre=" + roleMembre + ", description=" + description + '}';
    }

    
    
}
