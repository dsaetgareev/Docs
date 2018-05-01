package ru.dinis.docs.service.impl;


import ru.dinis.docs.beans.Firm;
import ru.dinis.docs.beans.Subdivision;
import ru.dinis.docs.dao.impl.SubdivDaoImpl;
import ru.dinis.docs.dao.interfaces.SubdivDao;
import ru.dinis.docs.service.interfaces.SubdivService;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Create by dinis of 22.04.18.
 */
public class SubdivServiceImpl implements SubdivService {

    private Firm firm;

    private SubdivDao subdivDao = new SubdivDaoImpl();

    public SubdivServiceImpl() {
    }

    public SubdivServiceImpl(Firm firm) {
        this.firm = firm;
    }

    @Override
    public void addSubdiv(Subdivision subdivision) {
        this.subdivDao.addSubdiv(subdivision);
    }

    @Override
    public Set<Subdivision> getAllSubdiv() {
        return this.subdivDao.getSubdivBySql("FROM Subdivision");
    }

    public Set<Subdivision> getSubdivFromFirm() {
        return this.firm.getSubdivs();
    }

    @Override
    public Subdivision getSubdivById(int id) {
        Iterator<Subdivision> iterator = this.subdivDao.getSubdivBySql("FROM Subdivision where subdiv_id = " + id).iterator();
        return iterator.hasNext() ? iterator.next() : new Subdivision();
    }

    public Subdivision getSubdivByIdFromFirm(int id) {
        Subdivision result = null;
        for (Subdivision subdivision : getSubdivFromFirm()) {
            if (subdivision.getSubdivId() == id) {
                result = subdivision;
            }
        }
        return result;
    }

    @Override
    public Set<Subdivision> getSubdivByName(String name) {
        return this.subdivDao.getSubdivBySql("FROM Subdivision where name = '" + name + "'");
    }

    public Subdivision getSubdivByNameFromFirm(String name) {
        Subdivision result = null;
        for (Subdivision subdivision : getSubdivFromFirm()) {
            if (subdivision.getName().equals(name)) {
                result = subdivision;
            }
        }
        return result;
    }

    @Override
    public void updateSubdiv(Subdivision subdivision) {
        this.subdivDao.updateSubdiv(subdivision);
    }

    @Override
    public void deleteSubdiv(Subdivision subdivision) {
        this.subdivDao.deleteSubdiv(subdivision);
    }
}
