package ru.dinis.docs.service.impl;


import ru.dinis.docs.beans.Employee;
import ru.dinis.docs.beans.Subdivision;
import ru.dinis.docs.beans.Task;
import ru.dinis.docs.dao.impl.TaskDaoImpl;
import ru.dinis.docs.dao.interfaces.TaskDao;
import ru.dinis.docs.service.interfaces.EmployeeService;
import ru.dinis.docs.service.interfaces.TaskServaice;

import java.util.Iterator;
import java.util.Set;

/**
 * Create by dinis of 22.04.18.
 */
public class TaskServiceImpl implements TaskServaice {

    private TaskDao taskDao = new TaskDaoImpl();

    private EmployeeService employeeService= new EmployeeServiceImpl();

    private Employee employee;

    public TaskServiceImpl() {
    }

    public TaskServiceImpl(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void addTask(Task task) {
        this.taskDao.addTask(task);
    }

    @Override
    public Set<Task> getAllTask() {
        return null;
    }

    @Override
    public Set<Task> getAllTaskSubdiv(Subdivision subdivision) {
        return null;
    }

    @Override
    public Set<Task> getAllTaskAuthor(Employee employee) {

        return this.taskDao.getTaskBySql("FROM Task where author = " + employee);
    }

    @Override
    public Task getTaskById(int id) {
        Iterator<Task> iterator = this.taskDao.getTaskBySql("FROM Task WHERE taskId = " + id).iterator();
        return iterator.hasNext() ? iterator.next() : new Task();
    }

    @Override
    public void deleteTask(Task task) {
        this.taskDao.deleteTask(task);
    }
}
