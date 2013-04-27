/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Processstatus;
import com.scrumble.server.entities.Sprint;
import com.scrumble.server.entities.Userstory;
import com.scrumble.server.entities.Userstorysprint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cyril
 */
@Local
public interface SprintFacadeLocal {

    void create(Sprint sprint);

    void edit(Sprint sprint);

    void remove(Sprint sprint);

    Sprint find(Object id);

    List<Sprint> findAll();

    List<Sprint> findRange(int[] range);

    int count();
    
    /**
     * Get the list of all Userstory objects of a specified Sprint object
     * @param idSprint the id of a specified Sprint object
     * @return a list of all userstories of the sprint 
     */
    public List<Userstory> findAllSprintUserstories(Integer idSprint) throws Exception;
    
    /**
     * Get the list of all Sprint objects of a specified Project object
     * @param idProject the id of a specified Project object
     * @return a list of all sprints of the project 
     */
    public List<Sprint> findAllProjectSprints(Integer idProject) throws Exception;
    
    
    /**
    * Add or update an sprint related to a project
    * @param sprint the sprint which will be added or updated
    * @param idProject the id of a specified Project object
    */
    public void add_updateSprintToProject(Sprint sprint, Integer idProject);
    
    
    /**
     * Get the list of all Userstory objects that are NOT linked with a  specified Sprint object
     * @param idSprint the id of a specified Sprint object
     * @return a list of userstories
     */
    public List<Userstory> findAllNotSprintUserstories(Integer idSprint) throws Exception;
    
    
    /**
     * Save the list of Userstories, related to a sprint
     * @param idSprint the id of a specified Sprint object
     * @return void
     */
    public void addListUserstoriesToSprint(List<Integer> array, Integer idSprint) throws Exception;
    
    
    /**
     * Retrieves informations to display the sprint burndown chart
     * @param idSprint the id of the Sprint object
     * @return the list of informations needed to display a Sprint Burndown Chart
     */
    public String findSprintBurndownChartInformations(Integer idSprint) throws Exception;
    
    
    /**
     * Retrieves process status of the specified sprint
     * @param idSprint the id of the Sprint object
     * @return the process status object
     */
    public Processstatus getProcessStatusOfSprint(Integer idSprint) throws Exception;
    
    
    /**
     * Update the process status of the sprint
     * @param idSprint the id of a specified Sprint object
     * @param status the status to update
     */
    public void updateProcessStatusOfSprint(Integer idSprint, String codeStatus);
    
    
    /**
     * Retrieves the number of userstories assigned to a sprint
     * @param idSprint the id of the sprint
     * @return a number of userstories
     */
    public int findUserstoryNumberOfSprint(Integer idSprint) throws Exception;
    
    
    /**
     * Retrieves the progression of a sprint
     * @param idSprint the id of the sprint
     * @return the progression in percent
     */
    public float findProgressionOfSprint(Integer idSprint) throws Exception;

    
    /**
     * Check whether userstories can be added to this sprint or not
     * @param idSprint the id of the sprint to check
     * @return a boolean.
     */
    public boolean isSprintEditable(Integer idSprint);
    
    
    /**
     * Retrieves sprint velocity value
     * @param idSprint the id of the sprint to check
     * @return an Integer.
     */
    public Integer getVelocityOfSprint(Integer idSprint);
    
    
    /**
     * Retrieves the running sprint of a project
     * @param idProject the id of the Project object
     * @return a JSON representation of the related Sprint object.
     */
    public Sprint getRunningSprint(Integer idProject);

    /**
     * Retrieves remaining sprint velocity value
     * @param idSprint the id of the sprint
     * @return an Integer.
     */
    public Integer getRemainingVelocityOfSprint(Integer idSprint);
    
}
