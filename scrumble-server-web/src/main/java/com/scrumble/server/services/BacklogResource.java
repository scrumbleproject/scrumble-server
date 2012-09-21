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
public class BacklogResource {

    private String id;

    /**
     * Creates a new instance of BacklogResource
     */
    private BacklogResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the BacklogResource
     */
    public static BacklogResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of BacklogResource class.
        return new BacklogResource(id);
    }

    /**
     * Retrieves representation of an instance of com.scrumble.server.services.BacklogResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of BacklogResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource BacklogResource
     */
    @DELETE
    public void delete() {
    }
}
