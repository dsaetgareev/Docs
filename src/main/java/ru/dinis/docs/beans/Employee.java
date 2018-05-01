package ru.dinis.docs.beans;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Create by dinis of 21.04.18.
 */
@Entity
@Table(name = "employee")
@XmlRootElement
public class Employee implements Serializable, Comparable<Employee> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empl_id")
    private int emplId;

    @Column(name = "surname")
    private String surname;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "position")
    private String position;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "subdiv_id")
    @JsonBackReference
    private Subdivision subdivision;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE })
    @JoinTable(name = "empl_tasks",
    joinColumns = @JoinColumn(name = "empl_id"),
    inverseJoinColumns = @JoinColumn(name = "task_id"))
    @JsonIgnore
    private Set<Task> instructions = new HashSet<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Task> myTasks = new HashSet<>();

    public Employee() {
    }

    @Override
    public int compareTo(Employee o) {
        return this.surname.compareTo(o.surname);
    }

    public int getEmplId() {
        return emplId;
    }

    public void setEmplId(int emplId) {
        this.emplId = emplId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }

    public Set<Task> getInstructions() {
        return instructions;
    }

    public void setInstructions(Set<Task> instructions) {
        this.instructions = instructions;
    }

    public Set<Task> getMyTasks() {
        return myTasks;
    }

    public void setMyTasks(Set<Task> myTasks) {
        this.myTasks = myTasks;
    }
}
