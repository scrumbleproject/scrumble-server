/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import javax.ejb.Local;

/**
 *
 * @author cyril
 */
@Local
public interface AuthFacadeLocal {

    
    public String authenticate(String login, String password) throws Exception;
    
}
