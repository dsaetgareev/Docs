package ru.dinis.docs.service.impl;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import ru.dinis.docs.beans.Employee;
import ru.dinis.docs.beans.Subdivision;
import ru.dinis.docs.dao.impl.EmployeeDaoImpl;
import ru.dinis.docs.dao.interfaces.EmployeeDao;
import ru.dinis.docs.db.DBManager;
import ru.dinis.docs.service.interfaces.EmployeeService;
import ru.dinis.docs.service.interfaces.SubdivService;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Create by dinis of 22.04.18.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    private SubdivService subdivService = new SubdivServiceImpl();

    private Subdivision subdivision;

    public EmployeeServiceImpl() {
    }

    public EmployeeServiceImpl(Subdivision subdivision) {
        this.subdivision = subdivision;
    }

    @Override
    public void addEmployee(Employee employee) {
        this.employeeDao.addEmployee(employee);
    }

    @Override
    public Set<Employee> getAllEmployee() {
        return this.employeeDao.getBySql("FROM Employee");
    }

    @Override
    public Set<Employee> getAllEmplFromFirm() {
        return null;
    }

    @Override
    public Set<Employee> getAllEmplFromSubdiv() {
        return this.subdivision.getEmployees();
    }

    @Override
    public Employee getEmployeeById(int id) {
        Iterator<Employee> iterator = this.employeeDao.getBySql("FROM Employee where emplId = " + id).iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    @Override
    public Set<Employee> getEmployeeByName(String surname, String firstName, String patronymic, Subdivision subdivision) {
        Session session = DBManager.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Employee.class, "e");
        Transaction tr = session.beginTransaction();
        List<Employee> users = null;
        if (subdivision != null) {
            criteria = criteria.add(Restrictions.ilike("e.subdivision", subdivision.getName(), MatchMode.START));
        }
        if (surname != null) {
            criteria = criteria.add(Restrictions.ilike("e.surname",surname, MatchMode.START));;
        }
        if (firstName != null) {
            criteria = criteria.add(Restrictions.ilike("e.firstName",firstName, MatchMode.START));;
        }
        if (patronymic != null) {
            criteria = criteria.add(Restrictions.ilike("e.patronymic",patronymic, MatchMode.START));;
        }
        try {
            users = criteria.list();
            tr.commit();
        } catch (HibernateException he) {
            tr.rollback();
            he.printStackTrace();
        } finally {
            session.close();
        }
        return new TreeSet<Employee>(users);
    }

    @Override
    public void updateEmployee(Employee employee) {
        this.employeeDao.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        this.employeeDao.deleteEmployee(employee);
    }
}
