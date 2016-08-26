package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Promotion;

public class ImplementPromotion {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

    public Promotion insertPromotion(Promotion prom) {
        getEm().getTransaction().begin();
        getEm().persist(prom);
        getEm().getTransaction().commit();
        return prom;
    }

    public List<Promotion> selectPromotion() {
        List<Promotion> p = getEm().createQuery("select p from Promotion p").getResultList();
        return p;
    }

    public Promotion selectById(Long id) {
        try {
            Query query = getEm().createNamedQuery("Promotion.findById");
            query.setParameter("idpromotion", id);
            return (Promotion) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public Promotion updatePromotion(Promotion prom) {
        getEm().getTransaction().begin();
        prom = getEm().merge(prom);
        getEm().getTransaction().commit();
        return prom;
    }

    public void deletePromotion(Promotion prom) {
        getEm().getTransaction().begin();
        prom = getEm().merge(prom);
        getEm().remove(prom);
        getEm().getTransaction().commit();
    }

}
