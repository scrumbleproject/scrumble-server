/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.services;


import com.scrumble.server.entities.Member1;
import com.scrumble.server.entities.Project;
import com.scrumble.server.entities.Sprint;
import com.scrumble.server.entities.Sprinttaskassignation;
import com.scrumble.server.entities.Task;
import com.scrumble.server.sessionbeans.ProjectFacadeLocal;
import com.scrumble.server.sessionbeans.SprintFacadeLocal;
import com.scrumble.server.sessionbeans.SprinttaskassignationFacade;
import com.scrumble.server.sessionbeans.SprinttaskassignationFacadeLocal;
import com.scrumble.server.sessionbeans.TaskFacadeLocal;
import com.scrumble.server.sessionbeans.UserstoryFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("tasks")
@Stateless
public class TasksResource {
    
    @Context
    private UriInfo context;
    
    @EJB
    private TaskFacadeLocal taskBean;
    
    @EJB
    private UserstoryFacadeLocal userstoryBean;
    
    @EJB
    private ProjectFacadeLocal projectBean;
    
    @EJB
    private SprinttaskassignationFacadeLocal sprinttaskassignationBean;
    
    @EJB
    private SprintFacadeLocal sprintBean;
    
    
    
    /**
     * Creates a new instance of TasksResource
     */
    public TasksResource()
    {
    }

    
    @GET
    @Path("all")
    @Produces("application/json")
    public List<Task> findAll()
    {
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
    public List<Task> searchTasksQuick(@PathParam("pattern") String pattern)
    {
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
    public Task getTask(@PathParam("id") String id)
    {
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
    public void addTask(Task task)
    {
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
    public void updateTask(Task task)
    {
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
    public void removeTask(@PathParam("id") String id)
    {
        if(taskBean.find(Integer.parseInt(id))!=null)
        {
            taskBean.remove(taskBean.find(Integer.parseInt(id)));
        }
    }
    
    
    /**
     * Retrieves the list of a com.scrumble.server.entities.Tasks linked with the Userstory object
     * @param id the id of the Userstory object to retrieve
     * @return a JSON representation of the related Userstory object.
     */
    @GET
    @Path("{id}/userstories")
    @Produces("application/json")
    public List<Task> findAllTaskUserstories(@PathParam("id") String id)
    {
        return taskBean.findAllTaskUserstories(Integer.parseInt(id));
    }
    
    
    /**
     * POST method for updating a processStatus of Task object
     * @param id id of task
     * @param status status of task
     * @return an HTTP response with content of the created resource.
     */
    @POST
    @Path("{id}/{status}")
    @Consumes("application/json")
    @Produces("application/json")
    public void updateProcessStatusOfTask(@PathParam("id") String id, @PathParam("status") String status)
    {
        taskBean.updateProcessStatusOfTask(Integer.parseInt(id), status);
    }
    
    
    /**
     * POST assign a member to a Task object if not already assigned
     * @param id id of task
     * @param login login of member to assign to this task
     * @return an HTTP response with content of the created resource.
     */
    @POST
    @Path("{idSprint}/{idTask}/{login}/add")
    @Produces("application/json")
    public void addAssignedMemberForTask(@PathParam("idSprint") String idSprint,
                                        @PathParam("idTask") String idTask,
                                        @PathParam("login") String login)
    {
        try
        {
            taskBean.addAssignedMemberForTask(Integer.parseInt(idSprint), Integer.parseInt(idTask), login);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    
    /**
     * POST assign a member to a Task object if not already assigned
     * @param id id of task
     * @param login login of member to assign to this task
     * @return an HTTP response with content of the created resource.
     */
    @POST
    @Path("{idSprint}/{idTask}/{login}/remove")
    @Produces("application/json")
    public void removeAssignedMemberForTask(@PathParam("idSprint") String idSprint,
                                        @PathParam("idTask") String idTask,
                                        @PathParam("login") String login)
    {
        try
        {
            taskBean.removeAssignedMemberForTask(Integer.parseInt(idSprint), Integer.parseInt(idTask), login);
        } 
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    @GET
    @Path("{idSprint}/{idTask}/members")
    @Produces("application/json")
    public List<Member1> getAssignedMemberForTask (@PathParam("idSprint") String idSprint,
                                        @PathParam("idTask") String idTask)
    {
        List<Member1> assignedMembers = null;
        try
        {
            assignedMembers = taskBean.getAssignedMemberForTask(Integer.parseInt(idSprint), Integer.parseInt(idTask));
        }
        catch(Exception e)
        {
            throw new RESTException(e.getMessage());
        }
        return assignedMembers;
    }
    
    
    /**
     * Retrieves the running tasks of a sprint
     * @param idSprint the id of the Sprint object
     * @return a list of Tasks
     */
    @GET
    @Path("{idSprint}/runningtasks")
    @Produces("application/json")
    public List<Sprinttaskassignation> getRunningTasks(@PathParam("idSprint") String idSprint)
    {
        try
        {
            return sprinttaskassignationBean.findRunningTaskByIdSprint(Integer.parseInt(idSprint));
        } 
        catch(Exception ex)
        {
            Logger.getLogger(TasksResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    /**
     * Retrieves the running tasks of a sprint
     * @param idSprint the id of the Sprint object
     * @return a list of Tasks
     */
    @GET
    @Path("{login}/tasksforuser")
    @Produces("application/json")
    public List<Sprinttaskassignation> getRunningTasksForUser(@PathParam("login") String login)
    {
        try
        {
            //Get the list of projects
            List<Project> l = projectBean.findProjectByUser(login);
            //System.out.println(l);
            List<Sprinttaskassignation> tasklist = new ArrayList<Sprinttaskassignation>();
            int i = 0;
            Sprint s = null;
            
            while(i<l.size())
            {
                s = sprintBean.getRunningSprint(l.get(i).getIdProject());
                /*System.out.println("Compteur:"+i+",Projet:"+l.get(i).getIdProject());
                System.out.println(s);*/
                if(s != null)
                {
                    //System.out.println("NOT NULL");
                    tasklist.addAll(sprinttaskassignationBean.findRunningTaskByIdSprint(s.getIdSprint()));
                }
                /*else
                    System.out.println("NULL");*/
                
                i++;
            }
            
            return tasklist;
        }
        catch(Exception ex)
        {
            Logger.getLogger(TasksResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}