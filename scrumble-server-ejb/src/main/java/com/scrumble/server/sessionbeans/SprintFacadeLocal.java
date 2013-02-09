/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Sprint;
import com.scrumble.server.entities.Userstory;
import com.scrumble.server.entities.Userstorysprint;
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
     * Update the process status of the sprint
     * @param idSprint the id of a specified Sprint object
     * @param status the status to update
     */
    public void updateProcessStatusOfSprint(Integer idSprint, String codeStatus);

}
