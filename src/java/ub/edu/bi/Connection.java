
package ub.edu.bi;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table(name = "tbl_connection")
@NamedQueries({
    @NamedQuery (name = "Connection.findByLoginAndPassword", query = "select c from Connection c where c.login = :login and c.password = :password")
})
public class Connection implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUser;
    private String login;
    private String password;
    private String fonction;
    private String roles;
    private String etatUtilisateur;

    public Connection() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getEtatUtilisateur() {
        return etatUtilisateur;
    }

    public void setEtatUtilisateur(String etatUtilisateur) {
        this.etatUtilisateur = etatUtilisateur;
    }

    @Override
    public String toString() {
        return "Connection{" + "id=" + id + ", idUser=" + idUser + ", login=" + login + ", password=" + password + ", fonction=" + fonction + ", role=" + roles + ", etatUtilisateur=" + etatUtilisateur + '}';
    }
    
    
}
