/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import sun.misc.BASE64Encoder;

/**
 *
 * @author cyril
 */
@Stateless
@LocalBean
public class AuthFacade implements AuthFacadeLocal {
    
    //@EJB
    //private Member1FacadeLocal memberBean;

    public AuthFacade() {
        //
    }
    
    public String authenticate(String login, String password) 
            throws Exception{
        
        throw new Exception("mon exception");
        
        //if (memberBean.checkLoginAndPassword(login, password)){
            
//        String msg = "1";
//        try {
//            
//            String scrumbleToken = "5dc728a";
//        
//            String key = UUID.randomUUID().toString().toUpperCase() + "|" + scrumbleToken + "|" + login + "|" + System.currentTimeMillis();
//            msg += "2";
//            MessageDigest md = MessageDigest.getInstance("SHA");
//            msg += "3";
//            md.update(key.getBytes("UTF-8"));
//            msg += "4";
//            byte digest[] = md.digest();
//            msg += "5";
//            String encryptedKey = (new BASE64Encoder()).encode(digest);
//            msg += "6";
//            
//            return encryptedKey;
//        }
//        catch (Exception ex){
//            throw new Exception(msg);
//        }     
        
    }
    
}
