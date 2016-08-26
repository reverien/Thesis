package ub.edu.bi;

import java.io.Serializable;
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

@Entity
@Table(name = "tbl_note3")
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "select n from Note n ORDER BY N.id DESC"),
    @NamedQuery(name = "Note.findBy4Id", query = "select n from Note n where n.insc.id = :idInscription AND n.attrib.id = :idAttr and n.cl.id = :idClasse and n.promo.id = :idAnnee and n.sess.id = :idSession"),
    @NamedQuery(name = "Note.findBy3Id", query = "select  n from Note n where  n.cl.id = :idClasse and n.promo.id = :idAnnee and n.sess.id = :idSession"),
    @NamedQuery(name = "Note.findNoteBy4Id", query = "select  n from Note n where n.insc.id = :idInscription and  n.cl.id = :idClasse and n.promo.id = :idAnnee and n.sess.id = :idSession"),
    @NamedQuery(name = "Note.findNoteFicheBy4Id", query = "select  n from Note n where  n.cl.id = :idClasse and n.promo.id = :idAnnee and n.sess.id = :idSession and n.attrib.course.id = :idCours"),
    @NamedQuery(name = "Note.findByAttribition3Id", query = "select DISTINCT n.attrib from Note n where  n.cl.id = :idClasse and n.promo.id = :idAnnee and n.sess.id = :idSession"),
    @NamedQuery(name = "Note.findByInscription3Id", query = "select DISTINCT n.insc from Note n where  n.cl.id = :idClasse and n.promo.id = :idAnnee and n.sess.id = :idSession"),
    @NamedQuery(name = "Note.findClasseByProfesseur", query = "select DISTINCT n.cl from Note n where  n.attrib.professor.id = :idProfesseur"),
    @NamedQuery(name = "Note.findAnneByProfesseur", query = "select DISTINCT n.promo from Note n where  n.attrib.professor.id = :idProfesseur"),
    @NamedQuery(name = "Note.findSessionByProfesseur", query = "select DISTINCT n.sess from Note n where  n.attrib.professor.id = :idProfesseur"),
    @NamedQuery(name = "Note.findCoursByProfesseur", query = "select DISTINCT  n.attrib.course from Note n where  n.attrib.professor.id = :idProfesseur"),
//    @NamedQuery(name = "Note.findBy5Id", query = "select n from Note n where n.insc.id = :idInscription AND n.attrib.id = :idAttr and n.cl.id = :idClasse and n.promo.id = :idAnnee and n.sess.id = :idSession"),
})
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double note;

    @JoinColumn(name = "id_classe")
    @ManyToOne
    private Classe cl;

    @JoinColumn(name = "id_annee")
    @ManyToOne
    private Promotion promo;

    @JoinColumn(name = "id_typeSession")
    @ManyToOne
    private TypeInscription sess;

    @JoinColumn(name = "id_attribution")
    @ManyToOne
    private Attribution attrib;

    @JoinColumn(name = "id_inscription")
    @ManyToOne
    private Inscription insc;

    @PostConstruct
    public void init() {
        cl = new Classe();
        promo = new Promotion();
        sess = new TypeInscription();
        attrib = new Attribution();
        insc = new Inscription();
    }

    public Note() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Classe getCl() {
        return cl;
    }

    public void setCl(Classe cl) {
        this.cl = cl;
    }

    public Promotion getPromo() {
        return promo;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
    }

    public TypeInscription getSess() {
        return sess;
    }

    public void setSess(TypeInscription sess) {
        this.sess = sess;
    }

    public Attribution getAttrib() {
        return attrib;
    }

    public void setAttrib(Attribution attrib) {
        this.attrib = attrib;
    }

    public Inscription getInsc() {
        return insc;
    }

    public void setInsc(Inscription insc) {
        this.insc = insc;
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + id + ", note=" + note + ", cl=" + cl + ", promo=" + promo + ", sess=" + sess + ", attrib=" + attrib + ", insc=" + insc + '}';
    }

}
