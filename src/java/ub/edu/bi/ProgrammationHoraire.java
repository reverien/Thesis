package ub.edu.bi;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_programmationHoraire")
@NamedQueries({
     @NamedQuery(name = "ProgrammationHoraire.findAll", query = "select p from ProgrammationHoraire p"),
})
public class ProgrammationHoraire implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private DayOfWeek semaine;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Temporal(TemporalType.TIME)
    private Date heureDebut;
    @Temporal(TemporalType.TIME)
    private Date heureFin;
    @ManyToOne
    @JoinColumn(name = "id_cours")
    private Cours prorammerCoursAttribuer;
    @ManyToOne
    @JoinColumn(name = "id_salle")
    private Salle salleEtude;
    @ManyToOne
    @JoinColumn(name = "id_professeur")
    private Professeur programmerProfesseur;
    
    @PostConstruct
    public void init(){
        prorammerCoursAttribuer = new Cours();
        salleEtude = new Salle();
        programmerProfesseur = new Professeur();
    }

    public ProgrammationHoraire() {
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public DayOfWeek getSemaine() {
//        return semaine;
//    }
//
//    public void setSemaine(DayOfWeek semaine) {
//        this.semaine = semaine;
//    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public Cours getProrammerCoursAttribuer() {
        return prorammerCoursAttribuer;
    }

    public void setProrammerCoursAttribuer(Cours prorammerCoursAttribuer) {
        this.prorammerCoursAttribuer = prorammerCoursAttribuer;
    }

    public Salle getSalleEtude() {
        return salleEtude;
    }

    public void setSalleEtude(Salle salleEtude) {
        this.salleEtude = salleEtude;
    }

    public Professeur getProgrammerProfesseur() {
        return programmerProfesseur;
    }

    public void setProgrammerProfesseur(Professeur programmerProfesseur) {
        this.programmerProfesseur = programmerProfesseur;
    }

    @Override
    public String toString() {
        return "ProgrammationHoraire{" + "id=" + id + ",  dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", prorammerCoursAttribuer=" + prorammerCoursAttribuer + ", salleEtude=" + salleEtude + ", programmerProfesseur=" + programmerProfesseur + '}';
    }    
}
