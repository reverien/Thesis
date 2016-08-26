package ub.edu.bi;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Test {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("attributionPU").createEntityManager();
        Etudiant et = new Etudiant();
        et.setNomEtudiant("Ndab");
        et.setPrenomEtudiant("rever");
        et.setMatriculeEtudiant("08/714");
//        et.setPromotions((Collection<Promotion>) new Promotion("2015"));
//        et.setClasses((Collection<Classe>) new Classe("Bac+5"));
//        et.setSessions((Collection<Session>) new Session("Premiere session"));

        em.getTransaction().begin();
        em.persist(et);
        em.persist(new Etudiant("NDABOROHEYE", "Reverien", "08/714", "Burundaise", null));
        em.persist(new Etudiant(null, null, null, null, null));
       // em.persist(new Classe("TIC/BAC+3"));
        em.persist(new Promotion("2015"));
        em.persist(new Session("session 1"));
        em.getTransaction().commit();

        Etudiant e = em.find(Etudiant.class, 1L);
        Promotion p = em.find(Promotion.class, 1L);
        Session s = em.find(Session.class, 1L);
        Classe c = em.find(Classe.class, 1L);

        em.getTransaction().begin();
//            e.getPromotions().add(p);
//            p.getEtudiants().add(e);        
//            e.getSessions().add(s);
//            s.getEtudiants().add(e);        
//            e.getClasses().add(c);
//            c.getEtudiants().add(e);
        em.getTransaction().commit();
    }
}

