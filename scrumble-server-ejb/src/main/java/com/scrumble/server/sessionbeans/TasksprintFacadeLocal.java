/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Tasksprint;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cyril
 */
@Local
public interface TasksprintFacadeLocal {

    void create(Tasksprint tasksprint);

    void edit(Tasksprint tasksprint);

    void remove(Tasksprint tasksprint);

    Tasksprint find(Object id);

    List<Tasksprint> findAll();

    List<Tasksprint> findRange(int[] range);

    int count();
    
}
