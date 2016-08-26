package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ub.edu.bi.Promotion;
import ub.edu.bi.dao.ImplementPromotion;

@ManagedBean(name = "promotion")
@SessionScoped
public class ControleurPromotion implements Serializable{
    private static final long serialVersionUID = -1;
    private DataModel promotions;
    private Promotion newPromotion = new Promotion();
    private Promotion editPromotion;
    private ImplementPromotion promotionDAO = new ImplementPromotion();
    private List<Promotion> selectAllPromotion;
    
    @PostConstruct
    public  void init(){
        promotionDAO = new ImplementPromotion();
        newPromotion = new Promotion();
        selectAllPromotion = new ArrayList<>();
    }
    
    public String createPromotion(){
        promotionDAO.insertPromotion(newPromotion);
        newPromotion = new Promotion("");
        return "/Vue/Promotion/View?faces-redirect=true";
    }
    
    public String editPromotion(){
        editPromotion = (Promotion) promotions.getRowData();
        return "UpdatePromotion";
    }
    
    public String modifierPromotion(){
        promotionDAO.updatePromotion(editPromotion);
        promotions.setWrappedData(promotionDAO.selectPromotion());
        return "View_Promotion";
    }
    
    public void deletePromotion(){
        Promotion p = (Promotion) promotions.getRowData();
        promotionDAO.deletePromotion(p);
    }

    public List<Promotion> getSelectAllPromotion() {
        selectAllPromotion = promotionDAO.selectPromotion();
        return selectAllPromotion;
    }

    public void setSelectAllPromotion(List<Promotion> selectAllPromotion) {
        this.selectAllPromotion = selectAllPromotion;
    }
    
    

    public DataModel getPromotions() {
        if(promotions == null){
            promotions = new ListDataModel();
            promotions.setWrappedData(promotionDAO.selectPromotion());
        }
        return promotions;
    }

    public void setPromotions(DataModel promotions) {
        this.promotions = promotions;
    }

    public Promotion getNewPromotion() {
        return newPromotion;
    }

    public void setNewPromotion(Promotion newPromotion) {
        this.newPromotion = newPromotion;
    }

    public Promotion getEditPromotion() {
        return editPromotion;
    }

    public void setEditPromotion(Promotion editPromotion) {
        this.editPromotion = editPromotion;
    }

    public ImplementPromotion getPromotionDAO() {
        return promotionDAO;
    }

    public void setPromotionDAO(ImplementPromotion promotionDAO) {
        this.promotionDAO = promotionDAO;
    }
    
}
