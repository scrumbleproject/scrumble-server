/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Userstory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author cyril
 */
@Stateless
@LocalBean
public class UserstoryFacade extends AbstractFacade<Userstory> implements UserstoryFacadeLocal {
    @PersistenceContext(unitName = "com.scrumble.server_scrumble-server-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserstoryFacade() {
        super(Userstory.class);
    }
    
    public List<Userstory> quickSearch(String pattern){
        List<Userstory> results;
        
        //run named queries
        TypedQuery<Userstory> queryExact = getEntityManager().createNamedQuery("Userstory.quickSearchExact", Userstory.class);
        TypedQuery<Userstory> querySimple = getEntityManager().createNamedQuery("Userstory.quickSearchSimple", Userstory.class);
        
        //concatenate results
        results = queryExact.setParameter("pattern", pattern).getResultList();
        results.addAll(querySimple.setParameter("pattern", "%"+pattern+"%").getResultList());
        
        //supprimer les doublons
        Set set = new HashSet();
        set.addAll(results);
        
        return new ArrayList<Userstory>(set);
    }
    
    public List<Userstory> findAllOrderByImportance() {
        return this.em.createNamedQuery("Userstory.findAllOrderByImportance").getResultList();
    }
    
}
