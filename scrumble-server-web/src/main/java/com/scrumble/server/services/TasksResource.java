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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Soukeyna
 */
@Path("tasks")
@Stateless
public class TasksResource {
    

    @Context
    private UriInfo context;
    
    @EJB
    private TaskFacadeLocal taskBean;
    /**
     * Creates a new instance of TasksResource
     */
    
    
    public TasksResource() {
    }

    
    
    @GET
    @Path("all")
    @Produces("application/json")
    public List<Task> findAll() {
        return taskBean.findAll();
    }
    
    /**
     * Retrieves representation of a single com.scrumble.server.entities.Task object
     * @param id the id of the Task object to retrieve
     * @return a JSON representation of the related Task object.
     */
    @GET
    @Path("search/{pattern}")
    @Produces("application/json")
    public List<Task> searchTasksQuick(@PathParam("pattern") String pattern) {
        System.out.println("LOGS");
        return taskBean.quickSearch(pattern);
    }

    /**
     * Retrieves representation of a single com.scrumble.server.entities.Task object
     * @param id the id of the Task object to retrieve
     * @return a JSON representation of the related Task object.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Task getTask(@PathParam("id") String id) {
        return taskBean.find(Integer.parseInt(id));
    }

    /**
     * POST method for creating an instance of Task object
     * @param task JSON representation for the Task object
     * @return an HTTP response with content of the created resource.
     */
    @POST
    @Path("add")
    @Consumes("application/json")
    @Produces("application/json")
    public void addTask(Task task) {
        taskBean.create(taskBean.useDefaultProcessStatusIfNeededForTask(task));
    }
    
    /**
     * PUT method for updating an instance of Task object
     * @param task JSON representation for the Task object
     * @return an HTTP response with content of the created resource.
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public void updateTask(Task task) {
        taskBean.edit(taskBean.useDefaultProcessStatusIfNeededForTask(task));
    }
    
    /**
     * Removes a single com.scrumble.server.entities.Task object
     * @param id the id of the Task object to remove
     * @return nothing.
     */
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public void removeTask(@PathParam("id") String id) {
        if(taskBean.find(Integer.parseInt(id))!=null)
            taskBean.remove(taskBean.find(Integer.parseInt(id)));
    }
    
    /**
     * Retrieves the list of a com.scrumble.server.entities.Tasks linked with the Userstory object
     * @param id the id of the Userstory object to retrieve
     * @return a JSON representation of the related Userstory object.
     */
    @GET
    @Path("{id}/userstories")
    @Produces("application/json")
    public List<Task> findAllTaskUserstories(@PathParam("id") String id) {
        return taskBean.findAllTaskUserstories(Integer.parseInt(id));
    }
    
    /**
     * PUT method for updating a processStatus of Task object
     * @param task JSON representation for the Task object
     * @return an HTTP response with content of the created resource.
     */
    @POST
    @Path("{id}/{status}")
    @Consumes("application/json")
    @Produces("application/json")
    public void updateProcessStatusOfTask(@PathParam("id") String id, @PathParam("status") String status) {
        taskBean.updateProcessStatusOfTask(Integer.parseInt(id), status);
    }
    
}
