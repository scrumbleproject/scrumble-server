/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.services;

import com.scrumble.server.entities.Member1;
import com.scrumble.server.sessionbeans.Member1FacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author cyril
 */
@Path("members")
@Stateless
public class MembersResource {

    @Context
    private UriInfo context;
    
    @EJB
    private Member1FacadeLocal memberBean;

    /**
     * Creates a new instance of MembersResource
     */
    public MembersResource() {
    }
    
    
    /**
     * Retrieves representation of list of com.scrumble.server.entities.Member1 object
     * @return a JSON representation of the list of all members.
     */
    @GET
    @Path("all")
    @Produces("application/json")
    public List<Member1> findAll() {
        return memberBean.findAll();
    }
    
    /**
     * Retrieves representation of a single com.scrumble.server.entities.Member1 object
     * @param id the id of the Member1 object to retrieve
     * @return a JSON representation of the related Member1 object.
     */
    @GET
    @Path("search/{pattern}")
    @Produces("application/json")
    public List<Member1> searchMembersQuick(@PathParam("pattern") String pattern) {
        return memberBean.quickSearch(pattern);
    }
    
    /**
     * Retrieves representation of a single com.scrumble.server.entities.Member1 object
     * @param id the id of the Member1 object to retrieve
     * @return a JSON representation of the related Member1 object.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Member1 getMember(@PathParam("id") String id) {
        return memberBean.find(Integer.parseInt(id));
    }
    
    
    /**
     * POST method for creating an instance of Member1 object
     * @param member JSON representation for the Member1 object
     * @param idRole the id of the Role object to add
     * @return an HTTP response with content of the created resource.
     */
    @POST
    @Path("add/{idRole}")
    @Consumes("application/json")
    @Produces("application/json")
    public void addMember(Member1 member,@PathParam("idRole") String idRole) {
        memberBean.addRoleToMember(member, Integer.parseInt(idRole));
    }
    
    /**
     * PUT method for updating an instance of Member1 object
     * @param member JSON representation for the Member1 object
     * @param idRole the id of the Role object to update
     * @return an HTTP response with content of the created resource.
     */
    @PUT
    @Path("{idRole}")
    @Consumes("application/json")
    @Produces("application/json")
    public void updateMember(Member1 member,@PathParam("idRole") String idRole) {
        memberBean.updateRoleToMember(member, Integer.parseInt(idRole));
    }
    
    
    /**
     * Removes a single com.scrumble.server.entities.Member1 object
     * @param id the id of the Member1 object to remove
     * @return nothing.
     */
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public void removeMember(@PathParam("id") String id) {
        if(memberBean.find(Integer.parseInt(id))!=null)
            memberBean.remove(memberBean.find(Integer.parseInt(id)));
    }
    
    
    /**
     * GET method to retrieve display name for a specified user login
     * @param login the login of the user
     * @return the display name as string.
     */
    @GET
    @Path("{login}/display-name")
    @Produces("text/plain")
    public String displayNameForLogin(@PathParam("login") String login) {
        return memberBean.getDisplayNameForLogin(login);
    }
    
}
