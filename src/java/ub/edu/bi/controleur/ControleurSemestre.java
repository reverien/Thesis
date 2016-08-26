package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import ub.edu.bi.Semestre;
import ub.edu.bi.dao.ImplementSemestre;

@ManagedBean(name = "semestre")
@SessionScoped
public class ControleurSemestre implements Serializable{
    private static final long serialVersionUID = -1;

    private DataModel semestres;
    private Semestre newSemestre ;
    private Semestre editSemestre;
//    private ListDataModel<SelectItem> listeSemestre = new ListDataModel<>();
    private ImplementSemestre semestreDAO ;
    private String semestre;
    private String code;
    private String infos;
    private int tailleList;
    private List<Semestre> selectAllSemestre;
    
    @PostConstruct
    public void init(){
        newSemestre = new Semestre();
        semestreDAO = new ImplementSemestre();
        selectAllSemestre = new ArrayList<>();
    }

    public String CreateSemestre() {
        this.code = newSemestre.getCode();
        this.semestre = newSemestre.getSemestre();
        if (code.equals("") || semestre.equals("")) {
            infos = "Le code et semestre sont obligatoire";
            return "/Error/Semestre?faces-redirect=true";
        } else {
            Semestre test1 = semestreDAO.selectByCode(code);
            Semestre test2 = semestreDAO.selectBySemestre(semestre);
            if (test1 != null) {
                infos = "Un semestre de meme code existe";
                return "/Error/Semestre?faces-redirect=true";
            } else if (test2 != null) {
                infos = "Un semestre de meme nom existe";
                return "/Error/Semestre?faces-redirect=true";
            } else {
                try {
                    semestreDAO.InsertSemestre(newSemestre);
                    newSemestre = new Semestre("", "", "");
                    return "/Vue/Semestre/View?faces-redirect=true";
                } catch (Exception e) {
                    infos = "Operation echoue. Reessayez ou contactez l'administrateur";
                    return "/Error/Semestre?faces-redirect=true";
                }
            }
        }
    }

    public String editSemestre() {
        editSemestre = (Semestre) semestres.getRowData();
        return "Update_Semestre";
    }

    public String modifierSemestre() {
        semestreDAO.UpdateSemestre(editSemestre);
        semestres.setWrappedData(semestreDAO.selectAllSemestre());
        return "View_Semestre";
    }

    public void deleteSemestre() {
        Semestre s = (Semestre) semestres.getRowData();
        semestreDAO.deleteSemestre(s);
        semestres.setWrappedData(semestreDAO.selectAllSemestre());
    }

    public List<Semestre> getSelectAllSemestre() {
        selectAllSemestre = semestreDAO.selectAllSemestre();
        return selectAllSemestre;
    }

    public void setSelectAllSemestre(List<Semestre> selectAllSemestre) {
        this.selectAllSemestre = selectAllSemestre;
    }
    
    

    public DataModel getSemestres() {
        if (semestres == null) {
            semestres = new ListDataModel();
            semestres.setWrappedData(semestreDAO.selectAllSemestre());
            tailleList = semestres.getRowCount();
        }
        return semestres;
    }
//    Creation d'une liste pour alimenter le composant jsf "selectOneMenu"

   

    public List<SelectItem> listeSemestre() {
//        List<SelectItem> sem = null;
//        sem.add(new SelectItem(0, "choix"));        
        return semestreDAO.listeSemestre();
    }

    public void setSemestres(DataModel semestres) {
        this.semestres = semestres;
    }

    public Semestre getNewSemestre() {
        return newSemestre;
    }

    public void setNewSemestre(Semestre newSemestre) {
        this.newSemestre = newSemestre;
    }

    public Semestre getEditSemestre() {
        return editSemestre;
    }

    public void setEditSemestre(Semestre editSemestre) {
        this.editSemestre = editSemestre;
    }

    public ImplementSemestre getSemestreDAO() {
        return semestreDAO;
    }

    public void setSemestreDAO(ImplementSemestre semestreDAO) {
        this.semestreDAO = semestreDAO;
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

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public int getTailleList() {
        return tailleList;
    }

    public void setTailleList(int tailleList) {
        this.tailleList = tailleList;
    }

}
