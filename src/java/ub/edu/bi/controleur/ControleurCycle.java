package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ub.edu.bi.Cycle;
import ub.edu.bi.dao.ImplementCycle;

@ManagedBean(name = "cycle")
@SessionScoped
public class ControleurCycle implements Serializable{
    private static final long serialVersionUID = -1;

    private DataModel cycles;
    private Cycle newCycle;
    private Cycle editCycle;
    private ImplementCycle cycleDAO;
    private String nom;
    private String code;
    private String infos;
    private int tailleList;
    private List<Cycle> selectAllCycle;
    
    @PostConstruct
    public void init(){
        newCycle = new Cycle();
        cycleDAO = new ImplementCycle();
        selectAllCycle = new ArrayList<>();
    }

    public String createCycle() {
        this.nom = newCycle.getNomCycle();
        this.code = newCycle.getCodeCycle();
        Cycle test1 = cycleDAO.selectByNom(nom);
        Cycle test2 = cycleDAO.selectByCode(code);
        if (test1 != null) {
            infos = "Un cycle de meme nom existe";
            System.out.println("Un cycle de meme nom existe");
            newCycle = new Cycle("", "", "");
            return "/Error/cycle?faces-redirect=true";
        } else if (test2 != null) {
            infos = "Un cycle de meme code existe";
            System.out.println("Un cycle de meme code existe");
            newCycle = new Cycle("", "", "");  //vider l'object
            return "/Error/cycle?faces-redirect=true"; //redirection vers une autre page:page d'erreur
        } else {
            try {
                cycleDAO.InsertCycle(newCycle);
                newCycle = new Cycle("", "", "");
                infos = "Operation reussie";
                System.out.println("Operation reussie");
                return "/Vue/Cycle/View?faces-redirect=true";
            } catch (Exception e) {
                infos = "Operation echouee";
                System.out.println("Operation reussie");
                newCycle = new Cycle("", "", "");
                return "/Error/cycle?faces-redirect=true";
            }

        }
    }

    public String editCycle() {
        editCycle = (Cycle) cycles.getRowData();
        return "Update_Cycle";
    }

    public String modifierCycle() {
        cycleDAO.UpdateCycle(editCycle);
        cycles.setWrappedData(cycleDAO.selectAllCycle());
        return "View_Cycle";
    }

    public void deleteCycle() {
        Cycle c = (Cycle) cycles.getRowData();
        cycleDAO.deleteCycle(c);
        cycles.setWrappedData(cycleDAO.selectAllCycle());
    }

    public List<Cycle> getSelectAllCycle() {
        selectAllCycle = cycleDAO.selectAllCycle();
        return selectAllCycle;
    }

    public void setSelectAllCycle(List<Cycle> selectAllCycle) {
        this.selectAllCycle = selectAllCycle;
    }
    
    
    

    public DataModel getCycles() {
        if (cycles == null) {
            cycles = new ListDataModel();
            cycles.setWrappedData(cycleDAO.selectAllCycle());
            tailleList = cycleDAO.nombreCycle();
        }
        return cycles;
    }

    public void setCycles(DataModel cycles) {
        this.cycles = cycles;
    }

    public Cycle getNewCycle() {
        return newCycle;
    }

    public void setNewCycle(Cycle newCycle) {
        this.newCycle = newCycle;
    }

    public Cycle getEditCycle() {
        return editCycle;
    }

    public void setEditCycle(Cycle editCycle) {
        this.editCycle = editCycle;
    }

    public ImplementCycle getCycleDAO() {
        return cycleDAO;
    }

    public void setCycleDAO(ImplementCycle cycleDAO) {
        this.cycleDAO = cycleDAO;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
