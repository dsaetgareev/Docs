package ru.dinis.docs.beans;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "subdivision")
@XmlRootElement
public class Subdivision implements Serializable, Comparable<Subdivision>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subdiv_id")
    private int subdivId;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_date")
    private String contactDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empl_id")
    @JsonManagedReference
    private Employee headSubdiv;

    @OneToMany(mappedBy = "subdivision", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonManagedReference
    private Set<Employee> employees = new TreeSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "firm_id")
    @JsonBackReference
    private Firm firm;


    public Subdivision() {
    }

    @Override
    public int compareTo(Subdivision o) {
        return this.name.compareTo(o.getName());
    }

    public int getSubdivId() {
        return subdivId;
    }

    public void setSubdivId(int subdivId) {
        this.subdivId = subdivId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDate() {
        return contactDate;
    }

    public void setContactDate(String contactDate) {
        this.contactDate = contactDate;
    }

    public Employee getHeadSubdiv() {
        return headSubdiv;
    }

    public void setHeadSubdiv(Employee headSubdiv) {
        this.headSubdiv = headSubdiv;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

}
