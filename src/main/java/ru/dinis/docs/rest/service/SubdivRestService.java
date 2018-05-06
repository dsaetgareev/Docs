package ru.dinis.docs.rest.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.dinis.docs.beans.Subdivision;
import ru.dinis.docs.service.impl.SubdivServiceImpl;
import ru.dinis.docs.service.interfaces.SubdivService;

import javax.ws.rs.*;
import java.io.IOException;

/**
 * Create by dinis of 04.05.18.
 */
@Path(value = "/subdiv")
public class SubdivRestService {

    private SubdivService subdivService = new SubdivServiceImpl();


    @POST
    @Path("/update")
    @Consumes(value={"text/xml", "application/json"})
    public void updateSubdiv(String object) {
        ObjectMapper mapper = new ObjectMapper();
        Subdivision subdivision = null;
        try {
            subdivision = mapper.readValue(object, Subdivision.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.subdivService.updateSubdiv(subdivision);
    }

    @GET
    @Produces("application/json")
    @Path("/remove/{id}")
    public void removeSubdivById(@PathParam("id") int id) {
        this.subdivService.removeSubdivById(id);
    }

    @POST
    @Path("/add")
    @Consumes(value={"text/xml", "application/json"})
    public void addSubdiv(String object) {
        ObjectMapper mapper = new ObjectMapper();
        Subdivision subdivision = null;
        try {
            subdivision = mapper.readValue(object, Subdivision.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.subdivService.addSubdiv(subdivision);
    }
}
