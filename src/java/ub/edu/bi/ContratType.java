package ub.edu.bi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "tbl_contrat")
@NamedQueries({
    @NamedQuery(name = "ContratType.findAll", query = "select c from ContratType c"),
    @NamedQuery(name = "ContratType.findById", query = "select c from ContratType c where C.id = :id")
})
public class ContratType implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contratType;
    private String descriptionContrat;
    
    @OneToMany(mappedBy = "contrat_id",cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Professeur> listeProf = new ArrayList<>();

    
    public ContratType() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContratType() {
        return contratType;
    }

    public void setContratType(String contratType) {
        this.contratType = contratType;
    }

    public List<Professeur> getListeProf() {
        return listeProf;
    }

    public void setListeProf(List<Professeur> listeProf) {
        this.listeProf = listeProf;
    }   

    public String getDescriptionContrat() {
        return descriptionContrat;
    }

    public void setDescriptionContrat(String descriptionContrat) {
        this.descriptionContrat = descriptionContrat;
    }

    @Override
    public String toString() {
        return "ContratType{" + "id=" + id + ", contratType=" + contratType + ", descriptionContrat=" + descriptionContrat + '}';
    }
    
    
}
