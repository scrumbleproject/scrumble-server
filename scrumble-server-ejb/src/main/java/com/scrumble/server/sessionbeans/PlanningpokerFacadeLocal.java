/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Planningpoker;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cyril
 */
@Local
public interface PlanningpokerFacadeLocal {

    void create(Planningpoker planningpoker);

    void edit(Planningpoker planningpoker);

    void remove(Planningpoker planningpoker);

    Planningpoker find(Object id);

    List<Planningpoker> findAll();

    List<Planningpoker> findRange(int[] range);

    int count();
    
}
