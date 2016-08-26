package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Credit;

public class ImplementCredit  {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

    
    public Credit InsertCredit(Credit c) {
        getEm().getTransaction().begin();
        getEm().persist(c);
        getEm().getTransaction().commit();
        return c;
    }

  
    public List<Credit> selectCredit() {
        Query query = getEm().createNamedQuery("Credit.findAll");
        return query.getResultList();
    }

   
    public Credit UpdateCredit(Credit c) {
        getEm().getTransaction().begin();
        c = getEm().merge(c);
        getEm().getTransaction().commit();
        return c;
    }

   
    public void deleteCredit(Credit c) {
        getEm().getTransaction().begin();
        c = getEm().merge(c);
        getEm().remove(c);
        getEm().getTransaction().commit();
    }

  
    public Credit selectByCredit(Integer credit) {
        try {
            Query query = getEm().createNamedQuery("Credit.findBycredit").setParameter("c", credit).setMaxResults(1);
            return (Credit) query.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }
   

   
    public int nombreCredit() {   //account the list of credit
        Query query = getEm().createNamedQuery("Credit.findAll");
        int count = query.getResultList().size();
        return count;
    }

}
