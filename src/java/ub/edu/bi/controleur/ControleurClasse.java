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
import ub.edu.bi.Cours;
import ub.edu.bi.Cycle;
import ub.edu.bi.Option;
import ub.edu.bi.Semestre;

import ub.edu.bi.dao.ImplementClasse;
import ub.edu.bi.dao.ImplementCours;
import ub.edu.bi.dao.ImplementCycle;
import ub.edu.bi.dao.ImplementSemestre;
import ub.edu.bi.dao.implementOption;

@ManagedBean(name = "classe")
@SessionScoped
public class ControleurClasse implements Serializable {

    private static final long serialVersionUID = -1;

    private DataModel classes;
    private Classe newClasse;
    private Classe editClasse;
    private Long idSemestre, a, b;
    private String infos;
    private ImplementClasse classeDAO;
    private ImplementCycle CycleDAO;
    private implementOption OptionDAO;
    private ImplementCours coursDAO;
    private ImplementSemestre semestreDAO;
    private List<Classe> selectAllClasse;
    private Integer tailleListClasse;
    private Long postIdClasse;

    @PostConstruct
    public void init() {
        newClasse = new Classe();
        classeDAO = new ImplementClasse();
        CycleDAO = new ImplementCycle();
        OptionDAO = new implementOption();
        coursDAO = new ImplementCours();
        semestreDAO = new ImplementSemestre();
        selectAllClasse = new ArrayList<>();
    }

    public String createClasse() {
        try {
            int level = newClasse.getNiveau();//recuperation du niveau d'etude selectionner dans le formulaire
            implementOption o = new implementOption();
            Option opt = o.selectById(b);   //recuperation de l'objet Option selectionner dans le formulaire
            String codeOpt = opt.getCode();  //recuperation du code de l'objet option selectionner dans le formulaire

            ImplementCycle c = new ImplementCycle();
            Cycle cy = c.selectById(a); //recuperation de l'objet Cycle selectionner dans le formulaire
            String codeCy = cy.getCodeCycle();  //recuperation du code de l'objet cycle selectionner dans le formulaire

            String classe = codeOpt + "-" + codeCy + "" + level;  //definition du nom de la classe a enregistrer
            Classe test = classeDAO.selectByClasse(classe);
            if (test != null) {
                infos = "Une classe de meme nom existe dans la base";
                return "/Error/Classe?faces-redirect=true";
            } else if (level == 3 && a == 2) {
                infos = "Le second cycle doit compter 2 niveau d'etudes";
                return "/Error/Classe?faces-redirect=true";
            } else {
                try {
                    newClasse.setNomClasse(classe);
                    classeDAO.AddClasse(newClasse, a, b);
                    newClasse = new Classe();
                    return "/Vue/Classe/View?faces-redirect=true";
                } catch (Exception e) {
                    infos = "L'operation echoue. Reessayez";
                    return "/Error/Classe?faces-redirect=true";
                }
            }
        } catch (Exception e) {
            infos = "L'operation echoue. Reessayez";
            return "/Error/Classe?faces-redirect=true";
        }

    }

   

    public String editClasse(Long idClasse) {
        postIdClasse = idClasse;
        editClasse = classeDAO.selectById(postIdClasse);
        return "Update?classe=" + idClasse;
    }

    public String modifierClasse() {
//         System.out.println("Recuperer id depatement "+editOption.getCodeDepartement().getId());
//            editOption.setCodeDepartement(departementDAO.selectById(editOption.getCodeDepartement().getId()));

        System.out.println("recuperer Id cycle" + editClasse.getCodeCycle().getId());
        System.out.println("recuperer id option" + editClasse.getCodeOption().getId());
        editClasse.setCodeOption(OptionDAO.selectById(editClasse.getCodeOption().getId()));
        editClasse.setCodeCycle(CycleDAO.selectById(editClasse.getCodeCycle().getId()));
        System.out.println(editClasse);
        classeDAO.updateClasse(editClasse);
        classes.setWrappedData(classeDAO.selectClasse());
        return "View";
    }

    public void deleteClasse(Long id) {
        try {
            Classe c = classeDAO.selectById(id);
            classeDAO.deleteClasse(c);
            classes.setWrappedData(classeDAO.selectClasse());
        } catch (Exception e) {
        }
    }

    public DataModel getClasses() {
        if (classes == null) {
            classes = new ListDataModel();
            classes.setWrappedData(classeDAO.selectClasse());
        }
        return classes;
    }

    public List<Classe> getSelectAllClasse() {
        selectAllClasse = classeDAO.selectClasse();
        tailleListClasse = selectAllClasse.size();
        System.out.println("la liste compte" + tailleListClasse + "classes");
        return selectAllClasse;
    }

    public void setSelectAllClasse(List<Classe> selectAllClasse) {
        this.selectAllClasse = selectAllClasse;
    }

    public Integer getTailleListClasse() {
        return tailleListClasse;
    }

    public void setTailleListClasse(Integer tailleListClasse) {
        this.tailleListClasse = tailleListClasse;
    }

    public void setClasses(DataModel classes) {
        this.classes = classes;
    }

    public Classe getNewClasse() {
        return newClasse;
    }

    public void setNewClasse(Classe newClasse) {
        this.newClasse = newClasse;
    }

    public Classe getEditClasse() {
        return editClasse;
    }

    public void setEditClasse(Classe editClasse) {
        this.editClasse = editClasse;
    }

    public ImplementClasse getClasseDAO() {
        return classeDAO;
    }

    public void setClasseDAO(ImplementClasse classeDAO) {
        this.classeDAO = classeDAO;
    }

    public ImplementCycle getCycleDAO() {
        return CycleDAO;
    }

    public void setCycleDAO(ImplementCycle CycleDAO) {
        this.CycleDAO = CycleDAO;
    }

    public implementOption getOptionDAO() {
        return OptionDAO;
    }

    public void setOptionDAO(implementOption OptionDAO) {
        this.OptionDAO = OptionDAO;
    }

    public Long getPostIdClasse() {
        return postIdClasse;
    }

    public void setPostIdClasse(Long postIdClasse) {
        this.postIdClasse = postIdClasse;
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

    public Long getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(Long idSemestre) {
        this.idSemestre = idSemestre;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

}
