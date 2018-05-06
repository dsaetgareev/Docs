package ru.dinis.docs.rest.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.dinis.docs.beans.Employee;
import ru.dinis.docs.service.impl.EmployeeServiceImpl;
import ru.dinis.docs.service.interfaces.EmployeeService;

import javax.ws.rs.*;
import java.io.IOException;

/**
 * Create by dinis of 04.05.18.
 */
@Path(value = "empl")
public class EmplRestService {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @POST
    @Path("/update")
    @Consumes(value={"text/xml", "application/json"})
    public void updateEmpl(String object) {
        Employee employee = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            employee = mapper.readValue(object, Employee.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.employeeService.updateEmployee(employee);
    }

    @GET
    @Produces("application/json")
    @Path("/remove/{id}")
    public void removeEmplById(@PathParam("id") int id) {
        this.employeeService.removeEmplById(id);
    }

    @POST
    @Path("/add")
    @Consumes(value={"text/xml", "application/json"})
    public void addEmployee(String object) {
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = null;
        try {
            employee = mapper.readValue(object, Employee.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.employeeService.addEmployee(employee);
    }
}
