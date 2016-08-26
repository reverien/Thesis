package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.ProgrammationHoraire;

public class ProgrammationHoraireDAO {
    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }
    
    public ProgrammationHoraire insertHoraire(ProgrammationHoraire p){
        getEm().getTransaction().begin();
        getEm().persist(p);
        getEm().getTransaction().commit();
        return p;
    }
    
    public List<ProgrammationHoraire> selectAllHoraire(){
        Query query = getEm().createNamedQuery("ProgrammationHoraire.findAll");
        List<ProgrammationHoraire> liste = query.getResultList();
        return liste;
    }
    
}
