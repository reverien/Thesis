package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ub.edu.bi.ContratType;
import ub.edu.bi.Grade;
import ub.edu.bi.Professeur;
import ub.edu.bi.dao.ImplementContrat;
import ub.edu.bi.dao.ImplementGrade;
import ub.edu.bi.dao.ImplementProfesseur;

@ManagedBean(name = "professeur")
@SessionScoped
public class ControleurProfesseur implements Serializable {

    private static final long serialVersionUID = -1;

    private DataModel professeurs;
    private Professeur newProfesseur;
    private Professeur editProfesseur;
    private Long a;
    private String infos;
    private Long PostIdProfesseur;
    private ImplementProfesseur professeurDAO;
    private ImplementGrade gradeDAO;
    private ImplementContrat contratDAO;
    private List<Professeur> selectAllProfesseur;
    private List<Professeur> listeTemporelle;

    @PostConstruct
    public void init() {
        newProfesseur = new Professeur();
        listeTemporelle = new ArrayList<>();
        newProfesseur.setCodeGrade(new Grade());
        newProfesseur.setContrat_id(new ContratType());
        professeurDAO = new ImplementProfesseur();
        gradeDAO = new ImplementGrade();
        contratDAO = new ImplementContrat();
    }

    public void loadProfesseur() {
        System.out.println("afficher le param" + professeurDAO.selectProfesseurById(PostIdProfesseur));
        editProfesseur =  professeurDAO.selectProfesseurById(PostIdProfesseur);
//         editProfesseur =  professeurDAO.findById(PostIdProfesseur);
    }
    
     public String modifierProfesseur() {
//        System.out.println("------------------------"+editEtudiant.getMatriculeEtudiant());
        try {
            editProfesseur.setCodeGrade(gradeDAO.selectById(editProfesseur.getCodeGrade().getId()));
            editProfesseur.setContrat_id(contratDAO.selectById(editProfesseur.getContrat_id().getId()));
            professeurDAO.UpdateProfesseur(editProfesseur);
            professeurDAO.findById();
            getSelectAllProfesseur();
             return "/Vue/Professeur/View?faces-redirect=true";
        } catch (Exception e) {
             return "/Vue/Professeur/View?faces-redirect=true";
        }
    }

    public String createProfesseur() {
        System.out.println("-----------------Creation d'un professeur------------------------------");
        try {
            for (Professeur p : listeTemporelle) {
                newProfesseur = p;
                professeurDAO.AddProfesseur(newProfesseur);
                newProfesseur = new Professeur();
            }
            listeTemporelle = new ArrayList<>(); 
            return "/Vue/Professeur/View?faces-redirect=true";
        } catch (Exception e) {
            System.out.println(e);
            infos = "Erreur. Contactez admin";
            return "/Error/Professeur?faces-redirect=true";
        }
    }

    public String createProfesseur1() {
        try {
            String mat = newProfesseur.getMatricule();
            Professeur test = professeurDAO.selectByMatricule(mat);
            if (test != null) {
                infos = "Le professeur de meme matricule existe dans la base";
                return "/Error/Professeur?faces-redirect=true";
            } else {
                try {
                    professeurDAO.AddProfesseur(newProfesseur);
                    return "/View/View_Professeur?faces-redirect=true";
                } catch (Exception e) {
                    infos = "Echec! Contactez l'administrateur";
                    return "/Error/Professeur?faces-redirect=true";
                }
            }
        } catch (Exception e) {
            infos = "Echec! Contactez l'administrateur";
            return "/Error/Professeur?faces-redirect=true";
        }
    }

    public List<Professeur> getSelectAllProfesseur() {
        this.selectAllProfesseur = new ArrayList<>();
        try {
            selectAllProfesseur = professeurDAO.selectAllProfesseur();
        } catch (Exception e) {
        }
        return selectAllProfesseur;
    }

    public void setSelectAllProfesseur(List<Professeur> selectAllProfesseur) {
        this.selectAllProfesseur = selectAllProfesseur;
    }

    public DataModel getProfesseurs() {
        if (professeurs == null) {
            professeurs = new ListDataModel();
            professeurs.setWrappedData(professeurDAO.selectAllProfesseur());
        }
        return professeurs;
    }

    public void setProfesseurs(DataModel professeurs) {
        this.professeurs = professeurs;
    }

    public Professeur getNewProfesseur() {
        return newProfesseur;
    }

    public void setNewProfesseur(Professeur newProfesseur) {
        this.newProfesseur = newProfesseur;
    }

    public Professeur getEditProfesseur() {
        return editProfesseur;
    }

    public void setEditProfesseur(Professeur editProfesseur) {
        this.editProfesseur = editProfesseur;
    }

    public Long getA() {
        return a;
    }

    public void setA(Long a) {
        this.a = a;
    }

    public Long getPostIdProfesseur() {
        return PostIdProfesseur;
    }

    public void setPostIdProfesseur(Long PostIdProfesseur) {
        this.PostIdProfesseur = PostIdProfesseur;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public ImplementProfesseur getProfesseurDAO() {
        return professeurDAO;
    }

    public void setProfesseurDAO(ImplementProfesseur professeurDAO) {
        this.professeurDAO = professeurDAO;
    }

    public ImplementGrade getGradeDAO() {
        return gradeDAO;
    }

    public void setGradeDAO(ImplementGrade gradeDAO) {
        this.gradeDAO = gradeDAO;
    }

    public ImplementContrat getContratDAO() {
        return contratDAO;
    }

    public void setContratDAO(ImplementContrat contratDAO) {
        this.contratDAO = contratDAO;
    }

    public List<Professeur> getListeTemporelle() {
        return listeTemporelle;
    }

    public void setListeTemporelle(List<Professeur> listeTemporelle) {
        this.listeTemporelle = listeTemporelle;
    }

    public String createListeTemporelleProfesseur() {
        System.out.println(newProfesseur.getCodeGrade().getId());
        System.out.println(newProfesseur.getContrat_id().getId());
//        gradeDAO.selectById(newProfesseur.getCodeGrade().getId());
        newProfesseur.setCodeGrade(gradeDAO.selectById(newProfesseur.getCodeGrade().getId()));
        newProfesseur.setContrat_id(contratDAO.selectById(newProfesseur.getContrat_id().getId()));
        listeTemporelle.add(newProfesseur);
        newProfesseur = new Professeur();
        newProfesseur.setCodeGrade(new Grade());
        newProfesseur.setContrat_id(new ContratType());
        return "/Vue/Professeur/Add?faces-redirect=true";
    }

}
