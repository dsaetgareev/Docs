package ru.dinis.docs.dao.impl;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.dinis.docs.beans.Task;
import ru.dinis.docs.dao.interfaces.TaskDao;
import ru.dinis.docs.db.DBManager;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Create by dinis of 22.04.18.
 */
public class TaskDaoImpl implements TaskDao {

    private static Logger logger = Logger.getLogger(TaskDaoImpl.class);

    @Override
    public void addTask(Task task) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.saveOrUpdate(task);
            tr.commit();
            logger.info("Task added");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Set<Task> getTaskBySql(String sql) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        List<Task> tasks = null;
        try {
            tasks = session.createQuery(sql).list();
            tr.commit();
            logger.info("Task get by sql!");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new TreeSet<Task>(tasks);
    }

    @Override
    public void updateTask(Task task) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.merge(task);
            tr.commit();
            logger.info("Task " + task.getTaskId() + " updated");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteTask(Task task) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            task.setAuthor(null);
            task.setPerformers(null);
            session.delete(task);
            tr.commit();
            logger.info("Task " + task.getTaskId() + " deleted");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
