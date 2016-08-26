
package ub.edu.bi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "tbl_salle")
@NamedQueries({
    @NamedQuery (name = "Salle.findAll", query = "select s from Salle s"),
    @NamedQuery (name = "Salle.findById", query = "select s from Salle s where s.id = :idSalle"),
    @NamedQuery (name = "Salle.findByCode", query = "select s from Salle s where s.codeSalle = :code")
})
public class Salle implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String salle;
    private String codeSalle;
    
    @OneToMany(mappedBy = "salleEtude",fetch = FetchType.EAGER)
    public List<ProgrammationHoraire> listeHoraire = new ArrayList<>();

    public Salle() {
    }
    
    public Salle(String salle, String codeSalle) {
        this.salle = salle;
        this.codeSalle = codeSalle;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getCodeSalle() {
        return codeSalle;
    }

    public void setCodeSalle(String codeSalle) {
        this.codeSalle = codeSalle;
    }

    public List<ProgrammationHoraire> getListeHoraire() {
        return listeHoraire;
    }

    public void setListeHoraire(List<ProgrammationHoraire> listeHoraire) {
        this.listeHoraire = listeHoraire;
    }

    @Override
    public String toString() {
        return "Salle{" + "id=" + id + ", salle=" + salle + ", codeSalle=" + codeSalle + '}';
    }
    

   
}
