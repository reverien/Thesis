package ub.edu.bi.controleur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import ub.edu.bi.Session;
import ub.edu.bi.dao.ImplementSession;

@ManagedBean(name = "typeSession")
@SessionScoped
public class ControleurSession implements Serializable{
    private static final long serialVersionUID = -1;
    private DataModel sessions;
    private Session newSession;
    private Session editSession;
    private ImplementSession sessionDAO;
    private List<Session> selectAllSession;
    
    @PostConstruct
    public void init(){
        newSession = new Session();
        sessionDAO = new ImplementSession();
        selectAllSession = new ArrayList<>();
    }
    
    public String createSession(){
        sessionDAO.insertSession(newSession);
        newSession = new Session();
        return "/Vue/Session/View?faces-redirect=true";
    }
    
    public String editSession(){
        editSession = (Session) sessions.getRowData();
        return "updateSession";
    }
    
    public String modifierSession(){
        sessionDAO.updateSession(editSession);
        sessions.setWrappedData(sessionDAO.selectSession());
        return "View_Session";
    }
    
    public  void deleteSession(){
        Session s = (Session) sessions.getRowData();
        sessionDAO.deleteSession(s);
    }

    public List<Session> getSelectAllSession() {
        selectAllSession = sessionDAO.selectSession();
        return selectAllSession;
    }

    public void setSelectAllSession(List<Session> selectAllSession) {
        this.selectAllSession = selectAllSession;
    }
    
    

    public DataModel getSessions() {
        if(sessions == null){
            sessions = new ListDataModel();
            sessions.setWrappedData(sessionDAO.selectSession());
        }
        return sessions;
    }

    public void setSessions(DataModel sessions) {
        this.sessions = sessions;
    }

    public Session getNewSession() {
        return newSession;
    }

    public void setNewSession(Session newSession) {
        this.newSession = newSession;
    }

    public Session getEditSession() {
        return editSession;
    }

    public void setEditSession(Session editSession) {
        this.editSession = editSession;
    }

    public ImplementSession getSessionDAO() {
        return sessionDAO;
    }

    public void setSessionDAO(ImplementSession sessionDAO) {
        this.sessionDAO = sessionDAO;
    }
    
    
}
