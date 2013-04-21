package com.scrumble.server.services;

import com.scrumble.server.entities.Project;
import com.scrumble.server.entities.Task;
import com.scrumble.server.entities.Userstory;
import com.scrumble.server.sessionbeans.TaskFacadeLocal;
import com.scrumble.server.sessionbeans.UserstoryFacadeLocal;
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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ArNo
 */
@Path("userstories")
@Stateless
public class UserStoriesResource {

    @Context
    private UriInfo context;
    
    @EJB
    private UserstoryFacadeLocal userStoryBean;
    
    @EJB
    private TaskFacadeLocal taskBean;

    /**
     * Creates a new instance of UserStoriesResource
     */
    public UserStoriesResource() {
    }

    /**
     * Retrieves representation of list of com.scrumble.server.entities.Userstory object
     * @return a JSON representation of the list of all userstories.
     */
    @GET
    @Path("all")
    @Produces("application/json")
    public List<Userstory> findAll() {
        return userStoryBean.findAllOrderByImportance();
    }
    
    
    /**
     * Retrieves representation of a single com.scrumble.server.entities.Userstory object
     * @param id the id of the Userstory object to retrieve
     * @return a JSON representation of the related Userstory object.
     */
    @GET
    @Path("search/{pattern}")
    @Produces("application/json")
    public List<Userstory> searchUserStoriesQuick(@PathParam("pattern") String pattern) {
        return userStoryBean.quickSearch(pattern);
    }
    
    
    /**
     * Retrieves representation of a single com.scrumble.server.entities.Userstory object
     * @param id the id of the Userstory object to retrieve
     * @return a JSON representation of the related Userstory object.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Userstory getUserStory(@PathParam("id") String id) {
        return userStoryBean.find(Integer.parseInt(id));
    }
    
    
    /**
     * POST method for creating an instance of Userstory object
     * @param userstory JSON representation for the Userstory object
     * @param idProject the id of the Project object related to the Userstory object
     * @return an HTTP response with content of the created resource.
     */
    @POST
    @Path("add/{idProject}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addUserStory(Userstory userstory,@PathParam("idProject") String idProject) {
        System.out.println(userstory.toString());
        userStoryBean.create(userstory);
        userStoryBean.add_updateUserstoryToProject(userstory, Integer.parseInt(idProject));
        
        Response reponse=Response.status(200).build();
        return reponse;
    }
    
    
    /**
     * PUT method for updating an instance of Userstory object
     * @param userstory JSON representation for the Userstory object
     * @param idProject the id of the Project object related to the Userstory object
     * @return an HTTP response with content of the created resource.
     */
    @PUT
    @Path("{idProject}")
    @Consumes("application/json")
    @Produces("application/json")
    public void updateUserStory(Userstory userstory,@PathParam("idProject") String idProject) {
        userStoryBean.edit(userstory);
        userStoryBean.updateUserstoryTaskCollection(userstory);
        userStoryBean.add_updateUserstoryToProject(userstory, Integer.parseInt(idProject));
    }
    
    
    /**
     * Removes a single com.scrumble.server.entities.Userstory object
     * @param id the id of the Userstory object to remove
     * @return nothing.
     */
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public Response removeUserStory(@PathParam("id") String id) {
        if(userStoryBean.find(Integer.parseInt(id))!=null)
            userStoryBean.remove(userStoryBean.find(Integer.parseInt(id)));
        
        Response reponse=Response.status(200).build();
        return reponse;
    }
    
    
    /**
     * POST method for creating an instance of Userstory object
     * @param userstory JSON representation for the Userstory object
     * @return an HTTP response with content of the created resource.
     */
    @POST
    @Path("{id}/{position}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updatePriority(@PathParam("id") String id, @PathParam("position") String position) {
        userStoryBean.updateImportance(Integer.parseInt(id), Integer.parseInt(position));
        Response reponse=Response.status(200).build();
        return reponse;
    }
    
