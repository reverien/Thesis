package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Mention;

public class ImplementMention  {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;
    
    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }
    
    
    public Mention InsertMention(Mention m) {
        getEm().getTransaction().begin();
        getEm().persist(m);
        getEm().getTransaction().commit();
        return m;
    }
    
   
    public List<Mention> selectAllMention() {
        Query query = getEm().createNamedQuery("Mention.findAll");
        return query.getResultList();
    }
    
   
    public Mention UpdateMention(Mention m) {
        getEm().getTransaction().begin();
        m = getEm().merge(m);
        getEm().getTransaction().commit();
        return m;
    }
    
 
    public void deleteMention(Mention m) {
        getEm().getTransaction().begin();
        m = getEm().merge(m);
        getEm().remove(m);
        getEm().getTransaction().commit();
    }
    
}
