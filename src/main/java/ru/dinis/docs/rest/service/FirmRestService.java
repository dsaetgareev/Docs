package ru.dinis.docs.rest.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.dinis.docs.beans.Firm;
import ru.dinis.docs.service.impl.FirmServiceImpl;
import ru.dinis.docs.service.interfaces.FirmService;
import ru.dinis.docs.test.Bean;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Set;

/**
 * Create by dinis of 24.04.18.
 */
@Path(value = "/firm")
public class FirmRestService {

    private FirmService firmService = new FirmServiceImpl();

    @GET
    @Produces("application/json")
    public Set<Firm> getAllFirm() {
        return this.firmService.getAllFirm();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public String getFirmById(@PathParam("id") int id) {
        Firm firm = this.firmService.getFirmById(id);
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(firm);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @POST
    
    @Consumes(value={"text/xml", "application/json"})
    public void updateFirm(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Firm firm = null;
        Object object = null;
        try {
            object =  mapper.readValue(json, Firm.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(object);
        this.firmService.updateFirm(firm);
    }


}
