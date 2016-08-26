package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ub.edu.bi.Departement;
import ub.edu.bi.dao.ImplementDepartement;

@ManagedBean(name = "departement")
@SessionScoped
public class ControleurDepartement implements Serializable {

    private static final long serialVersionUID = -1;
    private DataModel departements;
    private Departement newDepartement;
    private Departement editDepartement;
    private ImplementDepartement departementDAO;
    private String codeDepart;
    private String depart;
    private String infos;
    private int tailleList;
    private List<Departement> selectAllDepartement;
    private Long postDepartementId;

    @PostConstruct
    public void init() {
        newDepartement = new Departement();
        departementDAO = new ImplementDepartement();
    }

    public void loadDepartement() {
        editDepartement = departementDAO.selectById(postDepartementId);
//        departementDAO.deleteDepartemet(departementDAO.selectById(postDepartementId));
    }

    public String createDepartement() {
        this.codeDepart = newDepartement.getCodeDepart();
        this.depart = newDepartement.getDepartement();
        Departement test1 = departementDAO.selectByCode(codeDepart);
        Departement test2 = departementDAO.selectByDepartement(depart);
        if (test1 != null) {
            infos = "Un departement de meme code existe deja";
            System.out.println("Un departement de meme code existe deja");
            return "/View/Error?faces-redirect=true";
        } else if (test2 != null) {
            infos = "Un departement de meme nom existe deja";
            System.out.println("Un departement de meme nom existe deja");
            return "/View/Error?faces-redirect=true";
        } else {
            try {
                departementDAO.InsertDepartement(newDepartement);
                newDepartement = new Departement();
                infos = "Operation reussie";
                System.out.println("Operation reussie");
            } catch (Exception e) {
                infos = "Opertaion echoue";
                System.out.println("Operation echoue");
                return "/View/Error?faces-redirect=true";
            }
        }
        return "/Vue/Departement/View?faces-redirect=true";
    }

    public String editDepartement(Long id) {
//        editDepartement = (Departement) departements.getRowData();
        return "Update?id=" + id;
    }

    public String modifierDepartement() {
        try {
            departementDAO.UpdateDepartement(editDepartement);
            departements.setWrappedData(departementDAO.selectAllDepartement());
            return "/Vue/Departement/View?faces-redirect=true";
        } catch (Exception e) {
            return "/Vue/Departement/View?faces-redirect=true";
        }

    }

    public void deleteDepartement(Long id) {
        try {
            Departement d = departementDAO.selectById(id);
            departementDAO.deleteDepartemet(d);
            departements.setWrappedData(departementDAO.selectAllDepartement());
        } catch (Exception e) {
        }
    }

    public DataModel getDepartements() {
        if (departements == null) {
            departements = new ListDataModel();
            departements.setWrappedData(departementDAO.selectAllDepartement());
            tailleList = departementDAO.nombreDepartement();
        }
        return departements;
    }

    public void setDepartements(DataModel departements) {
        this.departements = departements;
    }

    public Departement getNewDepartement() {
        return newDepartement;
    }

    public void setNewDepartement(Departement newDepartement) {
        this.newDepartement = newDepartement;
    }

    public Departement getEditDepartement() {
        return editDepartement;
    }

    public void setEditDepartement(Departement editDepartement) {
        this.editDepartement = editDepartement;
    }

    public ImplementDepartement getDepartementDAO() {
        return departementDAO;
    }

    public void setDepartementDAO(ImplementDepartement departementDAO) {
        this.departementDAO = departementDAO;
    }

    public String getCodeDepart() {
        return codeDepart;
    }

    public void setCodeDepart(String codeDepart) {
        this.codeDepart = codeDepart;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
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

    public Long getPostDepartementId() {
        return postDepartementId;
    }

    public void setPostDepartementId(Long postDepartementId) {
        this.postDepartementId = postDepartementId;
    }

    public void setTailleList(int tailleList) {
        this.tailleList = tailleList;
    }

    public List<Departement> getSelectAllDepartement() {
        selectAllDepartement = new ArrayList<>();
        selectAllDepartement = departementDAO.selectAllDepartement();
        tailleList = selectAllDepartement.size();
        return selectAllDepartement;
    }

    public void setSelectAllDepartement(List<Departement> selectAllDepartement) {
        this.selectAllDepartement = selectAllDepartement;
    }

}
