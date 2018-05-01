package ru.dinis.docs.dao.impl;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.dinis.docs.beans.Subdivision;
import ru.dinis.docs.dao.interfaces.SubdivDao;
import ru.dinis.docs.db.DBManager;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Create by dinis of 22.04.18.
 */
public class SubdivDaoImpl implements SubdivDao {

    @Override
    public void addSubdiv(Subdivision subdivision) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.saveOrUpdate(subdivision);
            tr.commit();
        } catch (HibernateException e) {
            tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Set<Subdivision> getSubdivBySql(String sql) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        List<Subdivision> subdivs = null;
        try {
            subdivs = session.createQuery(sql).list();
            tr.commit();
        } catch (HibernateException e) {
            tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return subdivs != null ? new TreeSet<Subdivision>(subdivs) : new TreeSet<Subdivision>();
    }

    @Override
    public void updateSubdiv(Subdivision subdivision) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.merge(subdivision);
            tr.commit();
        } catch (HibernateException e) {
            tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteSubdiv(Subdivision subdivision) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            subdivision.setFirm(null);
            session.delete(subdivision);
            tr.commit();
        } catch (HibernateException e) {
            tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
