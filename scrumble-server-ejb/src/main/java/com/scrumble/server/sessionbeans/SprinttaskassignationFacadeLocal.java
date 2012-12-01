/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.entities.Sprinttaskassignation;
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
    
}
