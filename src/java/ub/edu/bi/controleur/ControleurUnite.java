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
import ub.edu.bi.Semestre;
import ub.edu.bi.Unite;
import ub.edu.bi.dao.ImplementClasse;
import ub.edu.bi.dao.ImplementSemestre;
import ub.edu.bi.dao.ImplementUnite;

@ManagedBean(name = "unite")
@SessionScoped
public class ControleurUnite implements Serializable{
    private static final long serialVersionUID = -1;

    private DataModel unites;
    private Unite newUnite ;
    private Long a, b;
    private Unite editUnit;
    private String infos;
    private ImplementUnite uniteDAO;
    private List<Unite> selectAllUnite;
    
    
    @PostConstruct
    public void init(){
        uniteDAO = new ImplementUnite();
        newUnite = new Unite();
        selectAllUnite = new ArrayList<>();
    }

    public String createUnite() {
        try {
            int numero = newUnite.getNumero();
            String n = newUnite.getNomUnite();
            
            if(numero == 0 && n.equals("")){
                infos = "L'unite et le numero doivent etre null. Reessayez!";
                return "/Error/Unite?faces-redirect=true";
            }
           
            ImplementSemestre s = new ImplementSemestre();
            Semestre sem = s.selectById(b);
            String semestre = sem.getSemestre();

            ImplementClasse c = new ImplementClasse();
            Classe cl = c.selectById(a);
            String clas = cl.getNomClasse();
            String codeUnite = clas + "-" + numero;
            Unite test = uniteDAO.selectByCode(codeUnite);
            newUnite.setCode(codeUnite);

            if (test != null) {
                infos = "Une unite de meme code existe. Reessayez!";
                return "/Error/Unite?faces-redirect=true";
            } else {
                uniteDAO.AjoutUnite(newUnite, a, b);
                newUnite = new Unite("", "", 0, "");
                infos = "L'operation reussie";
                return "/Vue/Unite/View?faces-redirect=true";
            }
        } catch (Exception e) {
            infos = "Echec d'enregistrement. Contactez l'administrateur";
            return "/Error/Unite?faces-redirect=true";
        }    
    }

    public String editUnite() {
        editUnit = (Unite) unites.getRowData();
        return "Update_Unite";
    }

    public String modifierUnite() {
        uniteDAO.UpdateUnite(editUnit);
        unites.setWrappedData(uniteDAO.selectAllUnite());
        return "View_Unite";
    }

    public void deleteUnite() {
        Unite u = (Unite) unites.getRowData();
        uniteDAO.deleteUnite(u);
    }

    public List<Unite> getSelectAllUnite() {
        selectAllUnite = uniteDAO.selectAllUnite();
        return selectAllUnite;
    }

    public void setSelectAllUnite(List<Unite> selectAllUnite) {
        this.selectAllUnite = selectAllUnite;
    }
    
    
    

    public DataModel getUnites() {
        if (unites == null) {
            unites = new ListDataModel();
            unites.setWrappedData(uniteDAO.selectAllUnite());
        }
        return unites;
    }

    public void setUnites(DataModel unites) {
        this.unites = unites;
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

    public Unite getNewUnite() {
        return newUnite;
    }

    public void setNewUnite(Unite newUnite) {
        this.newUnite = newUnite;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public Unite getEditUnit() {
        return editUnit;
    }

    public void setEditUnit(Unite editUnit) {
        this.editUnit = editUnit;
    }

    public ImplementUnite getUniteDAO() {
        return uniteDAO;
    }

    public void setUniteDAO(ImplementUnite uniteDAO) {
        this.uniteDAO = uniteDAO;
    }

}
