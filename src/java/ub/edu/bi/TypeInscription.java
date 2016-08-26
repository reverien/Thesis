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
@Table(name = "tbl_typeInscription")
@NamedQueries({
    @NamedQuery(name = "TypeInscription.findAll", query = "select t from TypeInscription t"),
    @NamedQuery(name = "TypeInscription.findByCode", query = "select t from TypeInscription t WHERE t.codetype = :code"),
    @NamedQuery(name = "TypeInscription.findById", query = "select t from TypeInscription t where t.id = :idType")
})
public class TypeInscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codetype;
    private String type;
    
     @OneToMany(mappedBy = "typeInscr",fetch = FetchType.EAGER)
    public List<Inscription> listeInscription = new ArrayList<>();

     @OneToMany(mappedBy = "sess",fetch = FetchType.EAGER)
    public List<Note> listeNote = new ArrayList<>();

    public TypeInscription() {
    }

    public TypeInscription(String codetype, String type) {
        this.codetype = codetype;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodetype() {
        return codetype;
    }

    public void setCodetype(String codetype) {
        this.codetype = codetype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public List<Etudiant> getEtudiants() {
//        return etudiants;
//    }
//
//    public void setEtudiants(List<Etudiant> etudiants) {
//        this.etudiants = etudiants;
//    }

    public List<Inscription> getListeInscription() {
        return listeInscription;
    }

    public void setListeInscription(List<Inscription> listeInscription) {
        this.listeInscription = listeInscription;
    }

//    public List<Note> getListeNote() {
//        return listeNote;
//    }
//
//    public void setListeNote(List<Note> listeNote) {
//        this.listeNote = listeNote;
//    }
    
    
    
    @Override
    public String toString() {
        return "TypeInscription{" + "id=" + id + ", codetype=" + codetype + ", type=" + type + '}';
    }

}
