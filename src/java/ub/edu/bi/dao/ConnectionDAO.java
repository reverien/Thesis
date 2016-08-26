package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Connection;

public class ConnectionDAO {
     private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }
    
    public List<Connection> selectByLoginAndPassword(String l, String p){
        Query query = getEm().createNamedQuery("Connection.findByLoginAndPassword");
        query.setParameter("login", l);
        query.setParameter("password", p);
        List<Connection> liste = query.getResultList();
        return liste;
    }
    
}
