package ru.dinis.docs.dao.impl;


import org.apache.log4j.Logger;
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

    private static Logger logger = Logger.getLogger(SubdivDaoImpl.class);

    @Override
    public void addSubdiv(Subdivision subdivision) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.saveOrUpdate(subdivision);
            tr.commit();
            logger.info("Subdiv added!");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
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
            logger.info("get subdiv by sql!");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
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
            logger.info("subdiv " + subdivision.getSubdivId() + " updated!");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
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
            subdivision.setHeadSubdiv(null);
            this.updateSubdiv(subdivision);
            session.delete(subdivision);
            tr.commit();
            logger.info("subdiv " + subdivision.getSubdivId() + " deleted");
        } catch (HibernateException e) {
            tr.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
