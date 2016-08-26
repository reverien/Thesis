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
@Table(name = "tbl_inscription")
@NamedQueries({
    @NamedQuery(name = "Inscription.findAll", query = "select i from Inscription i"),
    @NamedQuery(name = "Inscription.findById", query = "select i from Inscription i WHERE i.id = :idInsc"),
    @NamedQuery(name = "Inscription.findByClasseAndSession", query = "select i from Inscription i where i.cl.id = :idClasse AND i.typeInscr.id = :idSession"),
    @NamedQuery(name = "Inscription.findByClasseAndSessionAndPromotion", query = "select i from Inscription i where i.cl.id = :idClasse AND i.typeInscr.id = :idSession AND i.prom.id = :idPromotion")
})
public class Inscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_etudiant")
    private Etudiant et;

    @ManyToOne
    @JoinColumn(name = "id_classe")
    private Classe cl;

    @ManyToOne
    @JoinColumn(name = "id_typeInscription")
    private TypeInscription typeInscr;

    @ManyToOne
    @JoinColumn(name = "id_anneAcademique")
    private Promotion prom;

    @OneToMany(mappedBy = "insc")
    public List<Note> listeNote = new ArrayList<>();

    public Inscription() {
    }

    @PostConstruct
    public void init() {
        et = new Etudiant();
        cl = new Classe();
        typeInscr = new TypeInscription();
        prom = new Promotion();
    }

    public Inscription(Etudiant et, Classe cl, TypeInscription typeInscr, Promotion prom) {
        this.et = et;
        this.cl = cl;
        this.typeInscr = typeInscr;
        this.prom = prom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Etudiant getEt() {
        return et;
    }

    public void setEt(Etudiant et) {
        this.et = et;
    }

    public Classe getCl() {
        return cl;
    }

    public void setCl(Classe cl) {
        this.cl = cl;
    }

    public TypeInscription getTypeInscr() {
        return typeInscr;
    }

    public void setTypeInscr(TypeInscription typeInscr) {
        this.typeInscr = typeInscr;
    }

    public Promotion getProm() {
        return prom;
    }

    public void setProm(Promotion prom) {
        this.prom = prom;
    }

    public List<Note> getListeNote() {
        return listeNote;
    }

    public void setListeNote(List<Note> listeNote) {
        this.listeNote = listeNote;
    }

    @Override
    public String toString() {
        return "Inscription{" + "id=" + id + ", et=" + et + ", cl=" + cl + ", typeInscr=" + typeInscr + '}';
    }

}
