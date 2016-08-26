package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Departement;

public class ImplementDepartement  {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

  
    public Departement InsertDepartement(Departement d) {
        getEm().getTransaction().begin();
        getEm().persist(d);
        getEm().getTransaction().commit();
        return d;
    }

   
    public List<Departement> selectAllDepartement() {
        Query query = getEm().createNamedQuery("Departement.findAll");
        return query.getResultList();
    }

 
    public Departement UpdateDepartement(Departement d) {
        getEm().getTransaction().begin();
        d = getEm().merge(d);
        getEm().getTransaction().commit();
        return d;
    }

 
    public void deleteDepartemet(Departement d) {
        getEm().getTransaction().begin();
        d = getEm().merge(d);
        getEm().remove(d);
        getEm().getTransaction().commit();
    }


    public Departement selectByCode(String c) {
        try {
            Query query = getEm().createNamedQuery("Departement.findByCode").setParameter("code", c).setMaxResults(1);
            return (Departement) query.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }


    public Departement selectByDepartement(String d) {
        try {
            Query query = getEm().createNamedQuery("Departement.findByDepartement").setParameter("depart", d).setMaxResults(1);
            return (Departement) query.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }


    public int nombreDepartement() {
        Query query = getEm().createNamedQuery("Departement.findAll");
        int count = query.getResultList().size();
        return count;
    }


    public Departement selectById(Long id) {
        try {
            Query query = getEm().createNamedQuery("Departement.findById").setParameter("identifiant", id).setMaxResults(1);
            return (Departement) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

}
