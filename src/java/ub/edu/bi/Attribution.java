package ub.edu.bi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
@Table(name = "tbl_attribution")
@NamedQueries({
    @NamedQuery(name = "Attribution.findAll", query = "select a from Attribution a ORDER BY A.id DESC"),
    @NamedQuery(name = "Attribution.findById", query = "select a from Attribution a WHERE A.id = :idAttr"),
    @NamedQuery(name = "Attribution.findByProf", query = "select a from Attribution a WHERE A.professor.id = :idProf"),
    @NamedQuery(name = "Attribution.findByClasseSemestreAnne", query = "select a from Attribution a WHERE A.course.codeUnite.classeCode.id = :idClasse AND A.course.codeUnite.semestreCode.id = :idSemestre AND A.annee.id = :idAnne ORDER BY A.id DESC"),})
public class Attribution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_cours")
    private Cours course;
    @ManyToOne
    @JoinColumn(name = "id_professeur")
    private Professeur professor;
    @ManyToOne
    @JoinColumn(name = "id_promotion")
    private Promotion annee;

    @OneToMany(mappedBy = "attrib", fetch = FetchType.EAGER)
    public List<Note> listeNote = new ArrayList<>();

    @PostConstruct
    public void init() {
        course = new Cours();
        professor = new Professeur();
        annee = new Promotion();
    }

    public Attribution() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cours getCourse() {
        return course;
    }

    public void setCourse(Cours course) {
        this.course = course;
    }

    public Professeur getProfessor() {
        return professor;
    }

    public void setProfessor(Professeur professor) {
        this.professor = professor;
    }

    public Promotion getAnnee() {
        return annee;
    }

    public void setAnnee(Promotion annee) {
        this.annee = annee;
    }

    public List<Note> getListeNote() {
        return listeNote;
    }

    public void setListeNote(List<Note> listeNote) {
        this.listeNote = listeNote;
    }

    @Override
    public String toString() {
        return "Attribution{" + "id=" + id + ", course=" + course + ", professor=" + professor + ", annee=" + annee +  '}';
    }

 

  
}
