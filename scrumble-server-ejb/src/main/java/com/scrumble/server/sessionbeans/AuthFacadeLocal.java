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

    /**
     * Allows user to authenticate with a login and a password
     * @param login the login of the user
     * @param password the password of the user 
     * @return a token if authentication has been successful or null otherwise
     */
    public String authenticate(String login, String password) throws Exception;
    
    
    /**
     * Check if token already exist or is not expired and authenticate otherwise
     * @param login the login of authenticated user
     * @param encryptKey the encrypted token passed in all user request
     * @return true if token is active or false otherwise
     */
    public boolean checkToken(String login, String encryptKey) throws Exception;
    
}
