package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Attribution;

public class AttributionDAO {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }
    
    public Attribution selectById(Long id) {
         try {
            Query query = getEm().createNamedQuery("Attribution.findById").setParameter("idAttr", id).setMaxResults(1);
            return (Attribution) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
     }
    
     public List<Attribution> selectByProf(Long idp) {
         try {
            Query query = getEm().createNamedQuery("Attribution.findByProf").setParameter("idProf", idp);
            List<Attribution> liste = query.getResultList();
            return liste;
        } catch (Exception e) {
            return null;
        }
     }

    public Attribution InsertAttribution(Attribution at) {
        getEm().getTransaction().begin();
        getEm().persist(at);
        getEm().getTransaction().commit();
        return at;
    }

    public List<Attribution> selectAllAttribution() {
        Query query = getEm().createNamedQuery("Attribution.findAll");
        List<Attribution> liste = query.getResultList();
        return liste;
    }

    public List<Attribution> selectByClasseAndSemestreAndPromotion(Long idClasse, Long idSemestre ,Long idPromotion) {
        Query query = getEm().createNamedQuery("Attribution.findByClasseSemestreAnne");
        query.setParameter("idClasse", idClasse);
        query.setParameter("idSemestre", idSemestre);
        query.setParameter("idAnne", idPromotion);
        List<Attribution> liste = query.getResultList();
        return liste;
    }
}
