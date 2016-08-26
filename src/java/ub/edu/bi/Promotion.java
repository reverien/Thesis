package ub.edu.bi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name = "tbl_promotion")
@NamedQueries({
    @NamedQuery(name = "Promotion.currentYear", query = "select p from Promotion p"),
    @NamedQuery(name = "Promotion.findById", query = "select p from Promotion p where P.id = :idpromotion")
})
public class Promotion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promotion")
    private Long id;
    private String promotion;
    
    @OneToMany(mappedBy = "annee")
    public List<Attribution> listeAttribution = new ArrayList<>();
    
    @OneToMany(mappedBy = "prom")
    public List<Inscription> listeInscription = new ArrayList<>();
    
//    @OneToMany(mappedBy = "promo",fetch = FetchType.EAGER)
//    public List<Note> listeNote = new ArrayList<>();

    public Promotion() {
    }

    public Promotion(String promotion, List<Etudiant> etudiants) {
        this.promotion = promotion;
//        this.etudiants = etudiants;
    }

    public Promotion(String promotion) {
        this.promotion = promotion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public List<Attribution> getListeAttribution() {
        return listeAttribution;
    }

    public void setListeAttribution(List<Attribution> listeAttribution) {
        this.listeAttribution = listeAttribution;
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

//    public List<Etudiant> getEtudiants() {
//        return etudiants;
//    }
//
//    public void setEtudiants(List<Etudiant> etudiants) {
//        this.etudiants = etudiants;
//    }

//    public List<Cours> getListOfCourse() {
//        return listOfCourse;
//    }
//
//    public void setListOfCourse(List<Cours> listOfCourse) {
//        this.listOfCourse = listOfCourse;
//    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", promotion=" + promotion + '}';
    }
    

}
