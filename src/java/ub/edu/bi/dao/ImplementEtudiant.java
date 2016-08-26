package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Classe;
import ub.edu.bi.Etudiant;
import ub.edu.bi.Promotion;
import ub.edu.bi.TypeInscription;

public class ImplementEtudiant{

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

    EntityTransaction tx = getEm().getTransaction();


    public Etudiant insertEtudiant(Etudiant et) {
        tx.begin();
        getEm().persist(et);
        getEm().flush();
        getEm().refresh(et);
        tx.commit();
        return et;
    }

   
    public List<Etudiant> selectEtudiant() {
        Query query = getEm().createNamedQuery("Etudiant.findAll");
        List<Etudiant> liste = query.getResultList();
        return liste;
    }

 
    public Etudiant updateEtudiant(Etudiant et) {
        getEm().getTransaction().begin();
        et = getEm().merge(et);
        getEm().getTransaction().commit();
        return et;
    }

 
    public void deleteEtudiant(Etudiant et) {
        getEm().getTransaction().begin();
        et = getEm().merge(et);
        getEm().remove(et);
        getEm().getTransaction().commit();
    }

 
    public Etudiant selectEtudiantByMatricule(String matricule) {
        try {
            Query query = getEm().createNamedQuery("Etudiant.findByMatricule").setParameter("matriculeEtudiant", matricule).setMaxResults(1);
            query.setParameter("matriculeEtudiant", matricule);
            return (Etudiant) (query.getSingleResult());
        } catch (NoResultException e) {
            return null;
        }
    }
    
     public Etudiant selectEtudiantById(Long id) {
        try {
            Query query = getEm().createNamedQuery("Etudiant.findById").setParameter("idEtudiant", id).setMaxResults(1);
            return (Etudiant) (query.getSingleResult());
        } catch (NoResultException e) {
            return null;
        }
    }

    public long findSalaryForNameAndDepartment(String deptName, String empName) {
        try {
            return (Long) em.createNamedQuery("findSalaryForNameAndDepartment").setParameter("deptName",
                    deptName).setParameter("empName", empName).getSingleResult();
        } catch (NoResultException e) {
            return 0;
        }
    }


    public int nombreEtudiant() {
        Query query = getEm().createNamedQuery("Etudiant.findAll");
        int count = query.getResultList().size();
        return count;
    }

 
    public void inscrireEtudiant(Long idClasse, Long idType, Long idEtudiant, Long idAnnee) {

//        Query query = getEm().createNamedQuery("Etudiant.findById").setParameter("idEtudiant", idEtudiant);
//        Etudiant et = (Etudiant) query.getSingleResult();
        Etudiant et = getEm().find(Etudiant.class, idEtudiant);
        TypeInscription typ = getEm().find(TypeInscription.class, idType);
        Classe cl = getEm().find(Classe.class, idClasse);
        Promotion prom = getEm().find(Promotion.class, idAnnee);

        tx.begin();
        System.out.println("begin ok");
//        typ.getEtudiants().add(et);
//        et.getTypeInscriptions().add(typ);
        getEm().persist(et);
        System.out.println("etudiant type ok");

//        cl.getEtudiants().add(et);
//        et.getClasses().add(cl);
        getEm().persist(et);

//        prom.getEtudiants().add(et);
//        et.getPromotions().add(prom);
        getEm().persist(et);
        tx.commit();

        System.out.println("test DAO reussi");
    }

}
