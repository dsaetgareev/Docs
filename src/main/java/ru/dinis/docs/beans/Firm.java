package ru.dinis.docs.beans;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Create by dinis of 21.04.18.
 */
@Entity
@Table(name = "firm")
@XmlRootElement
public class Firm implements Serializable, Comparable<Firm> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "firm_id")
    private int firmId;

    @Column(name = "name")
    private String name;

    @Column(name = "local_address")
    private String localAddress;

    @Column(name = "legal_address")
    private String legalAddress;

    @Column(name = "director")
    private String director;

    @OneToMany(mappedBy = "firm", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Subdivision> subdivs = new TreeSet<>();

    public Firm() {
    }

    @Override
    public int compareTo(Firm o) {
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
