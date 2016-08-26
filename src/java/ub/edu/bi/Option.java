package ub.edu.bi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "tbl_options")
@NamedQueries({
    @NamedQuery(name = "Option.findAll", query = "select o from Option o"),
    @NamedQuery(name = "Option.findById", query = "select o from Option o where o.id = :idOption"),
    //@NamedQuery(name = "Option.findByCode", query = "select o from Option o where o.codeDepartement = :idDepartement"),
    @NamedQuery(name = "Option.findByCode", query = "select o from Option o where o.code= :cod"),
    @NamedQuery(name = "Option.findByOption", query = "select o from Option o where o.nomOption = :opt")
})
public class Option implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomOption;
    private String code;
    private String desription;
    @ManyToOne
    private Departement codeDepartement;
    @OneToMany(mappedBy = "codeOption")
    private List<Classe> listeClasse = new ArrayList<>();

    public Option() {
    }

    public Option(String nomOption, String desription) {
        this.nomOption = nomOption;
        this.desription = desription;
    }

    public String getNomOption() {
        return nomOption;
    }

    public void setNomOption(String nomOption) {
        this.nomOption = nomOption;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public Departement getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(Departement codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public List<Classe> getListeClasse() {
        return listeClasse;
    }

    public void setListeClasse(List<Classe> listeClasse) {
        this.listeClasse = listeClasse;
    }

    @Override
    public String toString() {
        return "Option{" + "id=" + id + ", nomOption=" + nomOption + ", code=" + code + ", desription=" + desription + ", codeDepartement=" + codeDepartement + ", listeClasse=" + listeClasse + '}';
    }
    
    
}
