
package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Decision;

public class ImplementDecision {
    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

  
    public Decision InsertDecision(Decision d) {
        getEm().getTransaction().begin();
        getEm().persist(d);
        getEm().getTransaction().commit();
        return d;
    }

   
    public List<Decision> selectAllDecision() {
        Query query = getEm().createNamedQuery("Decision.findAll");
        return query.getResultList();
     }
   
  
    public Decision UpdateDecision(Decision d) {
        getEm().getTransaction().begin();
        getEm().merge(d);
        getEm().getTransaction().commit();
        return d;
    }

  
    public void deleteDecision(Decision d) {
        getEm().getTransaction().begin();
        d = getEm().merge(d);
        getEm().remove(d);
        getEm().getTransaction().commit();
    }
    
}
