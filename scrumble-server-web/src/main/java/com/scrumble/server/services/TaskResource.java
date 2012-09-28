/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.services;


import com.scrumble.server.entities.Task;
import com.scrumble.server.sessionbeans.TaskFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Soukeyna
 */
@Path("task")
@Stateless
public class TaskResource {
    

    @Context
    private UriInfo context;
    
    @EJB
    private TaskFacadeLocal taskBean;
    /**
     * Creates a new instance of TaskResource
     */
    
    
    public TaskResource() {
    }

    /**
     * Retrieves representation of an instance of com.scrumble.server.services.TaskResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TaskResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    
    @GET
    @Path("all")
    @Produces("application/json")
    public List<Task> findAll() {
        return taskBean.findAll();
    }
    

    /**
     * Retrieves representation of a single com.scrumble.server.entities.Task object
     * @param id the id of the Member1 object to retrieve
     * @return a JSON representation of the related Member1 object.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Task getTask(@PathParam("id") String id) {
        return taskBean.find(Integer.parseInt(id));
    }

    /**
     * POST method for creating an instance of TaskResource object
     * @param task JSON representation for the Task object
     * @return an HTTP response with content of the created resource.
     */
    @POST
    @Path("add")
    @Consumes("application/json")
    @Produces("application/json")
    public void addTask(Task task) {
        taskBean.create(task);
    }
    
    /**
     * PUT method for updating an instance of TaskResource object
     * @param task JSON representation for the Task object
     * @return an HTTP response with content of the created resource.
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public void updateTask(@PathParam("id") String id) {
        System.out.println("PUT");
        Task task = taskBean.find(Integer.parseInt(id));
        if (task==null)System.out.println("task is NULL !!!!");
        taskBean.edit(task);
    }
}
