package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import ub.edu.bi.Session;

public class ImplementSession {
     private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }
    
  
    public Session insertSession(Session sess) {
        getEm().getTransaction().begin();
        getEm().persist(sess);
        getEm().getTransaction().commit();
        return sess;
    }

   
    public List<Session> selectSession() {
        List<Session> s = getEm().createQuery("select s from Session s").getResultList();
        return s;
    }

    
    public Session updateSession(Session sess) {
        getEm().getTransaction().begin();
        sess = getEm().merge(sess);
        getEm().getTransaction().commit();
        return sess;
    }

 
    public void deleteSession(Session sess) {
        getEm().getTransaction().begin();
        sess = getEm().merge(sess);
        getEm().remove(sess);
        getEm().getTransaction().commit();
    }       
}
