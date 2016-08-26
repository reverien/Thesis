package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ub.edu.bi.Grade;
import ub.edu.bi.dao.ImplementGrade;

@ManagedBean(name = "grade")
@SessionScoped
public class ControleurGrade implements Serializable {
    private static final long serialVersionUID = -1;

    private DataModel grades;
    private Grade newGrade = new Grade();
    private Grade editGrade;
    private String infos;
    private ImplementGrade gradeDAO;
    private List<Grade> selectAllGrade;
    
    @PostConstruct
    public void init(){
        gradeDAO = new ImplementGrade();
        selectAllGrade = new  ArrayList<>();
    }

    public String createGrade() {
        try {
            String code = newGrade.getCode();
            Grade test = gradeDAO.selectByCode(code);
            if (test != null) {
                infos = "Le grade de meme nom existe dans la base";
                return "/Error/Grade?faces-redirect=true";
            } else {
                try {
                    gradeDAO.InsertGrade(newGrade);
                    newGrade = new Grade("", "", "");
                    return "/Vue/Grade/View?faces-redirect=true";
                } catch (Exception e) {
                    infos = "Echec! Contactez l'administrateur";
                    return "/Error/Grade?faces-redirect=true";
                }
            }
        } catch (Exception e) {
            infos = "Echec! contactez l'administrateur";
            return "/Error/Grade?faces-redirect=true";
        }
    }

    public String editGrade() {
        editGrade = (Grade) grades.getRowData();
        return "Update_Grade";
    }

    public String modifierGrade() {
        gradeDAO.updateGrade(editGrade);
        grades.setWrappedData(gradeDAO.selectGrade());
        return "View_Grade";
    }

    public String deleteGrade() {
        Grade g = (Grade) grades.getRowData();
        gradeDAO.deleteGrade(g);
        grades.setWrappedData(gradeDAO.selectGrade());
        return "View_Grade";
    }

    public List<Grade> getSelectAllGrade() {
        selectAllGrade = gradeDAO.selectGrade();
        return selectAllGrade;
    }

    public void setSelectAllGrade(List<Grade> selectAllGrade) {
        this.selectAllGrade = selectAllGrade;
    }
    
    

    public DataModel getGrades() {
        if (grades == null) {
            grades = new ListDataModel();
            grades.setWrappedData(gradeDAO.selectGrade());
        }
        return grades;
    }

    public Grade getNewGrade() {
        return newGrade;
    }

    public void setNewGrade(Grade newGrade) {
        this.newGrade = newGrade;
    }

    public Grade getEditGrade() {
        return editGrade;
    }

    public void setEditGrade(Grade editGrade) {
        this.editGrade = editGrade;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public ImplementGrade getGradeDAO() {
        return gradeDAO;
    }

    public void setGradeDAO(ImplementGrade gradeDAO) {
        this.gradeDAO = gradeDAO;
    }
}
