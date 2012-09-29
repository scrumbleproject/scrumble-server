/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Project;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cyril
 */
@Local
public interface ProjectFacadeLocal {

    void create(Project project);

    void edit(Project project);

    void remove(Project project);

    Project find(Object id);

    List<Project> findAll();

    List<Project> findRange(int[] range);

    int count();
    
    /** custom methods **/
    
    /**
     * QUICK SEARCH : search members within all Project object attributes
     * @param pattern a String to compare with any attributes content
     * @return a list with all matching Project object 
     */
    public List<Project> quickSearch(String pattern);
    
}
