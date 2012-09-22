/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Userstory;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cyril
 */
@Local
public interface UserstoryFacadeLocal {

    void create(Userstory userstory);

    void edit(Userstory userstory);

    void remove(Userstory userstory);

    Userstory find(Object id);

    List<Userstory> findAll();

    List<Userstory> findRange(int[] range);

    int count();
    
}
