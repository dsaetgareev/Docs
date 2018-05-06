package ru.dinis.docs.rest.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.dinis.docs.beans.Task;
import ru.dinis.docs.service.impl.TaskServiceImpl;
import ru.dinis.docs.service.interfaces.TaskServaice;

import javax.ws.rs.*;
import java.io.IOException;

/**
 * Create by dinis of 06.05.18.
 */
@Path(value = "/task")
public class TaskRestService {

    private TaskServaice taskServaice = new TaskServiceImpl();

    @POST
    @Path("/add")
    @Consumes(value={"text/xml", "application/json"})
    public void addTask(String object) {
        ObjectMapper mapper = new ObjectMapper();
        Task task = null;
        try {
            task = mapper.readValue(object, Task.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.taskServaice.addTask(task);
    }

    @POST
    @Path("/update")
    @Consumes(value={"text/xml", "application/json"})
    public void updateTask(String object) {
        ObjectMapper mapper = new ObjectMapper();
        Task task = null;
        try {
            task = mapper.readValue(object, Task.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.taskServaice.updateTask(task);
    }

    @GET
    @Produces("application/json")
    @Path("/remove/{id}")
    public void removeTaskById(@PathParam("id") int id) {
        this.taskServaice.removeTaskById(id);
    }

}
