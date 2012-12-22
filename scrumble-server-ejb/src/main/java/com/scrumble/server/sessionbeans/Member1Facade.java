/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Member1;
import com.scrumble.server.utils.ScrumbleUtils;
import com.scrumble.server.entities.Role;
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
public class Member1Facade extends AbstractFacade<Member1> implements Member1FacadeLocal {
    @PersistenceContext(unitName = "com.scrumble.server_scrumble-server-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Member1Facade() {
        super(Member1.class);
    }
    
    
    public List<Member1> quickSearch(String pattern){
        List<Member1> results;
        
        //run named queries
        TypedQuery<Member1> queryExact = getEntityManager().createNamedQuery("Member1.quickSearchExact", Member1.class);
        TypedQuery<Member1> querySimple = getEntityManager().createNamedQuery("Member1.quickSearchSimple", Member1.class);
        
        //concatenate results
        results = queryExact.setParameter("pattern", pattern).getResultList();
        results.addAll(querySimple.setParameter("pattern", "%"+pattern+"%").getResultList());
        
        //supprimer les doublons
        Set set = new HashSet();
        set.addAll(results);
        
        return new ArrayList<Member1>(set);
    }
    
    
    public boolean checkLoginAndPassword(String login, String password) throws Exception{
        
        TypedQuery<Member1> query = getEntityManager().createNamedQuery("Member1.findByLogin", Member1.class);
        List<Member1> results = query.setParameter("login", login).getResultList();
        if (results.size()>0){
            String hashedPassword = results.get(0).getPassword(); //login should be unique
            
            //encrypte passed password
            String hashedPasswordToCheck = ScrumbleUtils.encryptStringWithAlgorithm(password, "SHA1");
            
            //compare passwords
            if (hashedPassword.equalsIgnoreCase(hashedPasswordToCheck)){
                return true;
            }
            
        }        
        return false;
    }
    
    
    public void add_updateRoleToMember(Member1 member, Integer idRole){
        member.setIdRole(this.em.find(Role.class, idRole));
        this.edit(member);
    }
    
    public String getDisplayNameForLogin(String login){
        TypedQuery<Member1> query = getEntityManager().createNamedQuery("Member1.findByLogin", Member1.class);
        List<Member1> results = query.setParameter("login", login).getResultList();
        if (results.size()>0){
            Member1 result = results.get(0);
            return ScrumbleUtils.capitalize(result.getFirstname())+" "+ScrumbleUtils.capitalize(result.getLastname());
        }
        return "Not Found";
    }
    
}
