package ub.edu.bi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_etudiant")
@NamedQueries({
    @NamedQuery(name = "Etudiant.findAll", query = "select e from Etudiant e ORDER BY E.id DESC"),
    @NamedQuery(name = "Etudiant.findByMatricule", query = "select e from Etudiant e where e.matriculeEtudiant = :matriculeEtudiant"),
    @NamedQuery(name = "Etudiant.findById", query = "select e from Etudiant e where e.id = :idEtudiant")
//    @NamedQuery(name="findSalaryForNameAndDepartment",query="SELECT e.salary " +"FROM Professor e " +"WHERE e.department.name = :deptName AND " +"e.name = :empName")
//    @NamedQuery(name="findSalaryForName",query="SELECT o.id, o.quantity, o.item, " +"i.id, i.name, i.description " +"FROM Order o, Item i " + "WHERE (o.quantity > 25) AND (o.item = i.id)","OrderItemResults")

})
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etudiant")
    private Long id;
    @NotNull
    private String nomEtudiant;
    @NotNull
    private String prenomEtudiant;
    @NotNull
    private String matriculeEtudiant;
    private String nationalite;
    @Temporal(TemporalType.DATE)
    private Date dateInscription;
    
    @OneToMany(mappedBy = "et",fetch = FetchType.EAGER)
    public List<Inscription> listeInscription = new ArrayList<>();

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "tbl_inscription", joinColumns = @JoinColumn(name = "id_etudiant"), inverseJoinColumns = @JoinColumn(name = "id_TypeInscription"))
//    private Collection<TypeInscription> typeInscriptions;
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "tbl_inscription", joinColumns = @JoinColumn(name = "id_etudiant"), inverseJoinColumns = @JoinColumn(name = "id_promotion"))
//    private Collection<Promotion> promotions;
//
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "tbl_inscription", joinColumns = @JoinColumn(name = "id_etudiant"), inverseJoinColumns = @JoinColumn(name = "id_classe"))
//    private Collection<Classe> classes;

//     @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "tbl_inscription", joinColumns = @JoinColumn(name = "id_etudiant"),inverseJoinColumns = @JoinColumn(name = "id_session"))  
//    private Collection<Session> sessions;
    public Etudiant() {
    }

    public Etudiant(String nomEtudiant, String prenomEtudiant, String matriculeEtudiant, String nationalite, Date dateInscription) {
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.matriculeEtudiant = matriculeEtudiant;
        this.nationalite = nationalite;
        this.dateInscription = dateInscription;
    }

    public Etudiant(String nomEtudiant, String prenomEtudiant, String matriculeEtudiant, Collection<Session> sessions, Collection<Promotion> promotions, Collection<Classe> classes) {
        this.nomEtudiant = nomEtudiant;
        this.prenomEtudiant = prenomEtudiant;
        this.matriculeEtudiant = matriculeEtudiant;
//        this.promotions = promotions;
//        this.classes = classes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomEtudiant() {
        return nomEtudiant;
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant = nomEtudiant;
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant;
    }

    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant = prenomEtudiant;
    }

    public String getMatriculeEtudiant() {
        return matriculeEtudiant;
    }

    public void setMatriculeEtudiant(String matriculeEtudiant) {
        this.matriculeEtudiant = matriculeEtudiant;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

//    public Collection<Session> getSessions() {
//        return sessions;
//    }
//
//    public void setSessions(Collection<Session> sessions) {
//        this.sessions = sessions;
//    }
//    public Collection<Promotion> getPromotions() {
//        return promotions;
//    }
//
//    public void setPromotions(Collection<Promotion> promotions) {
//        this.promotions = promotions;
//    }
//
//    public Collection<Classe> getClasses() {
//        return classes;
//    }
//
//    public void setClasses(Collection<Classe> classes) {
//        this.classes = classes;
//    }
//
//    public Collection<TypeInscription> getTypeInscriptions() {
//        return typeInscriptions;
//    }
//
//    public void setTypeInscriptions(Collection<TypeInscription> typeInscriptions) {
//        this.typeInscriptions = typeInscriptions;
//    }

    public List<Inscription> getListeInscription() {
        return listeInscription;
    }

    public void setListeInscription(List<Inscription> listeInscription) {
        this.listeInscription = listeInscription;
    }

    @Override
    public String toString() {
        return "Etudiant{" + "id=" + id + ", nomEtudiant=" + nomEtudiant + ", prenomEtudiant=" + prenomEtudiant + ", matriculeEtudiant=" + matriculeEtudiant + '}';
    }

}
