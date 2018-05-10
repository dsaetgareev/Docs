package ru.dinis.docs.dto;

import ru.dinis.docs.beans.Employee;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * Create by dinis of 07.05.18.
 */
public class TaskDto {


        private int taskId;

        private String subject;

        private Employee author;

        private Set<Employee> performers = new TreeSet<>();

        private Date period;

        private Boolean control;

        private Boolean execution;

        private String descr;

        public TaskDto() {
        }

        public int compareTo(TaskDto o) {
            return this.taskId > o.taskId ? 1 : -1;
        }

        public String fullName() {
            return this.author.getSurname() + " " + this.author.getFirstName() + " " + this.author.getPatronymic();
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

