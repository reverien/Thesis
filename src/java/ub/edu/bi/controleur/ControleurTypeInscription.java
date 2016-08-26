package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ub.edu.bi.TypeInscription;
import ub.edu.bi.dao.ImplementTypeInscription;

 @ManagedBean(name = "typeInscription")
@SessionScoped
public class ControleurTypeInscription implements Serializable{
     private static final long serialVersionUID = -1;

    private DataModel Types;
    private TypeInscription newType;
    private TypeInscription editType;
    private ImplementTypeInscription typeInscriptionDAO;
    private List<TypeInscription> selectAllTypeInscription;
    
    @PostConstruct
    public void init(){
        newType = new TypeInscription();
        typeInscriptionDAO = new ImplementTypeInscription();
        selectAllTypeInscription = new ArrayList<>();
    }
    
    public String createTypeInscription(){
        typeInscriptionDAO.addType(newType);
        newType = new TypeInscription("", "");
        return "/Vue/TypeInscription/View?faces-redirect=true";
    }

    public List<TypeInscription> getSelectAllTypeInscription() {
        selectAllTypeInscription = typeInscriptionDAO.selectAll();
        return selectAllTypeInscription;
    }

    public void setSelectAllTypeInscription(List<TypeInscription> selectAllTypeInscription) {
        this.selectAllTypeInscription = selectAllTypeInscription;
    }
    
    
     
    public DataModel getTypes() {
        if(Types == null){
            Types = new ListDataModel();
            Types.setWrappedData(typeInscriptionDAO.selectAll());
        }
        return Types;
    }
    
    public  String editType(){
        editType = (TypeInscription) Types.getRowData();
        return "updateType";
    }
    
    public String modifierType(){
        typeInscriptionDAO.UpdateType(editType);
        Types.setWrappedData(typeInscriptionDAO.selectAll());
        return "View";
    }
    
    public  void deleteType(){
        TypeInscription s = (TypeInscription) Types.getRowData();
        typeInscriptionDAO.deleteType(s);
    }

    public TypeInscription getNewType() {
        return newType;
    }

    public void setNewType(TypeInscription newType) {
        this.newType = newType;
    }
    
    
    public void setTypes(DataModel Types) {
        this.Types = Types;
    }

    public TypeInscription getEditType() {
        return editType;
    }

    public void setEditType(TypeInscription editType) {
        this.editType = editType;
    }

    public ImplementTypeInscription getTypeInscriptionDAO() {
        return typeInscriptionDAO;
    }

    public void setTypeInscriptionDAO(ImplementTypeInscription typeInscriptionDAO) {
        this.typeInscriptionDAO = typeInscriptionDAO;
    }

    
    
    
    
}
