/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Sprint;
import com.scrumble.server.entities.Sprinttaskassignation;
import com.scrumble.server.entities.Task;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cyril
 */
@Local
public interface SprinttaskassignationFacadeLocal {

    void create(Sprinttaskassignation sprinttaskassignation);

    void edit(Sprinttaskassignation sprinttaskassignation);

    void remove(Sprinttaskassignation sprinttaskassignation);

    Sprinttaskassignation find(Object id);

    List<Sprinttaskassignation> findAll();

    List<Sprinttaskassignation> findRange(int[] range);

    int count();
    
    /**
     * Get the list of all Sprinttaskassignation objects that are linked with a specified Sprint object
     * @param idSprint the id of a specified Sprint object
     * @return a list of Sprinttaskassignation
     */
    public List<Sprinttaskassignation> findSprinttaskassignationByIdSprint(Integer idSprint) throws Exception;

    /**
     * Get the list of all Task objects that are linked with a specified Sprint object
     * @param idSprint the id of a specified Sprint object
     * @return a list of Task
     */
    public List<Sprinttaskassignation> findRunningTaskByIdSprint(Integer idSprint) throws Exception;

}
