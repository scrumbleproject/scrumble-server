package com.scrumble.server.services;

import com.scrumble.server.entities.Project;
import com.scrumble.server.entities.Userstory;
import com.scrumble.server.sessionbeans.UserstoryFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ArNo
 */
@Path("userstory")
@Stateless
public class UserStoryResource {

    @Context
    private UriInfo context;
    
    @EJB
    private UserstoryFacadeLocal userStoryBean;

    /**
     * Creates a new instance of UserStoryResource
     */
    public UserStoryResource() {
    }

    /**
     * Retrieves representation of an instance of com.scrumble.server.services.UserStoryResource
     * @return an instance of java.lang.String
     */
    /*@GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }*/

    /**
     * PUT method for updating or creating an instance of UserStoryResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    /*@PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }*/
    
    /**
     * Retrieves representation of list of com.scrumble.server.entities.Userstory object
     * @return a JSON representation of the list of all userstories.
     */
    @GET
    @Path("all")
    @Produces("application/json")
    public List<Userstory> findAll() {
        return userStoryBean.findAll();
    }
    
    /**
     * Retrieves representation of a single com.scrumble.server.entities.Userstory object
     * @param id of the Userstory object to retrieve
     * @return a JSON representation of the related Userstory object.
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Userstory getUserStory(@PathParam("id") String id) {
        return userStoryBean.find(Integer.parseInt(id));
    }
    
    /**
     * Removes a single com.scrumble.server.entities.Userstory object
     * @param id the id of the Userstory object to remove
     * @return nothing.
     */
    /*@DELETE
    @Path("removeuserstory/{id:[0-9]+}")
    @Produces("application/json")
    public void removeUserStory(@PathParam("id") int id) {
        userStoryBean.remove(userStoryBean.find(id));
        
        Response reponse=Response.status(200).build();
        return reponse;
    }*/
    
    /**
     * Creates a single com.scrumble.server.entities.Userstory object
     * @param form
     * @return nothing.
     */
    /*@POST
    @Path("createuserstory")
    @Produces("text/html")
    public void createUserStory(MultivaluedMap<String,String> paramsFormulaire) {
        
        Userstory userstory=new Userstory();
        
        userstory.setCategory(paramsFormulaire.get("").toString());
        userstory.setDemonstration(paramsFormulaire.get("").toString());
        userstory.setEstimation(paramsFormulaire.get("").toString());
        userstory.setIdProject(new Project(Integer.parseInt(paramsFormulaire.get("").toString())));
        userstory.setImportance(paramsFormulaire.get("").toString());
        userstory.setNote(paramsFormulaire.get("").toString());
        userstory.setTitle(paramsFormulaire.get("").toString());
        
        userStoryBean.create(userstory);
    }*/
}