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
@Table(name = "tbl_unit")
@NamedQueries({
    @NamedQuery(name = "Unite.findAll", query = "select u from Unite u"),
    @NamedQuery(name = "Unite.findById", query = "select u from Unite u where u.id = :idUnite"),
    @NamedQuery(name = "Unite.findByUnite", query = "select u from Unite u where u.nomUnite = :unite"),
    @NamedQuery(name = "Unite.findByCode", query = "select u from Unite u where u.code = :codeUnite"),
    @NamedQuery(name = "Unite.findByClasse", query = "select u from Unite u where u.classeCode.id = :idClasse"),
    @NamedQuery(name = "Unite.findByClasseAndSemestre", query = "select u from Unite u WHERE u.classeCode.id = :idClasse AND u.semestreCode.id = :idSemestre")
})
public class Unite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomUnite;
    private String code;
    private int numero;
    private String description;
    

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "codeClasse")
    private Classe classeCode;

    @ManyToOne
    @JoinColumn(name = "codeSemestre")
    private Semestre semestreCode;

    @OneToMany(mappedBy = "codeUnite", fetch = FetchType.EAGER)
    private List<Cours> listecours = new ArrayList<>();

    public Unite() {
    }
    
    @PostConstruct
    public void init(){
        semestreCode = new Semestre();
    }

    public Unite(String nomUnite, String code, int numero, String description) {
        this.nomUnite = nomUnite;
        this.code = code;
        this.numero = numero;
        this.description = description;
    }    

//    public Unite(String nomUnite, String description, Classe classeCode, Semestre semestreCode) {
//        this.nomUnite = nomUnite;
//        this.description = description;
//        this.classeCode = classeCode;
//        this.semestreCode = semestreCode;
//    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Classe getClasseCode() {
        return classeCode;
    }

    public void setClasseCode(Classe classeCode) {
        this.classeCode = classeCode;
    }

    public Semestre getSemestreCode() {
        return semestreCode;
    }

    public void setSemestreCode(Semestre semestreCode) {
        this.semestreCode = semestreCode;
    }

    public List<Cours> getListecours() {
        return listecours;
    }

    public void setListecours(List<Cours> listecours) {
        this.listecours = listecours;
    }

    @Override
    public String toString() {
        return "Unite{" + "id=" + id + ", nomUnite=" + nomUnite + ", description=" + description + ", classeCode=" + classeCode + ", semestreCode=" + semestreCode + '}';
    }

}
