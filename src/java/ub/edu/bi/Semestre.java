
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
@Table(name = "tbl_semestre")
@NamedQueries({
    @NamedQuery(name = "Semestre.findAll", query = "select s from Semestre s"),
    @NamedQuery(name = "Semestre.findById", query = "select s from Semestre s where s.id = :idSemestre"),
    @NamedQuery(name = "Semestre.findBycode", query = "select s from Semestre s where s.code = :c"),
    @NamedQuery(name = "Semestre.findBySemestre", query = "select s from Semestre s where s.semestre = :s"),
})
public class Semestre implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String semestre ;
    private String code;
    private String description;
    
    @OneToMany(mappedBy = "semestreCode", fetch = FetchType.EAGER)
    private List<Unite> listeUnite = new ArrayList<>();
   

    public Semestre() {
    }

    public Semestre(String semestre, String code, String description) {
        this.semestre = semestre;
        this.code = code;
        this.description = description;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Unite> getListeUnite() {
        return listeUnite;
    }

    public void setListeUnite(List<Unite> listeUnite) {
        this.listeUnite = listeUnite;
    }

    @Override
    public String toString() {
        return "Semestre{" + "id=" + id + ", semestre=" + semestre + ", code=" + code + ", description=" + description + '}';
    }
    
}
