package ru.dinis.docs.beans;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Create by dinis of 21.04.18.
 */
@Entity
@Table(name = "tasks")
@XmlRootElement
public class Task implements Serializable, Comparable<Task> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;

    @Column(name = "subject")
    private String subject;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empl_id")
    @JsonBackReference
    private Employee author;

    @ManyToMany
    @JoinTable(name = "empl_tasks",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "empl_id"))
    @JsonIgnore
    private Set<Employee> performers = new TreeSet<>();

    @Column(name = "period")
    @Temporal(TemporalType.DATE)
    private Date period;

    @Column(name = "control")
    private Boolean control;

    @Column(name = "execution")
    private Boolean execution;

    @Column(name = "descr")
    private String descr;

    public Task() {
    }

    @Override
    public int compareTo(Task o) {
        return this.taskId > o.taskId ? 1 : -1;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Employee getAuthor() {
        return author;
    }

    public void setAuthor(Employee author) {
        this.author = author;
    }

    public Set<Employee> getPerformers() {
        return performers;
    }

    public void setPerformers(Set<Employee> performers) {
        this.performers = performers;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public Boolean getControl() {
        return control;
    }

    public void setControl(Boolean control) {
        this.control = control;
    }

    public Boolean getExecution() {
        return execution;
    }

    public void setExecution(Boolean execution) {
        this.execution = execution;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
