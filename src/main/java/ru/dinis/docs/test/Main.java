package ru.dinis.docs.test;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.dinis.docs.beans.Employee;
import ru.dinis.docs.beans.Firm;
import ru.dinis.docs.beans.Subdivision;
import ru.dinis.docs.beans.Task;
import ru.dinis.docs.dao.impl.FirmDaoImpl;
import ru.dinis.docs.dao.interfaces.FirmDao;
import ru.dinis.docs.db.DBManager;
import ru.dinis.docs.dto.ConverterDto;
import ru.dinis.docs.dto.EmplConvDto;
import ru.dinis.docs.dto.EmployeeDto;
import ru.dinis.docs.dto.TaskDto;
import ru.dinis.docs.rest.service.FirmRestService;
import ru.dinis.docs.service.impl.EmployeeServiceImpl;
import ru.dinis.docs.service.impl.FirmServiceImpl;
import ru.dinis.docs.service.impl.SubdivServiceImpl;
import ru.dinis.docs.service.impl.TaskServiceImpl;
import ru.dinis.docs.service.interfaces.EmployeeService;
import ru.dinis.docs.service.interfaces.SubdivService;
import ru.dinis.docs.service.interfaces.TaskServaice;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by dinis of 21.04.18.
 */
public class Main {

    static void show(Set<Firm> set) {
        for(Firm firm : set) {
            System.out.println(firm.getName());
        }
    }

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = employeeService.getEmployeeById(790);
        EmployeeDto employeeDto = EmplConvDto.emplToEmplDto(employee);
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
        System.out.println(json);
    }

}
