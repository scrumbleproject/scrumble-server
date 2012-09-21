/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.services;

import com.scrumble.server.entities.Member1;
import com.scrumble.server.sessionbeans.Member1FacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author cyril
 */
@Path("/members")
@Stateless
public class MembersResource {

    @Context
    private UriInfo context;

    @EJB
    private Member1FacadeLocal ejbMember;
    
    /**
     * Creates a new instance of MembersResource
     */
    public MembersResource() {
    }

    /**
     * Retrieves representation of an instance of com.scrumble.server.services.MembersResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of MemberResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public MemberResource getMemberResource(@PathParam("id") String id) {
        return MemberResource.getInstance(id);
    }
    
    
    /**
     * Retrieves representation of list of com.scrumble.server.entities.Member1 object
     * @return a JSON representation of the list of all members.
     */
    @GET
    @Path("findall")
    @Produces("application/json")
    public List<Member1> findAll() {
        List<Member1> results = ejbMember.findAll();
        return results;
    }
    
}
