/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.services;

import com.scrumble.server.entities.Role;
import com.scrumble.server.sessionbeans.RoleFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Arnaud
 */
@Path("roles")
@Stateless
public class RolesResource {

    @Context
    private UriInfo context;

    @EJB
    private RoleFacadeLocal roleBean;
    
    /**
     * Creates a new instance of RolesResource
     */
    public RolesResource() {
    }
    
    /**
     * Retrieves representation of list of com.scrumble.server.entities.Role object
     * @return a JSON representation of the list of all roles.
     */
    @GET
    @Path("all")
    @Produces("application/json")
    public List<Role> findAll() {
        return roleBean.findAll();
    }
}
