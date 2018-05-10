package ru.dinis.docs.rest.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.dinis.docs.beans.Employee;
import ru.dinis.docs.beans.Task;
import ru.dinis.docs.dto.ConverterDto;
import ru.dinis.docs.dto.EmplConvDto;
import ru.dinis.docs.dto.EmployeeDto;
import ru.dinis.docs.dto.TaskDto;
import ru.dinis.docs.service.impl.EmployeeServiceImpl;
import ru.dinis.docs.service.impl.TaskServiceImpl;
import ru.dinis.docs.service.interfaces.EmployeeService;
import ru.dinis.docs.service.interfaces.TaskServaice;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Create by dinis of 06.05.18.
 */
@Path(value = "/task")
public class TaskRestService {

    private static Logger logger = Logger.getLogger(TaskRestService.class);

    private TaskServaice taskServaice = new TaskServiceImpl();

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @GET
    @Produces("application/json")
    @Path("empl/{id}")
    public String getAllEmpl(@PathParam("id") int id) {
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = this.employeeService.getEmployeeById(id);

        EmployeeDto employeeDto = EmplConvDto.emplToEmplDto(employee);
        employeeDto.setSubdivision(null);
        String json = null;
        try {
            json = mapper.writeValueAsString(employeeDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logger.info(json);
        return json;
    }

    @GET
    @Produces("application/json")
    @Path("empl/author/{id}")
    public String getInstuctions(@PathParam("id") int id) {
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = this.employeeService.getEmployeeById(id);

        EmployeeDto employeeDto = EmplConvDto.emplToEmplDto(employee);
        employeeDto.setSubdivision(null);
        String json = null;
        Set<TaskDto> tasks = new HashSet<>();

        for (Task task : employeeDto.getInstructions()) {
            TaskDto temp = ConverterDto.taskTotaskDto(task);
            temp.setPerformers(null);
            tasks.add(temp);
        }

        try {
            json = mapper.writeValueAsString(tasks);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logger.info(json);
        return json;
    }

    @POST
    @Path("/add")
    @Consumes(value={"text/xml", "application/json"})
    public void addTask(String object) {
        ObjectMapper mapper = new ObjectMapper();
        TaskDto taskDto = null;
        Task task = null;
        try {
            taskDto = mapper.readValue(object, TaskDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        task = ConverterDto.taskDtoToTask(taskDto);
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
