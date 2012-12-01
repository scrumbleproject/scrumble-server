/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.services;

import com.scrumble.server.entities.Sprint;
import com.scrumble.server.entities.Userstory;
import com.scrumble.server.sessionbeans.SprintFacadeLocal;
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
@Path("sprints")
@Stateless
public class SprintsResource {

    @Context
    private UriInfo context;
    
    @EJB
    private SprintFacadeLocal sprintBean;

    /**
     * Creates a new instance of SprintsResource
     */
    public SprintsResource() {
    }
    
    /**
     * Retrieves representation of list of com.scrumble.server.entities.Sprint object
     * @return a JSON representation of the list of all sprint.
     */
    @GET
    @Path("all")
    @Produces("application/json")
    public List<Sprint> findAll() {
        return sprintBean.findAll();
    }
    
    /**
     * Retrieves representation of a single com.scrumble.server.entities.Sprint object
     * @param id the id of the Sprint object to retrieve
     * @return a JSON representation of the related Sprint object.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Sprint getProject(@PathParam("id") String id) {
        return sprintBean.find(Integer.parseInt(id));
    }
    
    /**
     * POST method for creating an instance of Sprint object
     * @param sprint JSON representation for the Sprint object
     * @return an HTTP response with content of the created resource.
     */
    @POST
    @Path("add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addSprint(Sprint sprint) {
        sprintBean.create(sprint);
        
        Response reponse=Response.status(200).build();
        return reponse;
    }
    
    /**
     * PUT method for updating an instance of Sprint object
     * @param sprint JSON representation for the Sprint object
     * @return an HTTP response with content of the created resource.
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public void updateSprint(Sprint sprint) {
        sprintBean.edit(sprint);
    }
    
    /**
     * Removes a single com.scrumble.server.entities.Sprint object
     * @param id the id of the Sprint object to remove
     * @return nothing.
     */
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public Response removeSprint(@PathParam("id") String id) {
        if(sprintBean.find(Integer.parseInt(id))!=null)
            sprintBean.remove(sprintBean.find(Integer.parseInt(id)));
        
        Response reponse=Response.status(200).build();
        return reponse;
    }

    
    /**
     * Retrieves the list of a com.scrumble.server.entities.Userstory linked with the Sprint object
     * @param idSprint the id of the Sprint object to retrieve
     * @return a JSON representation of the list of all userstories.
     */
    @GET
    @Path("{idSprint}/userstories")
    @Produces("application/json")
    public List<Userstory> findAllSprintUserstories(@PathParam("idSprint") String idSprint) {
        List<Userstory> results = null;
        try {
            results = sprintBean.findAllSprintUserstories(Integer.parseInt(idSprint));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return results;
    }
    
}
