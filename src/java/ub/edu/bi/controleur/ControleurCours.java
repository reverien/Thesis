package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ub.edu.bi.Classe;
import ub.edu.bi.Cours;
import ub.edu.bi.Credit;
import ub.edu.bi.Semestre;
import ub.edu.bi.Unite;
import ub.edu.bi.dao.ImplementClasse;
import ub.edu.bi.dao.ImplementCours;
import ub.edu.bi.dao.ImplementSemestre;
import ub.edu.bi.dao.ImplementUnite;

@ManagedBean(name = "cours")
@SessionScoped
public class ControleurCours implements Serializable {

    private static final long serialVersionUID = -1;

    private DataModel cours;
    private Cours newCours;
    private Cours editCours;
    private Long a, b, idSemestre, idClasse;
    private String infos;
    private ImplementCours coursDAO;
    private ImplementSemestre semestreDAO;
    private ImplementClasse classeDAO;
    private List<Cours> selectAllCours;
    private List<Cours> selectCoursByClasseAndSemestre;
    private List<Unite> selectUniteByClasseAndSemestre;
    private int tailleListCours;
    private boolean activeCours = true;
    
    @PostConstruct
    public void init(){
        newCours = new Cours();
        newCours.setCodeUnite(new Unite());
        newCours.setCodeCredit(new Credit());
        coursDAO = new ImplementCours();
        semestreDAO = new ImplementSemestre();
        classeDAO = new ImplementClasse();
        selectAllCours = new ArrayList<>();
        selectCoursByClasseAndSemestre = new ArrayList<>();
        selectUniteByClasseAndSemestre = new ArrayList<>();
    }

    public void clickOnUniteObjectActiveCoursObject() {
        System.out.println(activeCours);
        activeCours = false;
        System.out.println(activeCours);
    }

    public String createCours() {
        try {
            int numeroCours = newCours.getNumero();
            ImplementUnite un = new ImplementUnite();
            Unite u = un.selectById(a);
            String codeUnite = u.getCode();
            String codeCours = codeUnite + "" + numeroCours;
            newCours.setCodeCours(codeCours);
            Cours test = coursDAO.selectByCodeCours(codeCours);
            if (test != null) {
                infos = "Un cours de meme code existe dans la base";
                test = null;
                return "/Error/Cours?faces-redirect=true";
            } else {
                try {
                    coursDAO.AddCours(newCours, a, b);
                    newCours = new Cours("", 0, "");
                    return "/Vue/Cours/View?faces-redirect=true";
                } catch (Exception e) {
                    infos = "Operation echoue! Contactez l'administrateur SVP";
                    return "/Error/Cours?faces-redirect=true";
                }
            }
        } catch (Exception e) {
            infos = "Erreur! Contactez l'administrateur SVP";
            return "/Error/Cours?faces-redirect=true";
        }
    }

    public String rechercheCoursByClasse() {
        Long idCl = this.idClasse;
        Classe c = classeDAO.selectById(idCl);
        System.out.println("------------------------------" + c);
        newCours.getCodeUnite().setClasseCode(c);
        
        Long idSem = this.idSemestre;
        Semestre s = semestreDAO.selectById(idSem);
        newCours.getCodeUnite().setSemestreCode(s);

        System.out.println("------------------------------" + idCl + " " + idSem);
        selectCoursByClasseAndSemestre = coursDAO.selectByClasseSemestre(idCl, idSem);
        return "/Vue/Cours/FindCoursByClasse?faces-redirect=true";
    }

    public String editCours() {
        editCours = (Cours) cours.getRowData();
        return "Update_Cours";
    }

    public String modifierCours() {
        coursDAO.UpdateCours(editCours);
        cours.setWrappedData(coursDAO.selectAllCours());
        return "View_Cours";
    }

    public void deleteCours() {
        Cours c = (Cours) cours.getRowData();
        coursDAO.deleteCours(c);
    }

    public DataModel getCours() {
        if (cours == null) {
            cours = new ListDataModel();
            cours.setWrappedData(coursDAO.selectAllCours());
        }
        return cours;
    }

    public List<Cours> getSelectAllCours() {
        selectAllCours = coursDAO.selectAllCours();
        tailleListCours = selectAllCours.size();
        return selectAllCours;
    }

    public void setSelectAllCours(List<Cours> selectAllCours) {
        this.selectAllCours = selectAllCours;
    }

    public void setCours(DataModel cours) {
        this.cours = cours;
    }

    public Cours getNewCours() {
        return newCours;
    }

    public void setNewCours(Cours newCours) {
        this.newCours = newCours;
    }

    public Cours getEditCours() {
        return editCours;
    }

    public void setEditCours(Cours editCours) {
        this.editCours = editCours;
    }

    public ImplementCours getCoursDAO() {
        return coursDAO;
    }

    public void setCoursDAO(ImplementCours coursDAO) {
        this.coursDAO = coursDAO;
    }

    public boolean isActiveCours() {
        return activeCours;
    }

    public void setActiveCours(boolean activeCours) {
        this.activeCours = activeCours;
    }

    public int getTailleListCours() {
        return tailleListCours;
    }

    public void setTailleListCours(int tailleListCours) {
        this.tailleListCours = tailleListCours;
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

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public Long getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(Long idSemestre) {
        this.idSemestre = idSemestre;
    }

    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public List<Cours> getSelectCoursByClasseAndSemestre() {
        return selectCoursByClasseAndSemestre;
    }

    public void setSelectCoursByClasseAndSemestre(List<Cours> selectCoursByClasseAndSemestre) {
        this.selectCoursByClasseAndSemestre = selectCoursByClasseAndSemestre;
    }

    public ImplementSemestre getSemestreDAO() {
        return semestreDAO;
    }

    public void setSemestreDAO(ImplementSemestre semestreDAO) {
        this.semestreDAO = semestreDAO;
    }

    public ImplementClasse getClasseDAO() {
        return classeDAO;
    }

    public void setClasseDAO(ImplementClasse classeDAO) {
        this.classeDAO = classeDAO;
    }

    public List<Unite> getSelectUniteByClasseAndSemestre() {
        return selectUniteByClasseAndSemestre;
    }

    public void setSelectUniteByClasseAndSemestre(List<Unite> selectUniteByClasseAndSemestre) {
        this.selectUniteByClasseAndSemestre = selectUniteByClasseAndSemestre;
    }

}
