/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Processstatus;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cyril
 */
@Local
public interface ProcessstatusFacadeLocal {

    void create(Processstatus processstatus);

    void edit(Processstatus processstatus);

    void remove(Processstatus processstatus);

    Processstatus find(Object id);

    List<Processstatus> findAll();

    List<Processstatus> findRange(int[] range);

    int count();
    
}
