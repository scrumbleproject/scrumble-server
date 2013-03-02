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
public interface UserstorysprintFacadeLocal {

    void create(Userstorysprint userstorysprint);

    void edit(Userstorysprint userstorysprint);

    void remove(Userstorysprint userstorysprint);

    Userstorysprint find(Object id);

    List<Userstorysprint> findAll();

    List<Userstorysprint> findRange(int[] range);

    int count();
    
    public List<Userstory> findUserstoriesForSprint(Integer idSprint) throws Exception;
    
    public Userstorysprint findByIdSprintAndIdUserstory(Integer idSprint, Integer idStory) throws Exception;
    
    public List<Sprint> findSprintAssignationForUserstory(Integer idStory);
}
