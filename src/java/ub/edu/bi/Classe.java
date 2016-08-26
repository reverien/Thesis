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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "tbl_classes")
@NamedQueries({
    @NamedQuery(name = "Classe.findAll", query = "select c from Classe c ORDER BY C.id DESC"),
    @NamedQuery(name = "Classe.findByClasse", query = "select c from Classe c where c.nomClasse = :classe"),
    @NamedQuery(name = "Classe.findById", query = "select c from Classe c where c.id = :idClasse")
})
public class Classe implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomClasse;
    private int niveau;
    
    @ManyToOne
    @JoinColumn(name = "codeCycle")
    private Cycle codeCycle;
    
    @ManyToOne
    @JoinColumn(name = "idOption")
    private Option codeOption;
    
     @OneToMany(mappedBy = "cl",fetch = FetchType.EAGER)
    public List<Inscription> listeInscription = new ArrayList<>();
     
//     @OneToMany(mappedBy = "cl",fetch = FetchType.EAGER)
//    public List<Note> listeNote= new ArrayList<>();

//    @ManyToMany(mappedBy = "classes")
//    private List<Etudiant> etudiants;
    
//    @ManyToMany(mappedBy = "classes")
//    private Collection<Etudiant> etudiants;
    
    @OneToMany(mappedBy = "classeCode")
    private List<Unite> listeUnite = new ArrayList<>() ;

    public Classe() {
    }

    public Classe(String nomClasse, List<Etudiant> etudiants) {
        this.nomClasse = nomClasse;
//        this.etudiants = etudiants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public List<Unite> getListeUnite() {
        return listeUnite;
    }

    public void setListeUnite(List<Unite> listeUnite) {
        this.listeUnite = listeUnite;
    }

    public Cycle getCodeCycle() {
        return codeCycle;
    }

    public void setCodeCycle(Cycle codeCycle) {
        this.codeCycle = codeCycle;
    }

    public Option getCodeOption() {
        return codeOption;
    }

    public void setCodeOption(Option codeOption) {
        this.codeOption = codeOption;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

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
        return "Classe{" + "id=" + id + ", nomClasse=" + nomClasse + '}';
    }
}
