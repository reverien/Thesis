package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Attribution;
import ub.edu.bi.Classe;
import ub.edu.bi.Cours;
import ub.edu.bi.Inscription;
import ub.edu.bi.Note;
import ub.edu.bi.Promotion;
import ub.edu.bi.TypeInscription;

public class NoteDAO {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

    public Note InsertNote(Note n) {
        getEm().getTransaction().begin();
        getEm().persist(n);
        getEm().getTransaction().commit();
        return n;
    }

    public List<Note> selectAllNote() {
        Query query = getEm().createNamedQuery("Note.findAll");
        List<Note> liste = query.getResultList();
        return liste;
    }
    
    public Note findNoteByAllId(Long idInscription, Long idAttr, Long idClasse, Long idAnnee, Long idSession) {
        try {
            Query query = getEm().createNamedQuery("Note.findBy4Id");
            query.setParameter("idInscription", idInscription);
            query.setParameter("idAttr", idAttr);
            query.setParameter("idClasse", idClasse);
            query.setParameter("idAnnee", idAnnee);
            query.setParameter("idSession", idSession);
            return (Note) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Note> findByAllId(Long idInscription, Long idAttr, Long idClasse, Long idAnnee, Long idSession) {
        try {
            Query query = getEm().createNamedQuery("Note.findBy4Id");
            query.setParameter("idInscription", idInscription);
            query.setParameter("idAttr", idAttr);
            query.setParameter("idClasse", idClasse);
            query.setParameter("idAnnee", idAnnee);
            query.setParameter("idSession", idSession);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Attribution> findAttributionBy3Id(Long idClasse, Long idAnnee, Long idSession) {
        try {
            Query query = getEm().createNamedQuery("Note.findByAttribition3Id");
            query.setParameter("idClasse", idClasse);
            query.setParameter("idAnnee", idAnnee);
            query.setParameter("idSession", idSession);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Inscription> findInscriptionBy3Id(Long idClasse, Long idAnnee, Long idSession) {
        try {
            Query query = getEm().createNamedQuery("Note.findByInscription3Id");
            query.setParameter("idClasse", idClasse);
            query.setParameter("idAnnee", idAnnee);
            query.setParameter("idSession", idSession);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Note> findBy3Id(Long idClasse, Long idAnnee, Long idSession) {
        try {
            Query query = getEm().createNamedQuery("Note.findBy3Id");
            query.setParameter("idClasse", idClasse);
            query.setParameter("idAnnee", idAnnee);
            query.setParameter("idSession", idSession);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
 
    
      public List<Note> findNoteBy4Id(Long idInscription, Long idClasse, Long idAnnee, Long idSession) {
        try {
            Query query = getEm().createNamedQuery("Note.findNoteBy4Id");
            query.setParameter("idClasse", idClasse);
            query.setParameter("idAnnee", idAnnee);
            query.setParameter("idSession", idSession);
            query.setParameter("idInscription", idInscription);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
      
      public List<Note> findNoteFicheBy4Id(Long idClasse, Long idAnnee, Long idSession, Long idCours) {
        try {
            Query query = getEm().createNamedQuery("Note.findNoteFicheBy4Id");
            query.setParameter("idClasse", idClasse);
            query.setParameter("idAnnee", idAnnee);
            query.setParameter("idSession", idSession);
            query.setParameter("idCours", idCours);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
      
        public List<Classe> findClasseByProfesseur(Long idProfesseur) {
        try {
            Query query = getEm().createNamedQuery("Note.findClasseByProfesseur");
            query.setParameter("idProfesseur", idProfesseur);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Promotion> findAnneByProfesseur(Long idProfesseur) {
        try {
            Query query = getEm().createNamedQuery("Note.findAnneByProfesseur");
            query.setParameter("idProfesseur", idProfesseur);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<TypeInscription> findSessionByProfesseur(Long idProfesseur) {
        try {
            Query query = getEm().createNamedQuery("Note.findSessionByProfesseur");
            query.setParameter("idProfesseur", idProfesseur);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Cours> findCoursByProfesseur(Long idProfesseur) {
        try {
            Query query = getEm().createNamedQuery("Note.findCoursByProfesseur");
            query.setParameter("idProfesseur", idProfesseur);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    

}
