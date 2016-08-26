package ub.edu.bi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
@Table(name = "tbl_professeur")
@NamedQueries({
    @NamedQuery(name = "Professeur.findAll", query = "select p from Professeur p"),
    @NamedQuery(name = "professeur.findByMatricule", query = "select p from Professeur p where p.matricule = :mat "),
    @NamedQuery(name = "professeur.findProfesseurById", query = "select p from Professeur p where p.id = :idProfesseur")
   
})
public class Professeur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomProfesseur;
    private String prenomProfesseur;
    private String matricule;
    private String phone;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "codeGrade")
    private Grade codeGrade;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "contrat_id")
    private ContratType contrat_id;
//    @ManyToMany(mappedBy = "listOfProfesor")
//    private List<Cours> listOfCourses;

    @OneToMany(mappedBy = "professor", fetch = FetchType.EAGER)
    public List<Attribution> listeAttribution = new ArrayList<>();
    
    @OneToMany(mappedBy = "programmerProfesseur",  fetch = FetchType.EAGER)
    public List<ProgrammationHoraire> listeHoraire = new ArrayList<>();

    @PostConstruct
    public void init() {
        codeGrade = new Grade();
        contrat_id = new ContratType();
    }

    public Professeur() {
    }

    public Professeur(String nomProfesseur, String prenomProfesseur, String matricule, String phone, Grade codeGrade, ContratType contrat_id) {
        this.nomProfesseur = nomProfesseur;
        this.prenomProfesseur = prenomProfesseur;
        this.matricule = matricule;
        this.phone = phone;
        this.codeGrade = codeGrade;
        this.contrat_id = contrat_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomProfesseur() {
        return nomProfesseur;
    }

    public void setNomProfesseur(String nomProfesseur) {
        this.nomProfesseur = nomProfesseur;
    }

    public String getPrenomProfesseur() {
        return prenomProfesseur;
    }

    public void setPrenomProfesseur(String prenomProfesseur) {
        this.prenomProfesseur = prenomProfesseur;
    }

    public Grade getCodeGrade() {
        return codeGrade;
    }

    public void setCodeGrade(Grade codeGrade) {
        this.codeGrade = codeGrade;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ContratType getContrat_id() {
        return contrat_id;
    }

    public void setContrat_id(ContratType contrat_id) {
        this.contrat_id = contrat_id;
    }

//    public List<Cours> getListOfCourses() {
//        return listOfCourses;
//    }
//
//    public void setListOfCourses(List<Cours> listOfCourses) {
//        this.listOfCourses = listOfCourses;
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
        return "Professeur{" + "id=" + id + ", nomProfesseur=" + nomProfesseur + ", prenomProfesseur=" + prenomProfesseur + ", matricule=" + matricule + ", phone=" + phone + ", codeGrade=" + codeGrade + ", contrat_id=" + contrat_id + '}';
    }
}
