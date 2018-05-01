package ru.dinis.docs.service.interfaces;

import ru.dinis.docs.beans.Firm;
import ru.dinis.docs.beans.Subdivision;

import java.util.Set;

/**
 * Create by dinis of 22.04.18.
 */
public interface SubdivService {

    void addSubdiv(Subdivision subdivision);

    Set<Subdivision> getAllSubdiv();

    Subdivision getSubdivById(int id);

    Set<Subdivision> getSubdivByName(String name);

    void updateSubdiv(Subdivision subdivision);

    void deleteSubdiv(Subdivision subdivision);

}
