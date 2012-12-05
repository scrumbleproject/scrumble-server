/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.services;

import com.scrumble.server.sessionbeans.AuthFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author cyril
 */
@Path("auth")
@Stateless
public class AuthResource {

    @Context
    private UriInfo context;

    @EJB
    private AuthFacadeLocal authBean;  
    
    /**
     * Creates a new instance of AuthResource
     */
    public AuthResource() {
        //
    }

    /**
     * Retrieves representation of an instance of com.scrumble.server.services.AuthResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        return "YES";
    }

    /**
     * POST method to authenticate user and return a token to use in requests
     * @param login the login of the user
     * @param password the password of the user
     * @return the token as the authentication key or null if authenticate failed.
     */
    @GET
    @Path("{login}/{password}")
    //@Consumes("application/json")
    @Produces("application/json")
    public String authenticate(@PathParam("login") String login, @PathParam("password") String password) {
    //public String authenticate() {
        System.out.println("AUTH with "+login+"/"+password);
        String token = "rien";
        try {
            if (authBean==null) System.out.println("authBean is null");
            //token = authBean.authenticate(login, password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //ex.printStackTrace();
            //Logger.getLogger(AuthResource.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("TOKEN="+token);
            return "FAILED";
        }
        //String token = "SUCCESS";
        System.out.println("TOKEN="+token);
        return token;
    }
}
