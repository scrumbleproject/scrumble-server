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

    /** netbeans generated methods **/
    
    void create(Member1 member1);

    void edit(Member1 member1);

    void remove(Member1 member1);

    Member1 find(Object id);

    List<Member1> findAll();

    List<Member1> findRange(int[] range);

    int count();
    
    /** custom methods **/
    
    /**
     * QUICK SEARCH : search members within all Member1 object attributes
     * @param pattern a String to compare with any attributes content
     * @return a list with all matching Member1 object 
     */
    public List<Member1> quickSearch(String pattern);
    
}
