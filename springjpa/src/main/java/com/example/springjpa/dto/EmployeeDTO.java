package com.example.springjpa.dto;

import com.example.springjpa.model.Employee;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private Double salary;
    public EmployeeDTO(Employee employee_save) {
        this.lastName = employee_save.getLastName();
        this.firstName = employee_save.getFirstName();
        this.email = employee_save.getEmail();
        this.salary = employee_save.getSalary();
        this.id = employee_save.getId();
        this.department = employee_save.getDepartment();
    }
}
