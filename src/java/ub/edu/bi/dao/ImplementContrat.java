package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.ContratType;

public class ImplementContrat {
    
    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

    
    public ContratType InsertContrat(ContratType c) {
        getEm().getTransaction().begin();
        getEm().persist(c);
        getEm().getTransaction().commit();
        return c;
    }

  
    public List<ContratType> selectContrat() {
        Query querry = getEm().createNamedQuery("ContratType.findAll");
        return querry.getResultList();
    }

  
    public ContratType updateContrat(ContratType c) {
        getEm().getTransaction().begin();
        c = getEm().merge(c);
        getEm().getTransaction().commit();
        return c;
    }

  
    public void deleteContrat(ContratType c) {
        getEm().getTransaction().begin();
        c = getEm().merge(c);
        getEm().remove(c);
        getEm().getTransaction().commit();
    }

  
    public ContratType selectById(Long id) {
        try {
            Query query = getEm().createNamedQuery("ContratType.findById").setParameter("id", id).setMaxResults(1);
            return (ContratType) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
   }

}
