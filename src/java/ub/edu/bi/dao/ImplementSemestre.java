
package ub.edu.bi.dao;

import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Semestre;

public class ImplementSemestre {
    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }


    public Semestre InsertSemestre(Semestre s) {
        getEm().getTransaction().begin();
        getEm().persist(s);
        getEm().getTransaction().commit();
        return s;
    }

   
    public List<Semestre> selectAllSemestre() {
        Query querry = getEm().createNamedQuery("Semestre.findAll");
        return querry.getResultList();
    }

  
    public Semestre UpdateSemestre(Semestre s) {
        getEm().getTransaction().begin();
        s = getEm().merge(s);
        getEm().getTransaction().commit();
        return s;
    }

 
    public void deleteSemestre(Semestre s) {
        getEm().getTransaction().begin();
        s = getEm().merge(s);
        getEm().remove(s);
        getEm().getTransaction().commit();
    }

  
    public List<SelectItem> listeSemestre() {
        List<SelectItem> listeSemestre = getEm().createQuery("select s from Semestre s").getResultList();
        return listeSemestre;
    }

  
    public Semestre selectByCode(String c) {
        try {
            Query query = getEm().createNamedQuery("Semestre.findBycode").setParameter("c", c).setMaxResults(1);
            return (Semestre) query.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }

  
    public Semestre selectBySemestre(String s) {
        try {
            Query query = getEm().createNamedQuery("Semestre.findBySemestre").setParameter("s", s).setMaxResults(1);
            return (Semestre) query.getSingleResult();
        } catch (Exception e) {
             return null;
        }
   }

  
    public Semestre selectById(Long id) {
         try {
            Query query = getEm().createNamedQuery("Semestre.findById").setParameter("idSemestre", id).setMaxResults(1);
            return (Semestre) query.getSingleResult();
        } catch (Exception e) {
             return null;
        }
    }
    
}
