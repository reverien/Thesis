
package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Inscription;


public class InscriptionDAO {
     private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }
    
    public Inscription insertInscription(Inscription insc){
        getEm().getTransaction().begin();
        getEm().persist(insc);
        getEm().getTransaction().commit();
        return insc;
    }
    
    public Inscription selectById(Long id) {
         try {
            Query query = getEm().createNamedQuery("Inscription.findById").setParameter("idInsc", id).setMaxResults(1);
            return (Inscription) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

     }
    
   
    public List<Inscription> selectAllInscription() {
        Query query = getEm().createNamedQuery("Inscription.findAll");
        List<Inscription> liste = query.getResultList();
        return liste;
    }
    
    public List<Inscription> selectByClasseAndSession(Long idClasse, Long idSession){
        Query query = getEm().createNamedQuery("Inscription.findByClasseAndSession").setParameter("idClasse", idClasse).setParameter("idSession", idSession);
        List<Inscription> liste = query.getResultList();
        return liste;
    }
    
    public List<Inscription> selectByClasseAndSessionAndPromotion(Long idClasse, Long idSession, Long idPromotion){
        Query query = getEm().createNamedQuery("Inscription.findByClasseAndSessionAndPromotion").setParameter("idClasse", idClasse).setParameter("idSession", idSession).setParameter("idPromotion", idPromotion);
        List<Inscription> liste = query.getResultList();
        return liste;
    }

}
