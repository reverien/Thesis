package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ub.edu.bi.Decision;
import ub.edu.bi.dao.ImplementDecision;

@ManagedBean(name = "decision")
@SessionScoped
public class ControleurDecision implements Serializable{
    private static final long serialVersionUID = -1;
    private DataModel decisions;
    private Decision newDecision;
    private Decision editDecision;
    private ImplementDecision decisionDAO ;
    private List<Decision> selectAllDecision;
    
    @PostConstruct
    public  void init (){
        newDecision = new Decision();
        decisionDAO = new ImplementDecision();
        selectAllDecision = new ArrayList<>();
    }
    
    public String createDecision(){
        decisionDAO.InsertDecision(newDecision);
        newDecision = new  Decision("", "");
        return "/Vue/Decision/View?faces-redirect=true";
    }
    
    public String editDecision(){
        editDecision = (Decision) decisions.getRowData();
        return "update";
    }
    
    public String ModifierDecision(){
        decisionDAO.UpdateDecision(editDecision);
        decisions.setWrappedData(decisionDAO.selectAllDecision());
        return "View";
    }
    
    public void deleteDecision(){
        Decision d = (Decision) decisions.getRowData();
        decisionDAO.deleteDecision(d);
        decisions.setWrappedData(decisionDAO.selectAllDecision());
    }

    public List<Decision> getSelectAllDecision() {
        selectAllDecision = decisionDAO.selectAllDecision();
        return selectAllDecision;
    }

    public void setSelectAllDecision(List<Decision> selectAllDecision) {
        this.selectAllDecision = selectAllDecision;
    }

    public DataModel getDecisions() {
        if( decisions == null){
            decisions = new ListDataModel();
            decisions.setWrappedData(decisionDAO.selectAllDecision());
        }
        return decisions;
    }

    public void setDecisions(DataModel decisions) {
        this.decisions = decisions;
    }

    public Decision getNewDecision() {
        return newDecision;
    }

    public void setNewDecision(Decision newDecision) {
        this.newDecision = newDecision;
    }

    public Decision getEditDecision() {
        return editDecision;
    }

    public void setEditDecision(Decision editDecision) {
        this.editDecision = editDecision;
    }

    public ImplementDecision getDecisionDAO() {
        return decisionDAO;
    }

    public void setDecisionDAO(ImplementDecision decisionDAO) {
        this.decisionDAO = decisionDAO;
    }

   
    
    
}
