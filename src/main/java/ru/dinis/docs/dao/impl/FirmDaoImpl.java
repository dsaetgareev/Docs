package ru.dinis.docs.dao.impl;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.dinis.docs.beans.Firm;
import ru.dinis.docs.dao.interfaces.FirmDao;
import ru.dinis.docs.db.DBManager;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Create by dinis of 21.04.18.
 */
public class FirmDaoImpl implements FirmDao {

    private static Logger logger = Logger.getLogger(FirmDaoImpl.class);

    @Override
    public void addFirm(Firm firm) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.saveOrUpdate(firm);
            tr.commit();
            logger.info("Firm saved!");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public Set<Firm> getFirmBySql(String sql) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        List<Firm> firms = null;
        try {
            firms = session.createQuery(sql).list();
            tr.commit();
            logger.info("Get firm by sql!");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new TreeSet<Firm>(firms);
    }

    @Override
    public void updateFirm(Firm firm) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.merge(firm);
            tr.commit();
            logger.info("firm " + firm.getFirmId() + " updated!");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteFirm(Firm firm) {
        Session session = DBManager.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        try {
            session.delete(firm);
            tr.commit();
            logger.info("firm " + firm.getFirmId() + " deleted");
        } catch (HibernateException e) {
            tr.rollback();
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
