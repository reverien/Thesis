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
@Table(name = "tbl_cycles")
@NamedQueries({
    @NamedQuery(name = "Cycle.findAll", query = "select c from Cycle c "),
    @NamedQuery(name = "Cycle.findById", query = "select c from Cycle c where c.id = :idCycle "),
    @NamedQuery(name = "Cycle.findByNom", query = "select c from Cycle c where c.nomCycle = :nom "),
    @NamedQuery(name = "Cycle.findByCode", query = "select c from Cycle c where c.codeCycle = :code ")
})
public class Cycle implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomCycle = null;
    private String codeCycle = null;
    private String description;
    @OneToMany(mappedBy = "codeCycle")
    private List<Classe> listeClasse = new ArrayList<>();

    public Cycle() {
    }

    public Cycle(String nomCycle, String codeCycle, String description) {
        this.nomCycle = nomCycle;
        this.codeCycle = codeCycle;
        this.description = description;
    }

     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCycle() {
        return nomCycle;
    }

    public void setNomCycle(String nomCycle) {
        this.nomCycle = nomCycle;
    }

    public String getCodeCycle() {
        return codeCycle;
    }

    public void setCodeCycle(String codeCycle) {
        this.codeCycle = codeCycle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }  

    public List<Classe> getListeClasse() {
        return listeClasse;
    }

    public void setListeClasse(List<Classe> listeClasse) {
        this.listeClasse = listeClasse;
    }
}
