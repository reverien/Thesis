package ub.edu.bi.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Departement;
import ub.edu.bi.Option;

public class implementOption implements Serializable{
     private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

  
    public Option AddOption(Option p, Long codeDepartement) {
        Departement depart = getEm().find(Departement.class, codeDepartement);
        p.setCodeDepartement(depart);
        getEm().getTransaction().begin();
        getEm().persist(p);
        getEm().getTransaction().commit();
        return p;
     }

    
    public List<Option> selectAllOption() {
        Query query = getEm().createNamedQuery("Option.findAll");
        return query.getResultList();
    }

   
    public Option UpdateOption(Option o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public void deleteOption(Option o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public List<Option> selectByDepartement(Long idDepart) {
    try {
            Query query = getEm().createNamedQuery("Option.findByCode").setParameter("cod", idDepart).setMaxResults(1);
            return (List<Option>) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }        
    }

   
    public Option selectByCode(String code) {
        try {
            Query query = getEm().createNamedQuery("Option.findByCode").setParameter("cod", code).setMaxResults(1);
            return (Option) query.getSingleResult();
        } catch (Exception e) {
             return null;
        }       
    }

 
    public Option selectbyOption(String option) {
        try {
            Query query = getEm().createNamedQuery("Option.findByOption").setParameter("opt", option).setMaxResults(1);
            return (Option) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }    
    }

    
    public Option selectById(Long id) {
         try {
            Query query = getEm().createNamedQuery("Option.findById").setParameter("idOption", id).setMaxResults(1);
            return (Option) query.getSingleResult();
        } catch (Exception e) {
            return null;
        } 
    }
    
}
