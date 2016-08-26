package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Classe;
import ub.edu.bi.Semestre;
import ub.edu.bi.Unite;

public class ImplementUnite {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

    public Unite addUnite(Unite u) {
        getEm().getTransaction().begin();
        getEm().persist(u);
        getEm().getTransaction().commit();
        return u;
    }

    public List<Unite> selectAllUnite() {
        Query query = getEm().createNamedQuery("Unite.findAll");
        return query.getResultList();
    }

    public Unite UpdateUnite(Unite u) {
        getEm().getTransaction().begin();
        u = getEm().merge(u);
        getEm().getTransaction().commit();
        return u;
    }

    public void deleteUnite(Unite u) {
        getEm().getTransaction().begin();
        u = getEm().merge(u);
        getEm().remove(u);
        getEm().getTransaction().commit();
    }

    public List<Unite> selectByClasse(Long classeCode) {
        try {
            Query query = getEm().createNamedQuery("Unite.findByClasse");
            query.setParameter("idClasse", classeCode);
            List<Unite> liste = query.getResultList();
            return liste;
        } catch (NoResultException e) {
            return null;
        }
    }
    
 
    public List<Unite> selectByClasseAndSemestre(Long idClasse, Long idSemestre) {
        try {
            Query query = getEm().createNamedQuery("Unite.findByClasseAndSemestre");
            query.setParameter("idClasse", idClasse);
            query.setParameter("idSemestre", idSemestre);
            List<Unite> liste = query.getResultList();
            return liste;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Unite> selectBySemestre(Long semestreCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Unite> selectByClasseSemestre(Long classeCode, Long semestreCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Unite AjoutUnite(Unite u, Long codeClasse, Long codeSemestre) {
        if ((codeClasse != null) && (codeSemestre != null)) {
            Classe c = getEm().find(Classe.class, codeClasse);
            u.setClasseCode(c);
            Semestre s = getEm().find(Semestre.class, codeSemestre);
            u.setSemestreCode(s);
        }
        getEm().getTransaction().begin();
        getEm().persist(u);
        getEm().getTransaction().commit();
        return u;
    }

    public Unite selectById(Long id) {
        try {
            Query query = getEm().createNamedQuery("Unite.findById").setParameter("idUnite", id).setMaxResults(1);
            return (Unite) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Unite selectByUnite(Long unite) {
        try {
            Query query = getEm().createNamedQuery("Unite.findById").setParameter("unite", unite).setMaxResults(1);
            return (Unite) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Unite selectByCode(String c) {
        try {
            Query query = getEm().createNamedQuery("Unite.findByCode").setParameter("codeUnite", c).setMaxResults(1);
            return (Unite) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
