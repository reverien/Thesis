package ub.edu.bi.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.TypeInscription;

public class ImplementTypeInscription implements Serializable{

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }


    public TypeInscription addType(TypeInscription t) {
        getEm().getTransaction().begin();
        getEm().persist(t);
        getEm().getTransaction().commit();
        return t;
    }


    public List<TypeInscription> selectAll() {
        Query query = getEm().createNamedQuery("TypeInscription.findAll");
        List<TypeInscription> liste = query.getResultList();
        return liste;
    }


    public TypeInscription UpdateType(TypeInscription t) {
        getEm().getTransaction().begin();
        t = getEm().merge(t);
        getEm().getTransaction().commit();
        return t;
     }

   
    public void deleteType(TypeInscription t) {
        getEm().getTransaction().begin();
        t = getEm().merge(t);
        getEm().remove(t);
        getEm().getTransaction().commit();
   }

    
    public TypeInscription selectById(Long id) {
        try {
            Query query = getEm().createNamedQuery("TypeInscription.findById").setParameter("idType", id).setMaxResults(1);
            return (TypeInscription) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
   }

  
    public TypeInscription selectByCode(Long code) {
        try {
            Query query = getEm().createNamedQuery("TypeInscription.findByCode").setParameter("code", code).setMaxResults(1);
            return  (TypeInscription) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
