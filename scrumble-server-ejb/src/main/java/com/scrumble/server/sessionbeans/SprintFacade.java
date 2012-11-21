/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Sprint;
import com.scrumble.server.entities.Userstory;
import com.scrumble.server.entities.Userstorysprint;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author cyril
 */
@Stateless
@LocalBean
public class SprintFacade extends AbstractFacade<Sprint> implements SprintFacadeLocal {
    @PersistenceContext(unitName = "com.scrumble.server_scrumble-server-ejb_ejb_1.0PU")
    private EntityManager em;

    @EJB 
    private UserstorysprintFacadeLocal userstorysprintBean;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SprintFacade() {
        super(Sprint.class);
    }
    
    /*public List<Userstory> findAllSprintUserstories(Integer idSprint){
        Sprint sprint = em.find(Sprint.class, idSprint);  
        ArrayList<Userstory> results = new ArrayList<Userstory>(); 
        for (Userstorysprint uss : sprint.getUserstorysprintCollection()) {
            results.add(uss.getUserstory());
        }
        return results;
    }*/
    
    public List<Userstory> findAllSprintUserstories(Integer idSprint) throws Exception{
        List<Userstory> results;
        try {
            results = userstorysprintBean.findUserstoriesForSprint(idSprint);
        }
        catch(Exception e){
            throw e;
        }
        return results;
    }
    
}
