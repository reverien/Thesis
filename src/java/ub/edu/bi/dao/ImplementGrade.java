package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Grade;

public class ImplementGrade {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }


    public Grade InsertGrade(Grade g) {
        getEm().getTransaction().begin();
        getEm().persist(g);
        getEm().getTransaction().commit();
        return g;
    }


    public List<Grade> selectGrade() {
        Query querry = getEm().createNamedQuery("Grade.findAll");
        return querry.getResultList();
    }


    public Grade updateGrade(Grade g) {
        getEm().getTransaction().begin();
        g = getEm().merge(g);
        getEm().getTransaction().commit();
        return g;
    }

  
    public void deleteGrade(Grade g) {
        getEm().getTransaction().begin();
        g = getEm().merge(g);
        getEm().remove(g);
        getEm().getTransaction().commit();
    }
    
    public Grade selectById(Long id) {
        try {
            Query query = getEm().createNamedQuery("Grade.findById").setParameter("id", id).setMaxResults(1);
            return (Grade) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
   }

    
    public Grade selectByCode(String code) {
        try {
            Query query = getEm().createNamedQuery("Grade.findByCode").setParameter("c", code).setMaxResults(1);
            return (Grade) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
     }

}
