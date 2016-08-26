package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Cycle;

public class ImplementCycle  {
    
    private static final String jpa_unite = "attributionPU";
    private EntityManager em;
    
    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }
    
  
    public Cycle InsertCycle(Cycle c) {
        getEm().getTransaction().begin();
        getEm().persist(c);
        getEm().getTransaction().commit();
        return c;
    }
    
   
    public List<Cycle> selectAllCycle() {
        Query query = getEm().createNamedQuery("Cycle.findAll");
        return query.getResultList();
    }
    
 
    public Cycle UpdateCycle(Cycle c) {
        getEm().getTransaction().begin();
        c = getEm().merge(c);
        getEm().getTransaction().commit();
        return c;
    }
    
   
    public void deleteCycle(Cycle c) {
        getEm().getTransaction().begin();
        c = getEm().merge(c);
        getEm().remove(c);
        getEm().getTransaction().commit();
    }

    
    public Cycle selectByNom(String nom) {
        try {
            Query query = getEm().createNamedQuery("Cycle.findByNom").setParameter("nom", nom).setMaxResults(1);
            return (Cycle) query.getSingleResult();
        } catch (Exception e) {
        }        
        return null;
    }

   
    public Cycle selectByCode(String code) {
        try {
            Query query = getEm().createNamedQuery("Cycle.findByCode").setParameter("code", code).setMaxResults(1);
            return (Cycle) query.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }
    
  
    public int nombreCycle() {
         Query query = getEm().createNamedQuery("Cycle.findAll");
         int count = query.getResultList().size();
        return count;
    }

   
    public Cycle selectById(Long id) {
    try {
            Query query = getEm().createNamedQuery("Cycle.findById").setParameter("idCycle", id).setMaxResults(1);
            return (Cycle) query.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }
    
}
