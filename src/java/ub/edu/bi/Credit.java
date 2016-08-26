package ub.edu.bi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "tbl_credit")
@NamedQueries({
    @NamedQuery(name = "Credit.findAll", query = "select c from Credit c"),
    @NamedQuery(name = "Credit.findBycredit", query = "select c from Credit c where c.credit = :c")
})
public class Credit implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer credit = 0;
    private String description;
    @OneToMany(mappedBy = "codeCredit")
    private List<Cours> listeCours = new ArrayList<>();

    public Credit() {
    }

    public Credit(Integer credit, String description) {
        this.credit = credit;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Cours> getListeCours() {
        return listeCours;
    }

    public void setListeCours(List<Cours> listeCours) {
        this.listeCours = listeCours;
    }

    @Override
    public String toString() {
        return "Credit{" + "id=" + id + ", credit=" + credit + ", description=" + description + '}';
    }
    
}
