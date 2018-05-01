package ru.dinis.docs.dao.interfaces;

import ru.dinis.docs.beans.Task;

import java.util.Set;

/**
 * Create by dinis of 22.04.18.
 */
public interface TaskDao {

    void addTask(Task task);

    Set<Task> getTaskBySql(String sql);

    void updateTask(Task task);

    void deleteTask(Task task);
}
