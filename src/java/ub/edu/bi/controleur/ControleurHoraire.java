package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ub.edu.bi.Cours;
import ub.edu.bi.Professeur;
import ub.edu.bi.ProgrammationHoraire;
import ub.edu.bi.Salle;
import ub.edu.bi.dao.ProgrammationHoraireDAO;

@ManagedBean(name = "horaire")
@SessionScoped
public class ControleurHoraire implements Serializable{
    private ProgrammationHoraire newHoraire = new ProgrammationHoraire();
    private ProgrammationHoraire editHoraire;
    private List<ProgrammationHoraire> selectAllHoraire;
    private ProgrammationHoraireDAO dao;
    
    @PostConstruct
    public void init(){
        newHoraire = new ProgrammationHoraire();
        newHoraire.setProgrammerProfesseur(new Professeur());
        newHoraire.setProrammerCoursAttribuer(new Cours());
        newHoraire.setSalleEtude(new  Salle());
        selectAllHoraire = new ArrayList<>();
        dao = new ProgrammationHoraireDAO();
    } 
    
    public String createHoraire(){
        try {
            System.out.println(newHoraire);
            dao.insertHoraire(newHoraire);
            newHoraire = new ProgrammationHoraire();
            return "/Vue/Operation/ProgrammationHoraire/View?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }        
    }

    public ProgrammationHoraire getNewHoraire() {
        return newHoraire;
    }

    public void setNewHoraire(ProgrammationHoraire newHoraire) {
        this.newHoraire = newHoraire;
    }

    public ProgrammationHoraire getEditHoraire() {
        return editHoraire;
    }

    public void setEditHoraire(ProgrammationHoraire editHoraire) {
        this.editHoraire = editHoraire;
    }

    public List<ProgrammationHoraire> getSelectAllHoraire() {
        selectAllHoraire = dao.selectAllHoraire();
        return selectAllHoraire;
    }

    public void setSelectAllHoraire(List<ProgrammationHoraire> selectAllHoraire) {
        this.selectAllHoraire = selectAllHoraire;
    }

    public ProgrammationHoraireDAO getDao() {
        return dao;
    }

    public void setDao(ProgrammationHoraireDAO dao) {
        this.dao = dao;
    }
    
}
