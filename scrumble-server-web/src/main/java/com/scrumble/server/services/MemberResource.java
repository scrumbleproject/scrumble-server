/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;

/**
 * REST Web Service
 *
 * @author cyril
 */
public class MemberResource {

    private String id;

    /**
     * Creates a new instance of MemberResource
     */
    private MemberResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the MemberResource
     */
    public static MemberResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of MemberResource class.
        return new MemberResource(id);
    }

    /**
     * Retrieves representation of an instance of com.scrumble.server.services.MemberResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of MemberResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource MemberResource
     */
    @DELETE
    public void delete() {
    }
}
