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
import ub.edu.bi.Credit;
import ub.edu.bi.dao.ImplementCredit;

@ManagedBean(name = "credit")
@SessionScoped
public class ControleurCredit implements Serializable{
    private static final long serialVersionUID = -1;

    private DataModel credits;
    private Credit newCredit;
    private Credit editCredit;
    private ListDataModel<SelectItem> listecredit = new ListDataModel<>();
    private ImplementCredit creditDAO;
    private Integer credit;
    private int tailleList;
    private String infos;
    private List<Credit> selectAllCredit;
    
    @PostConstruct
    public  void init(){
        creditDAO = new ImplementCredit();
        newCredit = new Credit();
        selectAllCredit = new ArrayList<>();
    }

    public String createCredit() {
        this.credit = newCredit.getCredit();
        if (credit > 6 || credit < 1) {
            infos = "Le nombre de credit doit etre compris entre 2 a 4";
            System.out.println("Le nombre de credit doit etre compris entre 2 a 4");
            newCredit = new Credit(0, "");
            return "/Error/credit?faces-redirect=true";
        } else {
            Credit c = creditDAO.selectByCredit(credit);
            if (c != null) {
                infos = "Le credit existe deja";
                System.out.println("le credit existe deja");
                return "/Error/credit?faces-redirect=true";
            } else {
                try {
                    creditDAO.InsertCredit(newCredit);
                    infos = "Operation reussie";
                    System.out.println("Operation reussie");
                    newCredit = new Credit(0, "");
                    return "/Vue/Credit/View?faces-redirect=true";
                } catch (Exception e) {
                    infos = "Operation echouee. reessayez svp!";
                    System.out.println("Operation echoue");
                    newCredit = new Credit(0, "");
                    return "/Error/credit?faces-redirect=true";
                }
            }
        }
    }

    public String editCredit() {
        editCredit = (Credit) credits.getRowData();
        return "Update_Credit";
    }

    public String modifierCredit() {
        creditDAO.UpdateCredit(editCredit);
        credits.setWrappedData(creditDAO.selectCredit());
        return "View_Credit";
    }

    public void deleteCredit() {
        Credit c = (Credit) credits.getRowData();
        creditDAO.deleteCredit(c);
        credits.setWrappedData(creditDAO.selectCredit());
    }

    public List<Credit> getSelectAllCredit() {
        selectAllCredit = creditDAO.selectCredit();
        return selectAllCredit;
    }

    public void setSelectAllCredit(List<Credit> selectAllCredit) {
        this.selectAllCredit = selectAllCredit;
    }
    
    

    public DataModel getCredits() {
        if (credits == null) {
            credits = new ListDataModel();
            credits.setWrappedData(creditDAO.selectCredit());
           // tailleList = creditDAO.nombreCredit();
          tailleList =  credits.getRowCount();
        }
        return credits;
    }

    public void setCredits(DataModel credits) {
        this.credits = credits;
    }

    public Credit getNewCredit() {
        return newCredit;
    }

    public void setNewCredit(Credit newCredit) {
        this.newCredit = newCredit;
    }

    public Credit getEditCredit() {
        return editCredit;
    }

    public void setEditCredit(Credit editCredit) {
        this.editCredit = editCredit;
    }

    public ImplementCredit getCreditDAO() {
        return creditDAO;
    }

    public void setCreditDAO(ImplementCredit creditDAO) {
        this.creditDAO = creditDAO;
    }

    public int getTailleList() {
        return tailleList;
    }

    public void setTailleList(int tailleList) {
        this.tailleList = tailleList;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

}
