package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import ub.edu.bi.Connection;
import ub.edu.bi.Etudiant;
import ub.edu.bi.Professeur;
import ub.edu.bi.dao.ConnectionDAO;
import ub.edu.bi.dao.ImplementEtudiant;
import ub.edu.bi.dao.ImplementProfesseur;

@ManagedBean(name = "connection")
@SessionScoped
public class ControleurConnection implements Serializable {

    private Connection newConnection;
    private ConnectionDAO dao;
    private String login;
    private String password;
    private ImplementProfesseur daoProf;
    private ImplementEtudiant daoEtud;
    private Etudiant etudiant;
    private Professeur professeur;
    private boolean  fiche;

    @PostConstruct
    public void init() {
        newConnection = new Connection();
        dao = new ConnectionDAO();
        daoProf = new ImplementProfesseur();
        daoEtud = new ImplementEtudiant();
        etudiant = new Etudiant();
        professeur = new Professeur();

    }

    public String verifieSession() {
        HttpSession sess = ControleurConnection.findSessionUtilisateur();
        Connection c = new Connection();
        c = (Connection) sess.getAttribute("user");
        if (c == null) {
//            System.out.println(newConnection);
            System.out.println("------------------------la session null----------------------------------------");
            return "/index.xhtml?faces-redirect=true";
        } else {
            System.out.println(c);
            System.out.println("------------------------session non null-------------------------------------");
            return null;
        }
    }

    public String connection() {
        HttpSession sess = ControleurConnection.findSessionUtilisateur();
        List<Connection> liste = dao.selectByLoginAndPassword(newConnection.getLogin(), newConnection.getPassword());
        int taille = liste.size();

        if (taille != 0) {
            newConnection.setId(liste.get(0).getId());
            newConnection.setIdUser(liste.get(0).getIdUser());
            newConnection.setLogin(liste.get(0).getLogin());
            newConnection.setPassword(liste.get(0).getPassword());
            newConnection.setFonction(liste.get(0).getFonction());
            newConnection.setRoles(liste.get(0).getRoles());
            newConnection.setEtatUtilisateur(liste.get(0).getEtatUtilisateur());
            System.out.println(liste.get(0).getId() + " " + liste.get(0).getIdUser() + " " + liste.get(0).getLogin() + " " + liste.get(0).getPassword() + " " + liste.get(0).getFonction() + " " + liste.get(0).getEtatUtilisateur() + " " + liste.get(0).getRoles());
            if (liste.get(0).getFonction().equalsIgnoreCase("etudiant")) {
                System.out.println("etudiant" + daoEtud.selectEtudiantById(liste.get(0).getIdUser()));
                etudiant = daoEtud.selectEtudiantById(liste.get(0).getIdUser());
                sess.setAttribute("loginConnection", newConnection.getLogin());
                sess.setAttribute("passwordConnection", newConnection.getPassword());
                sess.setAttribute("idEtudiant", etudiant.getId());
                sess.setAttribute("fiche", true);
            } else if (liste.get(0).getFonction().equalsIgnoreCase("professeur")) {
                System.out.println("professeur" + daoProf.selectProfesseurById(liste.get(0).getIdUser()));
                professeur = daoProf.selectProfesseurById(liste.get(0).getIdUser());
                sess.setAttribute("loginConnection", newConnection.getLogin());
                sess.setAttribute("passwordConnection", newConnection.getPassword());
                sess.setAttribute("idProfesseur", professeur.getId());
                sess.setAttribute("fiche", false);
            }
            sess.setAttribute("user", newConnection);
            sess.setAttribute("et", etudiant);
            sess.setAttribute("prof", professeur);
            sess.setMaxInactiveInterval(-1);
            this.fiche = (boolean) sess.getAttribute("fiche");
            this.login = (String) sess.getAttribute("loginConnection");
            this.password = (String) sess.getAttribute("passwordConnection");
            return "./Home/Home.xhtml?faces-redirect=true";
        } else {
            return null;
        }

    }

    public static HttpSession findSessionUtilisateur() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        return (HttpSession) fcontext.getExternalContext().getSession(true);
    }

    public String deconnection() {
        HttpSession sess = ControleurConnection.findSessionUtilisateur();
        Connection u = new Connection();
        newConnection = new Connection();
        sess.setAttribute("user", u);
//        System.out.println(sess.getAttribute("user"));
        if (sess.getAttribute("user") != null) {
            //recuperer la session pour enregistrer dans la base
        }
//        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        return "/index.xhtml?faces-redirect=true";
    }

//    public String testConnection(){
//        if(newConnection.getLogin().equalsIgnoreCase(null))
//             return "/index.xhtml?faces-redirect=true";
//        return null;
//               
//    }
    public Connection getNewConnection() {
        return newConnection;
    }

    public void setNewConnection(Connection newConnection) {
        this.newConnection = newConnection;
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

    public boolean isFiche() {
        return fiche;
    }

    public void setFiche(boolean fiche) {
        this.fiche = fiche;
    }

}
