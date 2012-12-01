/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Userstory;
import com.scrumble.server.entities.Userstorysprint;
import java.util.ArrayList;
import java.util.List;
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
public class UserstorysprintFacade extends AbstractFacade<Userstorysprint> implements UserstorysprintFacadeLocal {
    @PersistenceContext(unitName = "com.scrumble.server_scrumble-server-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserstorysprintFacade() {
        super(Userstorysprint.class);
    }
    
    public List<Userstory> findUserstoriesForSprint(Integer idSprint) throws Exception {
        List<Userstory> results;
        try {
            TypedQuery<Userstorysprint> query = getEntityManager().createNamedQuery("Userstorysprint.findByIdSprint", Userstorysprint.class);
            List<Userstorysprint> userstorysprints = query.setParameter("idSprint", idSprint).getResultList();
            results = new ArrayList<Userstory>();
            for (Userstorysprint userstorysprint : userstorysprints){
                Userstory userstory = userstorysprint.getUserstory();
                userstory.sortTasksByProcessStatusOrder(); //order tasks by status order
                results.add(userstory);
            }
        }
        catch(Exception e){
            throw e;
        }
        
        return results;
    }
    
}
