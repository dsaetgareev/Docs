package ru.dinis.docs.service.interfaces;


import ru.dinis.docs.beans.Firm;

import java.util.Set;

/**
 * Create by dinis of 22.04.18.
 */
public interface FirmService {

    void addFirm(Firm firm);

    Set<Firm> getAllFirm();

    Firm getFirmById(int id);

    Set<Firm> getFirmByName(String name);

    void updateFirm(Firm firm);

    void deleteFirm(Firm firm);

    void removeFirmById(int id);

}
