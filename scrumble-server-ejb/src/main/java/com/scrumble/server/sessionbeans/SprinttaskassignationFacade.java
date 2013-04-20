/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Sprint;
import com.scrumble.server.entities.Sprinttaskassignation;
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
public class SprinttaskassignationFacade extends AbstractFacade<Sprinttaskassignation> implements SprinttaskassignationFacadeLocal {
    @PersistenceContext(unitName = "com.scrumble.server_scrumble-server-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SprinttaskassignationFacade() {
        super(Sprinttaskassignation.class);
    }
    
    public List<Sprinttaskassignation> findSprinttaskassignationByIdSprint(Integer idSprint) throws Exception {
        TypedQuery<Sprinttaskassignation> query = getEntityManager().createNamedQuery("Sprinttaskassignation.findByIdSprint", Sprinttaskassignation.class);
        return query.setParameter("idSprint", idSprint).getResultList();
    }
    
}
