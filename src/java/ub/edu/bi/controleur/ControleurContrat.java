package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ub.edu.bi.ContratType;
import ub.edu.bi.dao.ImplementContrat;
@ManagedBean(name = "contrat")
@RequestScoped
public class ControleurContrat implements Serializable{
    private DataModel contrats;
    private ContratType newContrat;
    private ContratType editContrat;
    private ImplementContrat contratDAO;
    private List<ContratType> selectAllContrat;
    
    @PostConstruct
    public  void init(){
        newContrat = new ContratType();
        contratDAO = new ImplementContrat();
        selectAllContrat = new ArrayList<>();
    }
    
    public  String createContrat(){
        try {
            contratDAO.InsertContrat(newContrat);
        } catch (Exception e) {
        }
        
        return "/Vue/Contrat/View?faces-redirect = true";
    }

    public List<ContratType> getSelectAllContrat() {
        selectAllContrat = contratDAO.selectContrat();
        return selectAllContrat;
    }

    public void setSelectAllContrat(List<ContratType> selectAllContrat) {
        this.selectAllContrat = selectAllContrat;
    }
    
    

    public DataModel getContrats() {
        if(contrats == null){
            contrats = new ListDataModel();
            contrats.setWrappedData(contratDAO.selectContrat());
        }
        return contrats;
    }
    
    public String editContrat(Long id){
        editContrat = contratDAO.selectById(id);
        return "Update?contrat="+id;
    }
    
    public String modifierContat(){
        return "/Vue/Contrat/View";
    }

    public void setContrats(DataModel contrats) {
        this.contrats = contrats;
    }

    public ContratType getNewContrat() {
        return newContrat;
    }

    public void setNewContrat(ContratType newContrat) {
        this.newContrat = newContrat;
    }

    public ContratType getEditContrat() {
        return editContrat;
    }

    public void setEditContrat(ContratType editContrat) {
        this.editContrat = editContrat;
    }

    public ImplementContrat getContratDAO() {
        return contratDAO;
    }

    public void setContratDAO(ImplementContrat contratDAO) {
        this.contratDAO = contratDAO;
    }

    @Override
    public String toString() {
        return "ControleurContrat{" + "contrats=" + contrats + ", newContrat=" + newContrat + ", editContrat=" + editContrat + ", contratDAO=" + contratDAO + '}';
    }
    
    
}
