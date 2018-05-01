package ru.dinis.docs.test;


import ru.dinis.docs.beans.Employee;
import ru.dinis.docs.beans.Firm;
import ru.dinis.docs.beans.Subdivision;
import ru.dinis.docs.beans.Task;
import ru.dinis.docs.service.impl.EmployeeServiceImpl;
import ru.dinis.docs.service.impl.FirmServiceImpl;
import ru.dinis.docs.service.impl.SubdivServiceImpl;
import ru.dinis.docs.service.impl.TaskServiceImpl;
import ru.dinis.docs.service.interfaces.EmployeeService;
import ru.dinis.docs.service.interfaces.FirmService;
import ru.dinis.docs.service.interfaces.SubdivService;
import ru.dinis.docs.service.interfaces.TaskServaice;

import java.util.*;

/**
 * Create by dinis of 22.04.18.
 */
public class GenerateDB {

    public Set<Firm> genFirm() {
        Set<Firm> set = new TreeSet<>();
        for(int i = 0;i < 5; i++) {
            Firm firm = new Firm();
            firm.setName("firm " + i);
            firm.setDirector("director " + i);
            firm.setLocalAddress("Local Addres " + i);
            firm.setLegalAddress("legal address" + i);
            set.add(firm);
        }
        return set;
    }

    public Set<Subdivision> genSubdiv() {
        Set<Subdivision> set = new TreeSet<>();
        for(int i = 0;i < 5; i++) {
            Subdivision subdiv = new Subdivision();
            subdiv.setName("subdiv" + Math.random());
            subdiv.setContactDate(new Date().toString());
            set.add(subdiv);
        }
        return set;
    }

    public Set<Employee> genEmployee() {
        Set<Employee> set = new TreeSet<>();
        for(int i = 0;i < 5; i++) {
            Employee employee = new Employee();
            employee.setSurname("surname " + i);
            employee.setFirstName("first name " + i);
            employee.setPatronymic("patronymim " + i);
            employee.setPosition("position " + i);
            set.add(employee);
        }
        return set;
    }

    public Set<Task> genTasks(Employee employee, Set<Employee> employees) {
        Set<Task> tasks = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            Task task = new Task();
            task.setAuthor(employee);
            task.setControl(true);
            task.setDescr(Math.random() + "");
            task.setExecution(Math.random() < 0.5);
            task.setPeriod(new Date());
            Set<Employee> perfor = new TreeSet<>();
            Iterator<Employee> iterator = employees.iterator();
            for (int j = 0; j < 5; j++) {
                perfor.add(iterator.next());
            }
            task.setPerformers(perfor);
            tasks.add(task);
        }
        return tasks;
    }


    public static void main(String[] args) {
        FirmService firmService = new FirmServiceImpl();
        SubdivService subdivService = new SubdivServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();

        Set<Employee> employees = employeeService.getAllEmployee();
        HashSet<Employee> empls = new HashSet<>(employees);
        for(Employee employee : employees) {
            TaskServaice taskServaice = new TaskServiceImpl(employee);
            for (Task task : new GenerateDB().genTasks(employee, empls)) {
                taskServaice.addTask(task);
            }

        }

    }

}
