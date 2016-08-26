package ub.edu.bi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
@Table(name = "tbl_cour")
@NamedQueries({
    @NamedQuery(name = "Cours.findAll", query = "select c from Cours c"),
    @NamedQuery(name = "Cours.findById", query = "select c from Cours c where C.id = :idCours"),
    @NamedQuery(name = "Cours.findByCodeCours", query = "select c from Cours c where c.codeCours = :codeCours"),
    @NamedQuery(name = "Cours.findByClasseSemestre", query = "select c from Cours c where c.codeUnite.classeCode.id = :idClasse AND c.codeUnite.semestreCode.id = :idSemestre"),
    @NamedQuery(name = "Cours.findCoursByUnite", query = "select c from Cours c where c.codeUnite.id = :idUnite")
})
public class Cours implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomCours;
    private int numero;
    private String codeCours;
    @ManyToOne
    @JoinColumn(name = "creditCode")
    private Credit codeCredit;
    @ManyToOne
    @JoinColumn(name = "codeUnite")
    private Unite codeUnite;
    
    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    public List<Attribution> listeAttribution = new ArrayList<>();
    
    @OneToMany(mappedBy = "prorammerCoursAttribuer",fetch = FetchType.EAGER)
    public List<ProgrammationHoraire> listeHoraire = new ArrayList<>();
    
//    @ManyToMany
//    @JoinTable(name = "tbl_attribution",joinColumns = @JoinColumn(name = "id_cours"), inverseJoinColumns = @JoinColumn(name = "id_promotion"))
//    private  List<Promotion> listePromotions;
//    
//    @ManyToMany
//    @JoinTable(name = "tbl_attribution",joinColumns = @JoinColumn(name = "id_cours"), inverseJoinColumns = @JoinColumn(name = "id_professeur"))
//    private List<Professeur> listOfProfesor;
    

    public Cours() {
    }

    public Cours(String nomCours, int numero, String codeCours) {
        this.nomCours = nomCours;
        this.numero = numero;
        this.codeCours = codeCours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodeCours() {
        return codeCours;
    }

    public void setCodeCours(String codeCours) {
        this.codeCours = codeCours;
    }

    public Credit getCodeCredit() {
        return codeCredit;
    }

    public void setCodeCredit(Credit codeCredit) {
        this.codeCredit = codeCredit;
    }

    public Unite getCodeUnite() {
        return codeUnite;
    }

    public void setCodeUnite(Unite codeUnite) {
        this.codeUnite = codeUnite;
    }

//    public List<Professeur> getListOfProfesor() {
//        return listOfProfesor;
//    }
//
//    public void setListOfProfesor(List<Professeur> listOfProfesor) {
//        this.listOfProfesor = listOfProfesor;
//    }
//
//    public List<Promotion> getListePromotions() {
//        return listePromotions;
//    }
//
//    public void setListePromotions(List<Promotion> listePromotions) {
//        this.listePromotions = listePromotions;
//    }

    public List<Attribution> getListeAttribution() {
        return listeAttribution;
    }

    public void setListeAttribution(List<Attribution> listeAttribution) {
        this.listeAttribution = listeAttribution;
    }
    
    public List<ProgrammationHoraire> getListeHoraire() {
        return listeHoraire;
    }

    public void setListeHoraire(List<ProgrammationHoraire> listeHoraire) {
        this.listeHoraire = listeHoraire;
    }
    

    @Override
    public String toString() {
        return "Cours{" + "id=" + id + ", nomCours=" + nomCours + ", numero=" + numero + ", codeCours=" + codeCours + ", codeCredit=" + codeCredit + ", codeUnite=" + codeUnite + '}';
    }

    

}
