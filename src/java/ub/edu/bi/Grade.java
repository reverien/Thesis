
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
@Table(name = "tbl_grade")
@NamedQueries({
    @NamedQuery(name = "Grade.findAll", query = "select g from Grade g"),
    @NamedQuery(name = "Grade.findByCode", query = "select g from Grade g where g.code = :c"),
    @NamedQuery(name = "Grade.findById", query = "select g from Grade g where g.id = :id")
})
public class Grade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String grade;
    private String code;
    private String description;
    @OneToMany(mappedBy = "codeGrade", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Professeur> listeProfesseur = new ArrayList<>();

    public Grade() {
    }

    public Grade(String grade, String code, String description) {
        this.grade = grade;
        this.code = code;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public List<Professeur> getListeProfesseur() {
        return listeProfesseur;
    }

    public void setListeProfesseur(List<Professeur> listeProfesseur) {
        this.listeProfesseur = listeProfesseur;
    }

    @Override
    public String toString() {
        return "Grade{" + "id=" + id + ", grade=" + grade + ", description=" + description + '}';
    }
    
}
