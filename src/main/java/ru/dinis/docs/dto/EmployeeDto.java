package ru.dinis.docs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.dinis.docs.beans.Subdivision;
import ru.dinis.docs.beans.Task;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Create by dinis of 21.04.18.
 */
@XmlRootElement
public class EmployeeDto implements Serializable, Comparable<EmployeeDto> {
    @JsonProperty(value = "emplId")
    private int emplId;
    @JsonProperty(value = "surname")
    private String surname;
    @JsonProperty(value = "firstName")
    private String firstName;
    @JsonProperty(value = "patronymic")
    private String patronymic;
    @JsonProperty(value = "position")
    private String position;
    @JsonProperty(value = "subdivision")
    private Subdivision subdivision;
    @JsonProperty(value = "instructions")
    private Set<Task> instructions = new HashSet<>();
    @JsonProperty(value = "myTasks")
    private Set<Task> myTasks = new HashSet<>();

    public EmployeeDto() {
    }

    @Override
    public int compareTo(EmployeeDto o) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ru.dinis.docs.beans.Employee)) return false;

        ru.dinis.docs.beans.Employee employee = (ru.dinis.docs.beans.Employee) o;

        if (getEmplId() != employee.getEmplId()) return false;
        if (getSurname() != null ? !getSurname().equals(employee.getSurname()) : employee.getSurname() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(employee.getFirstName()) : employee.getFirstName() != null)
            return false;
        if (getPatronymic() != null ? !getPatronymic().equals(employee.getPatronymic()) : employee.getPatronymic() != null)
            return false;
        if (getPosition() != null ? !getPosition().equals(employee.getPosition()) : employee.getPosition() != null)
            return false;
        if (getSubdivision() != null ? !getSubdivision().equals(employee.getSubdivision()) : employee.getSubdivision() != null)
            return false;
        if (getInstructions() != null ? !getInstructions().equals(employee.getInstructions()) : employee.getInstructions() != null)
            return false;
        return getMyTasks() != null ? getMyTasks().equals(employee.getMyTasks()) : employee.getMyTasks() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmplId();
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getPatronymic() != null ? getPatronymic().hashCode() : 0);
        result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
        result = 31 * result + (getSubdivision() != null ? getSubdivision().hashCode() : 0);
        result = 31 * result + (getInstructions() != null ? getInstructions().hashCode() : 0);
        result = 31 * result + (getMyTasks() != null ? getMyTasks().hashCode() : 0);
        return result;
    }
}
