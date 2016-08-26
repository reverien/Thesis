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
import ub.edu.bi.Option;
import ub.edu.bi.dao.ImplementDepartement;
import ub.edu.bi.dao.implementOption;

@ManagedBean(name = "option")
@SessionScoped
public class ControleurOption implements Serializable {

    private DataModel options;
    private Option newOption;
    private Option editOption;
    private long a;
    private List<Option> selectAllOption;
    private int tailleList;
    private String code;
    private String option;
    private String infos;
    private implementOption optionDAO;
    private ImplementDepartement departementDAO;
    private Long postIdOption;

    @PostConstruct
    public void init() {
        newOption = new Option();
        optionDAO = new implementOption();
        departementDAO = new ImplementDepartement();
        selectAllOption = new ArrayList<>();
    }

    public void loadOption() {
//        System.out.println("*****************testl'option*****************");
//        editOption = optionDAO.selectById(postIdOption);
//        System.out.println(editOption);
    }

    public String createOption() {
        this.code = newOption.getCode();
        this.option = newOption.getNomOption();
        Option test1 = optionDAO.selectByCode(code);
        Option test2 = optionDAO.selectbyOption(option);
        if (test1 != null) {
            infos = "Une option de meme code existe dans la base";
            return "/Error/Option?faces-redirect=true";
        } else if (test2 != null) {
            infos = "Une option de meme nom existe dans la base";
            return "/Error/Option?faces-redirect=true";
        } else {
            try {
                String c = newOption.getCode(); //recuperation du code de l'option saisie au clavier
                ImplementDepartement d = new ImplementDepartement();
                Departement depart = d.selectById(a); //recuperation de l'identifiant de l'objet selectionner dans le comboBox 
                String codeDepart = depart.getCodeDepart(); //recuperation du code du departement
                String codeOption = "FSI-" + codeDepart + "-" + c; //creation du code de l'option a stocker
                newOption.setCode(codeOption);   //Modification du code dans l'objet option          
                optionDAO.AddOption(newOption, a); //Enregistrement de l'objet
                newOption = new Option("", "");
                return "/Vue/Option/View?faces-redirect=true";
            } catch (Exception e) {
                infos = "L'operation echoue. reessayez!";
                return "/Error/Option?faces-redirect=true";
            }
        }
    }

    public String editOption(Long idOption) {
        postIdOption = idOption;
        
        System.out.println("ID : "+postIdOption);
        editOption = optionDAO.selectById(postIdOption);
        
        System.out.println(editOption);
        return "Update?opt=" + idOption;
    }

    public String modifierOption() {
        try {
            System.out.println("Recuperer id depatement "+editOption.getCodeDepartement().getId());
            editOption.setCodeDepartement(departementDAO.selectById(editOption.getCodeDepartement().getId()));
            optionDAO.UpdateOption(editOption);
            options.setWrappedData(optionDAO.selectAllOption());
        } catch (Exception e) {
        }
        return "/Vue/Option/View?faces-redirect=true";
    }

    public DataModel getOptions() {
        if (options == null) {
            options = new ListDataModel();
            options.setWrappedData(optionDAO.selectAllOption());
            tailleList = options.getRowCount();
        }
        return options;
    }

    public void setOptions(DataModel options) {
        this.options = options;
    }

    public Option getNewOption() {
        return newOption;
    }

    public void setNewOption(Option newOption) {
        this.newOption = newOption;
    }

    public Option getEditOption() {
        return editOption;
    }

    public void setEditOption(Option editOption) {
        this.editOption = editOption;
    }

    public long getA() {
        return a;
    }

    public void setA(long a) {
        this.a = a;
    }

    public implementOption getOptionDAO() {
        return optionDAO;
    }

    public int getTailleList() {
        return tailleList;
    }

    public void setTailleList(int tailleList) {
        this.tailleList = tailleList;
    }

    public Long getPostIdOption() {
        return postIdOption;
    }

    public void setPostIdOption(Long postIdOption) {
        this.postIdOption = postIdOption;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public void setOptionDAO(implementOption optionDAO) {
        this.optionDAO = optionDAO;
    }

    public List<Option> getSelectAllOption() {
        selectAllOption = optionDAO.selectAllOption();
        tailleList = selectAllOption.size();
        return selectAllOption;
    }

    public void setSelectAllOption(List<Option> selectAllOption) {
        this.selectAllOption = selectAllOption;
    }

}
