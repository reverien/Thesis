package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Classe;
import ub.edu.bi.Cycle;
import ub.edu.bi.Option;

public class ImplementClasse{
     private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }
    
    
    public Classe InsertClasse(Classe cl) {
        getEm().getTransaction().begin();
        getEm().persist(cl);
        getEm().getTransaction().commit();
        return cl;
    }

    
    public List<Classe> selectClasse() {
        List<Classe> c = getEm().createQuery("select c from Classe c").getResultList();
        return c;
    }

  
    public Classe updateClasse(Classe cl) {
        getEm().getTransaction().begin();
        cl = getEm().merge(cl);
        getEm().getTransaction().commit();
        return cl;
    }

   
    public void deleteClasse(Classe cl) {
        getEm().getTransaction().begin();
        cl = getEm().merge(cl);
        getEm().remove(cl);
        getEm().getTransaction().commit();
    }

    
    public Classe AddClasse(Classe c, Long codeCycle, Long codeOption) {
        Cycle cycle = getEm().find(Cycle.class, codeCycle);
        c.setCodeCycle(cycle);
        Option op = getEm().find(Option.class, codeOption);
        c.setCodeOption(op);
        getEm().getTransaction().begin();
        getEm().persist(c);
        getEm().getTransaction().commit();
        return c;
     }

   
    public Classe selectByClasse(String c) {
         try {
            Query query = getEm().createNamedQuery("Classe.findByClasse").setParameter("classe", c).setMaxResults(1);
            return (Classe) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

 
    public Classe selectById(Long id) {
         try {
            Query query = getEm().createNamedQuery("Classe.findById").setParameter("idClasse", id).setMaxResults(1);
            return (Classe) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

     }
    
}
