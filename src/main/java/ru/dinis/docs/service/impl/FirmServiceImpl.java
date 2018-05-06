package ru.dinis.docs.service.impl;


import ru.dinis.docs.beans.Firm;
import ru.dinis.docs.dao.impl.FirmDaoImpl;
import ru.dinis.docs.dao.interfaces.FirmDao;
import ru.dinis.docs.service.interfaces.FirmService;

import java.util.Iterator;
import java.util.Set;

/**
 * Create by dinis of 22.04.18.
 */
public class FirmServiceImpl implements FirmService {

    private FirmDao firmDao = new FirmDaoImpl();

    @Override
    public void addFirm(Firm firm) {
        this.firmDao.addFirm(firm);
    }

    @Override
    public Set<Firm> getAllFirm() {
        return this.firmDao.getFirmBySql("FROM Firm");
    }

    @Override
    public Firm getFirmById(int id) {
        Iterator<Firm> iterator = this.firmDao.getFirmBySql("FROM Firm where firmId = " + id).iterator();
        return iterator.hasNext() ? iterator.next() : new Firm();
    }

    @Override
    public Set<Firm> getFirmByName(String name) {
        return this.firmDao.getFirmBySql("FROM Firm where name = '" + name +"'");
    }

    @Override
    public void updateFirm(Firm firm) {
        this.firmDao.updateFirm(firm);
    }

    @Override
    public void deleteFirm(Firm firm) {
        this.firmDao.deleteFirm(firm);
    }

    @Override
    public void removeFirmById(int id) {
        Firm firm = this.getFirmById(id);
        this.deleteFirm(firm);
    }
}
