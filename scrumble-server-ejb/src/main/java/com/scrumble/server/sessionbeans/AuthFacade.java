/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.utils.ScrumbleUtils;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author cyril
 */
@Stateless
@LocalBean
public class AuthFacade implements AuthFacadeLocal {
    
    @EJB
    private Member1FacadeLocal memberBean;
    
    //list of all registered tokens
    private Map<String,String> tokens = new HashMap<String,String>();
    
    private int timeToLive = 1800;

    public AuthFacade() {
        //
    }
    
    public String authenticate(String login, String password) 
            throws Exception{
        
        if (memberBean.checkLoginAndPassword(login, password)){
            
            String scrumbleToken = "5dc728a";
            String key = UUID.randomUUID().toString().toUpperCase() + "|" + scrumbleToken + "|" + login + "|" + System.currentTimeMillis();
            
            String encryptedKey = ScrumbleUtils.encryptStringWithAlgorithm(key, "SHA1");
            
            //store token for this user
            this.tokens.put(login, key);
            
            return encryptedKey;
        }
        
        return null;
    }
    
    public boolean checkToken(String login, String encryptKey) throws Exception{
        
        if (this.tokens.containsKey(login)){ //if user already authenticated
           
            String token = this.tokens.get(login); 
           
           //check if the passed toked is correct
           String encryptToken = ScrumbleUtils.encryptStringWithAlgorithm(token, "SHA1"); 
           if (encryptToken.equalsIgnoreCase(encryptKey)) {
               
               //check if token has expired
               String[] splitToken = token.split("|");
               long createdTimestamp = Long.parseLong(splitToken[splitToken.length-1]);
               long currentTimestamp = System.currentTimeMillis();
               if (currentTimestamp-createdTimestamp<this.timeToLive){ //
                   return true;
               }
               else {
                   //token has expired, delete it and redirect to authentication
                   this.tokens.remove(login); //delete entry
                   return false; //redirect
               }
               
           }
           
        } 
           
        //redirect to authentication
        return false;
     
    }
    
}
