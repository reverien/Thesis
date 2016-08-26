package ub.edu.bi;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_session")
public class Session implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    private Long id;
    private String typeSession;
//    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//   @JoinTable(name = "tbl_inscription") 
    
//    @ManyToMany(mappedBy = "sessions")
//    private List<Etudiant> etudiants;
//    private  Collection<Etudiant> etudiants;

    public Session() {
    }

    public Session(String typeSession) {
        this.typeSession = typeSession;
    }

//    public Session(String typeSession, List<Etudiant> etudiants) {
//        this.typeSession = typeSession;
//        this.etudiants = etudiants;
//    }

//    public Session(String typeSession, Collection<Etudiant> etudiants) {
//        this.typeSession = typeSession;
//        this.etudiants = etudiants;
//    }
//    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeSession() {
        return typeSession;
    }

    public void setTypeSession(String typeSession) {
        this.typeSession = typeSession;
    }

//    public Collection<Etudiant> getEtudiants() {
//        return etudiants;
//    }
//
//    public void setEtudiants(Collection<Etudiant> etudiants) {
//        this.etudiants = etudiants;
//    }

//    public Collection<Etudiant> getEtudiants() {
//        return etudiants;
//    }
//
//    public void setEtudiants(Collection<Etudiant> etudiants) {
//        this.etudiants = etudiants;
//    }

    @Override
    public String toString() {
        return "Session{" + "id=" + id + ", typeSession=" + typeSession + '}';
    }

    
    
}
