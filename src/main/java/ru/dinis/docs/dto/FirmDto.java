package ru.dinis.docs.dto;

import ru.dinis.docs.beans.Subdivision;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;


public class FirmDto implements Serializable, Comparable<FirmDto> {

    private int firmId;

    private String name;

    private String localAddress;

    private String legalAddress;

    private String director;

    private Set<Subdivision> subdivs = new TreeSet<>();

    public FirmDto() {
    }

    public int compareTo(FirmDto o) {
        return this.name.compareTo(o.name);
    }

    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Set<Subdivision> getSubdivs() {
        return subdivs;
    }

    public void setSubdivs(Set<Subdivision> subdivs) {
        this.subdivs = subdivs;
    }
}

