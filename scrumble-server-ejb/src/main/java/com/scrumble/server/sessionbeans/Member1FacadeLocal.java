/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Member1;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cyril
 */
@Local
public interface Member1FacadeLocal {

    void create(Member1 member1);

    void edit(Member1 member1);

    void remove(Member1 member1);

    Member1 find(Object id);

    List<Member1> findAll();

    List<Member1> findRange(int[] range);

    int count();
    
}
