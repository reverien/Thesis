package ub.edu.bi.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class GestionnaireEntity {

    private static final String jpa_unite = "attributionPU";
    private EntityManager em;

    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }
}
