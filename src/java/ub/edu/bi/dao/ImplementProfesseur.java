package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Grade;
import ub.edu.bi.Professeur;

public class ImplementProfesseur {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

    EntityTransaction tx = getEm().getTransaction();

    public Professeur AddProfesseur(Professeur p) {
        tx.begin();
        getEm().persist(p);
        tx.commit();
        return p;
    }

    public Professeur AddProfesseur(Professeur p, Long g) {
        Grade grade = getEm().find(Grade.class, g);
        p.setCodeGrade(grade);
        tx.begin();
        getEm().persist(p);
        tx.commit();
        return p;
    }

    public List<Professeur> selectAllProfesseur() {
        Query query = getEm().createNamedQuery("Professeur.findAll");
        List<Professeur> liste = query.getResultList();
        return liste;
    }

    public Professeur UpdateProfesseur(Professeur p) {
        getEm().getTransaction().begin();
        p = getEm().merge(p);
//        getEm().persist(p);
        getEm().getTransaction().commit();
        return p;
    }

    public void deleteProfesseur(Professeur p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Professeur> selectProfesseurByGrade(Long codeGrade) {
        getEm().find(Professeur.class, codeGrade);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Professeur> SelectProfesseur() {
        Query query = getEm().createNamedQuery("Professeur.findAll");
        List<Professeur> liste = query.getResultList();
        return liste;
    }

    public Professeur selectByMatricule(String mat) {
        try {
            Query query = getEm().createNamedQuery("professeur.findByMatricule").setParameter("mat", mat).setMaxResults(1);
            return (Professeur) (query.getSingleResult());
        } catch (NoResultException e) {
            return null;
        }
    }

    public Professeur selectProfesseurById(Long id) {
        try {
            Query query = getEm().createNamedQuery("professeur.findProfesseurById").setParameter("idProfesseur", id).setMaxResults(1);
            return (Professeur) (query.getSingleResult());
        } catch (NoResultException e) {
            return null;
        }
    }

    public void findById() {
//        Professeur p = getEm().find(Professeur.class, id);
        getEm().flush();
        
    }

}
