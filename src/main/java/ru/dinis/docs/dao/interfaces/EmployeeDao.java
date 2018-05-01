package ru.dinis.docs.dao.interfaces;

import ru.dinis.docs.beans.Employee;

import java.util.Set;

/**
 * Create by dinis of 22.04.18.
 */
public interface EmployeeDao {

    void addEmployee(Employee employee);

    Set<Employee> getBySql(String sql);

    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}
