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
@Table(name = "tbl_departement")
@NamedQueries({
    @NamedQuery(name = "Departement.findAll", query = "select d from Departement d"),
    @NamedQuery(name = "Departement.findByCode", query = "select d from Departement d where d.codeDepart = :code"),
    @NamedQuery(name = "Departement.findByDepartement", query = "select d from Departement d where d.departement = :depart"),
    @NamedQuery(name = "Departement.findById", query = "select d from Departement d where d.id = :identifiant")
})
public class Departement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departement;
    private String codeDepart;
    private String description;
    @OneToMany(mappedBy = "codeDepartement")
    public List<Option> listeOption = new ArrayList<>();

    public Departement() {
    }

    public Departement(String departement, String codeDepart, String description) {
        this.departement = departement;
        this.codeDepart = codeDepart;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getCodeDepart() {
        return codeDepart;
    }

    public void setCodeDepart(String codeDepart) {
        this.codeDepart = codeDepart;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Option> getListeOption() {
        return listeOption;
    }

    public void setListeOption(List<Option> listeOption) {
        this.listeOption = listeOption;
    }

}
