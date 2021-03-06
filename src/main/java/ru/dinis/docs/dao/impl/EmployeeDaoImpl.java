package ru.dinis.docs.dao.impl;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.dinis.docs.beans.Employee;
import ru.dinis.docs.dao.interfaces.EmployeeDao;
import ru.dinis.docs.db.DBManager;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Create by dinis of 22.04.18.
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private static Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

    @Override
    public void addEmployee(Employee employee) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.saveOrUpdate(employee);
            tr.commit();
            logger.info("Employee added!");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Set<Employee> getBySql(String sql) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        List<Employee> employees = null;
        try {
            employees = session.createQuery(sql).list();
            tr.commit();
            logger.info("Employee get by sql!");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees != null ? new TreeSet<Employee>(employees) : new TreeSet<Employee>();
    }

    @Override
    public void updateEmployee(Employee employee) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.merge(employee);
            tr.commit();
            logger.info("Employee " + employee.getEmplId() + "updated!");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        if (employee.getSubdivision().getHeadSubdiv() != null && employee.getSubdivision().getHeadSubdiv().equals(employee)) {
            employee.getSubdivision().setHeadSubdiv(null);
        }
        employee.setInstructions(null);
        employee.setMyTasks(null);
        this.updateEmployee(employee);
        employee.setSubdivision(null);
        try {
            session.delete(employee);
            tr.commit();
            logger.info("Emloyee " + employee.getEmplId() + " deleted");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
