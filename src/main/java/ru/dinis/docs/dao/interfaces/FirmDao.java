package ru.dinis.docs.dao.interfaces;

import ru.dinis.docs.beans.Firm;

import java.util.Set;

/**
 * Create by dinis of 21.04.18.
 */
public interface FirmDao {

    void addFirm(Firm firm);

    Set<Firm> getFirmBySql(String sql);

    void updateFirm(Firm firm);

    void deleteFirm(Firm firm);
}
