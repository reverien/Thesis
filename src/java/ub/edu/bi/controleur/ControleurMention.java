package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.context.RequestContext;
import ub.edu.bi.Mention;
import ub.edu.bi.dao.ImplementMention;
@ManagedBean(name = "mention")
@SessionScoped
public class ControleurMention implements Serializable{
    private static final long serialVersionUID = -1;
    private DataModel mentions;
    private Mention newMention = new Mention();
    private Mention editMention;
    private ImplementMention mentionDAO = new ImplementMention();
    private List<Mention> listeMention = new ArrayList<>();
    private String infos;
    
    public String createMention(){
        mentionDAO.InsertMention(newMention);
        newMention = new Mention("", "", "");
        setInfos("Operation d'enregistrement reussie.");
        
        RequestContext requestContext = RequestContext.getCurrentInstance();         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");        
        return "/Vue/Mention/View?faces-redirect=true";
    }
    
    public String editMention(){
        editMention = (Mention) mentions.getRowData();
        return "Update_Mention";
    }
    
    public String modifierMention(){
        mentionDAO.UpdateMention(editMention);
        mentions.setWrappedData(mentionDAO.selectAllMention());
        return "View_Mention";
    }
    
    public void deleteMention(){
        Mention m = (Mention) mentions.getRowData();
        mentionDAO.deleteMention(m);
        mentions.setWrappedData(mentionDAO.selectAllMention());
    }

    public DataModel getMentions() {
        if(mentions == null){
            mentions = new ListDataModel();
            mentions.setWrappedData(mentionDAO.selectAllMention());       }
        return mentions;
    }

    public List<Mention> getListeMention() {
        listeMention = mentionDAO.selectAllMention();
        return listeMention;
    }

    public void setListeMention(List<Mention> listeMention) {
        this.listeMention = listeMention;
    }

    public void setMentions(DataModel mentions) {
        this.mentions = mentions;
    }

    public Mention getNewMention() {
        return newMention;
    }

    public void setNewMention(Mention newMention) {
        this.newMention = newMention;
    }

    public Mention getEditMention() {
        return editMention;
    }

    public void setEditMention(Mention editMention) {
        this.editMention = editMention;
    }

    public ImplementMention getMentionDAO() {
        return mentionDAO;
    }

    public void setMentionDAO(ImplementMention mentionDAO) {
        this.mentionDAO = mentionDAO;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }
    
}
