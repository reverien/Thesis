package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;
import ub.edu.bi.Connection;
import ub.edu.bi.Etudiant;
import ub.edu.bi.dao.ImplementEtudiant;

@ManagedBean(name = "etudiant")
//@javax.faces.bean.ViewScoped
@SessionScoped
public class ControleurEtudiant implements Serializable {

    private static final long serialVersionUID = -1;

    private DataModel etudiants;
    private Etudiant newEtudiant;
    private Etudiant editEtudiant;
    private ImplementEtudiant etudiantDAO;
    private List<Etudiant> selectAllEtudiant;
    private List<Etudiant> listeEtudiant;
    private String matricule;
    private int tailleList;
    private String infos;
    private Date dateInscription;

    private String mat;
    private Long a, b, c, d;
    private String rechercheMatricule;
    
    public void loadEtudiant() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++" + mat);
        editEtudiant = etudiantDAO.selectEtudiantByMatricule(mat);
        System.out.println("afficher l'etudiant" + editEtudiant.getNomEtudiant());

    }

    @PostConstruct
    public void init() {
        newEtudiant = new Etudiant();
        etudiantDAO = new ImplementEtudiant();
        listeEtudiant = new ArrayList<>();
//        editEtudiant = new Etudiant();
    }

    public String creationEtudiant() {
        try {
            for (Etudiant liste : listeEtudiant) {
                newEtudiant = liste;
                etudiantDAO.insertEtudiant(newEtudiant); //Enregistrement de l'objet etudiant dans la base 
                newEtudiant = new Etudiant();//Vider l'objet
            }
            listeEtudiant.removeAll(listeEtudiant);
        } catch (Exception e) {
            infos = "Enregistrement echoue. Reessayez SVP";
        }
        return "/Vue/Etudiant/View?faces-redirect=true";
    }

    public String createEtudiant() {
//        FacesMessage message;       
        this.matricule = newEtudiant.getMatriculeEtudiant(); //recuperation de la matricule saisi par l'utilisateur        
        Etudiant et = etudiantDAO.selectEtudiantByMatricule(matricule); //verification dans la base l'existence de l'etudiant saisi 
        if (et == null) {   //si l'etudiant n'existe pas, procedure d'enregistrement
            String nomUpperCase = newEtudiant.getNomEtudiant().toUpperCase();//conversion en majuscule le nom de l'etudiant
            newEtudiant.setNomEtudiant(nomUpperCase); //stockage du nom en majuscule dans la variable pour le stocker dans la base
            String prenomUpperCase1 = newEtudiant.getPrenomEtudiant().toLowerCase();//conversion en miniscule le prenom de l'etudiant
            String prenom = prenomUpperCase1.replaceFirst(".", (prenomUpperCase1.charAt(0) + "").toUpperCase());//conversion en majuscule la premier lettre du prenom de l'etudiant
            newEtudiant.setPrenomEtudiant(prenom); //stockage du prenom en majuscule dans la variable pour le stocker dans la base
            String natLower = newEtudiant.getNationalite().toLowerCase();
            String natFirstUp = natLower.replaceFirst(".", (natLower.charAt(0) + "").toUpperCase());
            newEtudiant.setNationalite(natFirstUp);
            Date d = newEtudiant.getDateInscription();
//            if(d < toda){ //compare la date saisie a la date actuelle
//            }else{
//            }

            try {
                for (Etudiant liste : listeEtudiant) {
                    newEtudiant = liste;
                    etudiantDAO.insertEtudiant(newEtudiant); //Enregistrement de l'objet etudiant dans la base 
                    newEtudiant = new Etudiant();//Vider l'objet
                }
                listeEtudiant.removeAll(listeEtudiant);
            } catch (Exception e) {
                infos = "Enregistrement echoue. Reessayez SVP";
            }
            System.out.println("Enregistrement du matricule " + matricule + " reussi");

//            etudiants.setWrappedData(etudiantDAO.selectEtudiant());
            getEtudiants();
            infos = "Un nouveau enregistrement dans la base";
        } else {
            System.out.println("L'etudiant " + et.getNomEtudiant() + " " + et.getPrenomEtudiant() + " existe dans la base");
            infos = "Un etudiant de meme matricule existe dans la base";
        }
//            message = new FacesMessage("Succes de l'enregistrement");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }else{
//            message = new FacesMessage("L'etudiant du meme numero matricule existe");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }        
//        etudiants.setWrappedData(etudiantDAO.selectEtudiant());

        return "/Vue/Etudiant/View?faces-redirect=true";
    }

    public String updateEtudiant(String matricule) {
//        editEtudiant = etudiantDAO.selectEtudiantByMatricule(matricule);
//        System.out.println("afficher l'etudiant"+editEtudiant.getNomEtudiant());
        return "/Update/UpDate_Etudiant?mat =" + matricule;
    }

    public String modifierEtudiant() {
//        System.out.println("------------------------"+editEtudiant.getMatriculeEtudiant());
        try {
            etudiantDAO.updateEtudiant(editEtudiant);
            etudiants.setWrappedData(etudiantDAO.selectEtudiant());
            return "/Vue/Etudiant/View?faces-redirect=true";
        } catch (Exception e) {
            return "/Vue/Etudiant/View?faces-redirect=true";
        }

    }

    public String deleteEtudiant(String matricule) {
        try {
            Etudiant e = etudiantDAO.selectEtudiantByMatricule(matricule);
            etudiantDAO.deleteEtudiant(e);
            etudiants.setWrappedData(etudiantDAO.selectEtudiant());
        } catch (Exception e) {
            this.infos = "Impossible de supprimer cet etudiant. Contactez l'administrateur systeme";
            System.out.println("Impossible de suppremer l'etudiant");
            return "/Error/Etudiant?faces-redirect=true";
        }
        return null;
    }

    public List<Etudiant> getSelectAllEtudiant() {
        this.selectAllEtudiant = new ArrayList<>();
//        selectAllEtudiant.add(new Etudiant("-Selectionnez etudiant-"));
        selectAllEtudiant = etudiantDAO.selectEtudiant();
        this.tailleList = etudiantDAO.nombreEtudiant();
        return selectAllEtudiant;
    }

    public DataModel getEtudiants() {

        if (etudiants == null) {
            etudiants = new ListDataModel();
            etudiants.setWrappedData(etudiantDAO.selectEtudiant());
        }
//        System.out.println("le nombre des etudiants" + etudiantDAO.nombreEtudiant());
        tailleList = etudiants.getRowCount();
        return etudiants;
    }

    public String showDetailsStudent(String matricule) {
//        editEtudiant = etudiantDAO.selectEtudiantByMatricule(matricule);
        return "EtudiantDetails?mat=" + matricule;
    }

    public String rechercheByMatricule() {
//        editEtudiant = etudiantDAO.selectEtudiantByMatricule(rechercheMatricule);
//        System.out.println(editEtudiant.getNationalite());
        return "EtudiantDetails?mat=" + rechercheMatricule;
    }

    public void setEtudiants(DataModel etudiants) {
        this.etudiants = etudiants;
    }

    public Etudiant getNewEtudiant() {
        return newEtudiant;
    }

    public void setNewEtudiant(Etudiant newEtudiant) {
        this.newEtudiant = newEtudiant;
    }

    public Etudiant getEditEtudiant() {
        return editEtudiant;
    }

    public void setEditEtudiant(Etudiant editEtudiant) {
        this.editEtudiant = editEtudiant;
    }

    public ImplementEtudiant getEtudiantDAO() {
        return etudiantDAO;
    }

    public void setEtudiantDAO(ImplementEtudiant etudiantDAO) {
        this.etudiantDAO = etudiantDAO;
    }

    public int getTailleList() {
        return tailleList;
    }

    public void setTailleList(int tailleList) {
        this.tailleList = tailleList;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public Long getA() {
        return a;
    }

    public void setA(Long a) {
        this.a = a;
    }

    public Long getB() {
        return b;
    }

    public void setB(Long b) {
        this.b = b;
    }

    public Long getC() {
        return c;
    }

    public void setC(Long c) {
        this.c = c;
    }

    public Long getD() {
        return d;
    }

    public void setD(Long d) {
        this.d = d;
    }

    public String getMat() {
        return mat;
    }

    public void setMat(String mat) {
        this.mat = mat;
    }

    public String getRechercheMatricule() {
        return rechercheMatricule;
    }

    public void setRechercheMatricule(String rechercheMatricule) {
        this.rechercheMatricule = rechercheMatricule;
    }

    public String createListeTemporelleEtudiant() {
//        listeEtudiant = new ArrayList<>();   
//        List<Etudiant> listeTemporelle = new ArrayList<>();
        listeEtudiant.add(newEtudiant);
        newEtudiant = new Etudiant();
        return "/Vue/Etudiant/Add?faces-redirect=true";
    }

    public List<Etudiant> getListeEtudiant() {
//        newEtudiant = new Etudiant();
        return listeEtudiant;
    }

    public void setListeEtudiant(List<Etudiant> listeEtudiant) {
        this.listeEtudiant = listeEtudiant;
    }

}
