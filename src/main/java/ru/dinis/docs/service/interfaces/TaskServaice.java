package ru.dinis.docs.service.interfaces;


import ru.dinis.docs.beans.Employee;
import ru.dinis.docs.beans.Subdivision;
import ru.dinis.docs.beans.Task;

import java.util.Set;

/**
 * Create by dinis of 22.04.18.
 */
public interface TaskServaice {

    void addTask(Task task);

    Set<Task> getAllTask();

    Set<Task> getAllTaskSubdiv(Subdivision subdivision);

    Set<Task> getAllTaskAuthor(Employee employee);

    Task getTaskById(int id);

    void deleteTask(Task task);

    void updateTask(Task task);

    void removeTaskById(int id);

}
