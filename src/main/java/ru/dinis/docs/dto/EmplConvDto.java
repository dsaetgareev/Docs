package ru.dinis.docs.dto;


import ru.dinis.docs.beans.Employee;

/**
 * Create by dinis of 07.05.18.
 */
public class EmplConvDto {

    public static EmployeeDto emplToEmplDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmplId(employee.getEmplId());
        employeeDto.setSurname(employee.getSurname());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setPatronymic(employee.getPatronymic());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setSubdivision(employee.getSubdivision());
        employeeDto.setInstructions(employee.getInstructions());
        employeeDto.setMyTasks(employee.getMyTasks());
        return employeeDto;
    }

    public static Employee emplDtoToEmpl(EmployeeDto employee) {
        Employee employeeDto = new Employee();
        employeeDto.setEmplId(employee.getEmplId());
        employeeDto.setSurname(employee.getSurname());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setPatronymic(employee.getPatronymic());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setSubdivision(employee.getSubdivision());
        employeeDto.setInstructions(employee.getInstructions());
        employeeDto.setMyTasks(employee.getMyTasks());
        return employeeDto;
    }

}
