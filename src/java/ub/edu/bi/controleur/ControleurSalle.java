package ub.edu.bi.controleur;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ub.edu.bi.Salle;
import ub.edu.bi.dao.ImplementSalle;

@ManagedBean(name = "salle")
@SessionScoped
public class ControleurSalle implements Serializable{
    private static final long serialVersionUID = -1;
    private DataModel salles;
    private Salle newSalle = new Salle();
    private Salle editSalle;
    private ImplementSalle salleDAO = new ImplementSalle();
    
    public String CreateSalle(){
        salleDAO.InsertSalle(newSalle);
        return "/Vue/Salle/View?faces-redirect=true";
    }
    
    public String editSAlle(){
        editSalle = (Salle) salles.getRowData();
        return "Update_Salle";
    }
    
    public String modifierSalle(){
        salleDAO.UpdateSalle(editSalle);
        salles.setWrappedData(salleDAO.selectAllSalle());
        return "View_Salle";
    }
    
    public void deleteSalle(){
        Salle s  = (Salle) salles.getRowData();
        salleDAO.deleteSalle(s);
        salles.setWrappedData(salleDAO.selectAllSalle());
    }

    public DataModel getSalles() {
        if(salles == null){
            salles = new ListDataModel();
            salles.setWrappedData(salleDAO.selectAllSalle());
        }
        return salles;
    }

    public void setSalles(DataModel salles) {
        this.salles = salles;
    }

    public Salle getNewSalle() {
        return newSalle;
    }

    public void setNewSalle(Salle newSalle) {
        this.newSalle = newSalle;
    }

    public Salle getEditSalle() {
        return editSalle;
    }

    public void setEditSalle(Salle editSalle) {
        this.editSalle = editSalle;
    }

    public ImplementSalle getSalleDAO() {
        return salleDAO;
    }

    public void setSalleDAO(ImplementSalle salleDAO) {
        this.salleDAO = salleDAO;
    }
    
}
