package ub.edu.bi.dao;

import java.util.List;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Classe;
import ub.edu.bi.Etudiant;
import ub.edu.bi.Promotion;
import ub.edu.bi.Session;

public class ImplemtationDAO implements InterfaceInscription {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }

    @Override
    public Classe InsertClasse(Classe cl) {
        getEm().getTransaction().begin();
        getEm().persist(cl);
        getEm().getTransaction().commit();
        return cl;
    }

    @Override
    public List<Classe> selectClasse() {
        List<Classe> c = getEm().createQuery("select c from Classe c").getResultList();
        return c;
    }

    @Override
    public Classe updateClasse(Classe cl) {
        getEm().getTransaction().begin();
        cl = getEm().merge(cl);
        getEm().getTransaction().commit();
        return cl;
    }

    @Override
    public void deleteClasse(Classe cl) {
        getEm().getTransaction().begin();
        cl = getEm().merge(cl);
        getEm().remove(cl);
        getEm().getTransaction().commit();
    }

    @Override
    public Etudiant insertEtudiant(Etudiant et) {
        getEm().getTransaction().begin();
        getEm().persist(et);
        getEm().getTransaction().commit();
        return et;
    }

    @Override
    public List<Etudiant> selectEtudiant() {
//        List<Etudiant> e = getEm().createQuery("select e from Etudiant e").getResultList();
        Query query = getEm().createNamedQuery("Etudiant.findAll");
        return query.getResultList();
    }

    @Override
    public Etudiant updateEtudiant(Etudiant et) {
        getEm().getTransaction().begin();
        et = getEm().merge(et);
        getEm().getTransaction().commit();
        return et;
    }

    @Override
    public void deleteEtudiant(Etudiant et) {
        getEm().getTransaction().begin();
        et = getEm().merge(et);
        getEm().remove(et);
        getEm().getTransaction().commit();
    }

    @Override
    public Promotion insertPromotion(Promotion prom) {
        getEm().getTransaction().begin();
        getEm().persist(prom);
        getEm().getTransaction().commit();
        return prom;
    }

    @Override
    public List<Promotion> selectPromotion() {
        List<Promotion> p = getEm().createQuery("select p from Promotion p").getResultList();
        return p;
    }

    @Override
    public Promotion updatePromotion(Promotion prom) {
        getEm().getTransaction().begin();
        prom = getEm().merge(prom);
        getEm().getTransaction().commit();
        return prom;
    }

    @Override
    public void deletePromotion(Promotion prom) {
        getEm().getTransaction().begin();
        prom = getEm().merge(prom);
        getEm().remove(prom);
        getEm().getTransaction().commit();
    }

    @Override
    public Session insertSession(Session sess) {
        getEm().getTransaction().begin();
        getEm().persist(sess);
        getEm().getTransaction().commit();
        return sess;
    }

    @Override
    public List<Session> selectSession() {
        List<Session> s = getEm().createQuery("select s from Session s").getResultList();
        return s;
    }

    @Override
    public Session updateSession(Session sess) {
        getEm().getTransaction().begin();
        sess = getEm().merge(sess);
        getEm().getTransaction().commit();
        return sess;
    }

    @Override
    public void deleteSession(Session sess) {
        getEm().getTransaction().begin();
        sess = getEm().merge(sess);
        getEm().remove(sess);
        getEm().getTransaction().commit();
    }

    @Override
    public void insertInscription(Long et, Long cl, Long prom, Long sess) {
        getEm().getTransaction().begin();

        Etudiant e = getEm().find(Etudiant.class, et);
        Classe c = getEm().find(Classe.class, cl);
        Promotion p = getEm().find(Promotion.class, prom);
        Session s = getEm().find(Session.class, sess);

//        e.getClasses().add(c);
//        e.getPromotions().add(p);
//        e.getSessions().add(s);

//        c.getEtudiants().add(e);
//        p.getEtudiants().add(e);
//        s.getEtudiants().add(e);

        getEm().getTransaction().commit();

    }

    @Override
    public List<SelectItem> listeClasse() {
        List<SelectItem> listeClasse = getEm().createQuery("select c from Classe c").getResultList();
        return listeClasse;
        }

}
