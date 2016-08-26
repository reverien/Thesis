package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Salle;

public class ImplementSalle  {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

   
    public Salle InsertSalle(Salle s) {
        getEm().getTransaction().begin();
        getEm().persist(s);
        getEm().getTransaction().commit();
        return s;
    }

   
    public List<Salle> selectAllSalle() {
        Query query = getEm().createNamedQuery("Salle.findAll");
        return query.getResultList();
    }

   
    public Salle UpdateSalle(Salle s) {
        getEm().getTransaction().begin();
        getEm().merge(s);
        getEm().getTransaction().commit();
        return s;
    }

  
    public void deleteSalle(Salle s) {
        getEm().getTransaction().begin();
        s = getEm().merge(s);
        getEm().remove(s);
        getEm().getTransaction().commit();
    }

}
