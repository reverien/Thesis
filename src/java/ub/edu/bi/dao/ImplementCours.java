package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Classe;
import ub.edu.bi.Cours;
import ub.edu.bi.Credit;
import ub.edu.bi.Unite;

public class ImplementCours {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

    public Cours AddCours(Cours c, Long codeUnite, Long codeCredit) {
        if (codeCredit != 0 && codeUnite != 0) {
            Unite u = getEm().find(Unite.class, codeUnite);
            c.setCodeUnite(u);
            Credit credit = getEm().find(Credit.class, codeCredit);
            c.setCodeCredit(credit);
            getEm().getTransaction().begin();
            getEm().persist(c);
            getEm().getTransaction().commit();
        }
        return c;
    }

    public List<Cours> selectAllCours() {
        List<Cours> c = getEm().createNamedQuery("Cours.findAll").getResultList();
        return c;
    }

    public Cours selectById(Long id) {
        try {
            Query query = getEm().createNamedQuery("Cours.findById").setParameter("idCours", id);
            return (Cours) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public Cours UpdateCours(Cours c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteCours(Cours c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Cours> selectCoursByUnite(Long codeUnite) {
        try {
            Query query = getEm().createNamedQuery("Cours.findCoursByUnite");
            query.setParameter("idUnite", codeUnite);
            List<Cours> liste = query.getResultList();
            return liste;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<Cours> selectByClasseSemestre(Long idClasse, Long idSemestre){
        try {
            Query query = getEm().createNamedQuery("Cours.findByClasseSemestre");
            query.setParameter("idClasse", idClasse);
            query.setParameter("idSemestre", idSemestre);
            List<Cours> liste = query.getResultList();
            return liste;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Cours> selectCoursByCredit(Long codeCredit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cours selectByCodeCours(String codeCours) {
        try {
            Query query = getEm().createNamedQuery("Cours.findByCodeCours").setParameter("codeCours", codeCours).setMaxResults(1);
            return (Cours) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