    /**
     * Retrieves the list of a com.scrumble.server.entities.Userstory linked with the Project object
     * @param idProject the id of the Project object to retrieve
     * @return a JSON representation of the list of all userstories.
     */
    @GET
    @Path("{idProject}/projects")
    @Produces("application/json")
    public List<Userstory> findAllProjectUserstories(@PathParam("idProject") String idProject) {
        List<Userstory> results = null;
        try {
            results = userStoryBean.findAllProjectUserstories(Integer.parseInt(idProject));
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return results;
    }
    
    
    
    
    

    
    
    
    /**
     * Retrieves the list of a com.scrumble.server.entities.Tasks linked with the Userstory object
     * @param idUserstory the id of the Userstory object to retrieve
     * @return a JSON representation of the Task object
     */
    @GET
    @Path("{idUserstory}/tasks/all")
    @Produces("application/json")
    public List<Task> findAllTaskUserstories(@PathParam("idUserstory") String idUserstory) {
        return taskBean.findAllTaskUserstories(Integer.parseInt(idUserstory));
    }
    
    
    /**
     * Retrieves representation of a single com.scrumble.server.entities.Task object
     * @param id the id of the Task object to retrieve
     * @return a JSON representation of the related Task object.
     */
    @GET
    @Path("{idUserstory}/tasks/{idTask}")
    @Produces("application/json")
    public Task getTask(@PathParam("idUserstory") String idUserstory, @PathParam("idTask") String idTask) {
        return taskBean.find(Integer.parseInt(idTask));
    }
    
    
    /**
     * POST method for creating an instance of Task object
     * @param task JSON representation for the Task object
     * @return an HTTP response with content of the created resource.
     */
    @POST
    @Path("{idUserstory}/tasks/add")
    @Consumes("application/json")
    @Produces("application/json")
    public void addTask(@PathParam("idUserstory") String idUserstory, Task task) {
        Userstory userstory = userStoryBean.find(Integer.parseInt(idUserstory));
        task.setIdUserstory(userstory);
        taskBean.create(taskBean.useDefaultProcessStatusIfNeededForTask(task));
        userstory.getTaskCollection().add(task);
        
        try
        {
            this.userStoryBean.updateEstimation(task.getIdUserstory().getIdUserstory());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    
    /**
     * PUT method for updating an instance of Task object
     * @param task JSON representation for the Task object
     * @return an HTTP response with content of the created resource.
     */
    @PUT
    @Path("{idUserstory}/tasks")
    @Consumes("application/json")
    @Produces("application/json")
    public void updateTask(@PathParam("idUserstory") String idUserstory, Task task) {
        task.setIdUserstory(userStoryBean.find(Integer.parseInt(idUserstory)));
        taskBean.edit(taskBean.useDefaultProcessStatusIfNeededForTask(task));
        
        try
        {
            this.userStoryBean.updateEstimation(task.getIdUserstory().getIdUserstory());
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    
    /**
     * Removes a single com.scrumble.server.entities.Task object
     * @param id the id of the Task object to remove
     * @return nothing.
     */
    @DELETE
    @Path("{idUserstory}/tasks/{idTask}")
    @Produces("application/json")
    public void removeTask(@PathParam("idUserstory") String idUserstory, @PathParam("idTask") String idTask) {
        if(taskBean.find(Integer.parseInt(idTask))!=null)
        {
            taskBean.remove(taskBean.find(Integer.parseInt(idTask)));
            
            try
            {
                userStoryBean.updateEstimation(Integer.parseInt(idUserstory));
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    

    /**
     * Retrieves representation of a single com.scrumble.server.entities.Task object
     * @param id the id of the Task object to retrieve
     * @return a JSON representation of the related Task object.
     */
    @GET
    @Path("{idUserstory}/tasks/search/{pattern}")
    @Produces("application/json")
    public List<Task> searchTasksQuick(@PathParam("idUserstory") String idUserstory, @PathParam("pattern") String pattern)
    {
        return taskBean.quickSearch(pattern);
    }
    
    /**
     * Check whether the userstory is editable or not in relation with 
     * its assignation to a sprint and the status of the sprint
     * @param idUserstory the id of the userstory to check
     * @return a boolean.
     */
    @GET
    @Path("{idUserstory}/iseditable")
    @Produces("text/plain")
    public String isUserstoryEditable(@PathParam("idUserstory") String idUserstory) {
        if (userStoryBean.isUserstoryEditable(Integer.parseInt(idUserstory))) {
           return "true"; 
        }
        return "false";
    }

}
