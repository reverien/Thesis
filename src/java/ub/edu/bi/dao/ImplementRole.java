package ub.edu.bi.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import ub.edu.bi.Role;

public class ImplementRole{
    
    private static final String jpa_unite = "attributionPU";
    private EntityManager em;
    
    public EntityManager getEm() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory(jpa_unite).createEntityManager();
        }
        return em;
    }
    
   
    public Role InsertRole(Role r) {
        getEm().getTransaction().begin();
        getEm().persist(r);
        getEm().getTransaction().commit();
        return r;
    }

  
    public void Actualiser(Role r) {
        getEm().getTransaction().begin();
        getEm().refresh(r);
        getEm().getTransaction().commit();
    }
    
   
    public List<Role> selectAllRole() {
        Query query = getEm().createNamedQuery("Role.findAll");
        return query.getResultList();
    }
    
   
    public Role UpdateRole(Role r) {
        getEm().getTransaction().begin();
        r = getEm().merge(r);
        getEm().getTransaction().commit();
        return r;
    }
    
   
    public void deleteRole(Role r) {
        getEm().getTransaction().begin();
        r = getEm().merge(r);
        getEm().remove(r);
        getEm().getTransaction().commit();
        
    }
    
}
