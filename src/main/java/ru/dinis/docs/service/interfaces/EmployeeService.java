package ru.dinis.docs.service.interfaces;

import ru.dinis.docs.beans.Employee;
import ru.dinis.docs.beans.Subdivision;

import java.util.Set;

/**
 * Create by dinis of 22.04.18.
 */
public interface EmployeeService {

    void addEmployee(Employee employee);

    Set<Employee> getAllEmployee();

    Set<Employee> getAllEmplFromFirm();

    Set<Employee> getAllEmplFromSubdiv();

    Employee getEmployeeById(int id);

    Set<Employee> getEmployeeByName(String surname, String firstName, String patronymic, Subdivision subdivision);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    void removeEmplById(int id);


}
