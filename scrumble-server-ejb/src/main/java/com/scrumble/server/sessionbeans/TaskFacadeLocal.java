/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Member1;
import com.scrumble.server.entities.Task;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cyril
 */
@Local
public interface TaskFacadeLocal {

    void create(Task task);

    void edit(Task task);

    void remove(Task task);

    Task find(Object id);

    List<Task> findAll();

    List<Task> findRange(int[] range);

    int count();

    public List<Task> quickSearch(String pattern);
    
    /**
     * Get the list of all Task objects of a specified Userstory object
     * @param idUserstory the id of a specified Userstory object
     * @return a list of all tasks of the project 
     */
    public List<Task> findAllTaskUserstories(Integer idUserstory);
    
    /**
     * Update a processStatus of Task object
     * @param idTask the id of a specified Task object
     * @param codeStatus the status code relating to processStatus object
     */
    public void updateProcessStatusOfTask(Integer idTask, String codeStatus);
    
    /**
     * Check if a task has already a processStatus, and use default one otherwise
     * @param task a specified Task object
     * @return the task with the default processStatus 
     */
    public Task useDefaultProcessStatusIfNeededForTask(Task task);
    
    /**
     * Assign a member to a Task object if not already assigned
     * @param idSprint id of sprint
     * @param idTask id of task
     * @param login login of member to assign to this task
     * @return an HTTP response with content of the created resource.
     */
    public void addAssignedMemberForTask(Integer idSprint, Integer idTask, String login) throws Exception;
    
    /**
     * Retrieve members that are assigned to this Task object
     * @param idSprint id of sprint
     * @param idTask id of task
     * @return a list of Member1 object
     */
    public List<Member1> getAssignedMemberForTask(Integer idSprint, Integer idTask) throws Exception;
    
}
