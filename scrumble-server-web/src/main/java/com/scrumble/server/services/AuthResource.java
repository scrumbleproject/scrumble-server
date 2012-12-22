/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.services;

import com.scrumble.server.sessionbeans.AuthFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

/**
 * REST Web Service
 *
 * @author cyril
 */
@Path("auth")
@Stateless
public class AuthResource {
    
    @Context
    private HttpHeaders httpHeaders;

    @EJB
    private AuthFacadeLocal authBean; 
    
    /**
     * Creates a new instance of AuthResource
     */
    public AuthResource() {
        //
    }

    /**
     * POST method to authenticate user and return a token to use in requests
     * @param login the login of the user
     * @param password the password of the user
     * @return the token as the authentication key or null if authenticate failed.
     */
    @POST
    @Path("{login}/{password}")
    @Produces("text/plain")
    public String authenticate(@PathParam("login") String login, @PathParam("password") String password) {
        String token = "";
        try {
            token = authBean.authenticate(login, password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "FAILED";
        }
        return token;
    }
    
    @POST
    @Path("check")
    @Produces("text/plain")
    public String checkToken(){
        StringBuffer msg = new StringBuffer();
        
        for (String s : httpHeaders.getRequestHeader(httpHeaders.AUTHORIZATION)){
            msg.append(s);
        }
        return msg.toString();
        //return httpHeaders.AUTHORIZATION;   
        //return "authorization";
    }
    
}
