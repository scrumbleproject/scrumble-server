/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.sessionbeans;

import com.scrumble.server.utils.ScrumbleUtils;
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
    
    @EJB
    private Member1FacadeLocal memberBean;

    public AuthFacade() {
        //
    }
    
    public String authenticate(String login, String password) 
            throws Exception{
        
        if (memberBean.checkLoginAndPassword(login, password)){
            
            String scrumbleToken = "5dc728a";
            String key = UUID.randomUUID().toString().toUpperCase() + "|" + scrumbleToken + "|" + login + "|" + System.currentTimeMillis();
            
            String encryptedKey = ScrumbleUtils.encryptStringWithAlgorithm(key, "SHA1");

            return encryptedKey;
        }
        
        return null;
    }
    
}
