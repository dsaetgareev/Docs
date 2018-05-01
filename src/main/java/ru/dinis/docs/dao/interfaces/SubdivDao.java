package ru.dinis.docs.dao.interfaces;

import ru.dinis.docs.beans.Subdivision;

import java.util.Set;

/**
 * Create by dinis of 22.04.18.
 */
public interface SubdivDao {

    void addSubdiv(Subdivision subdivision);

    Set<Subdivision> getSubdivBySql(String sql);

    void updateSubdiv(Subdivision subdivision);

    void deleteSubdiv(Subdivision subdivision);
}
