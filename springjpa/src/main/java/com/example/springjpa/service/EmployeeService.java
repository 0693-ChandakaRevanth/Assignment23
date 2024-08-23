package com.example.springjpa.service;
import com.example.springjpa.dto.EmployeeDTO;
import com.example.springjpa.model.Employee;
import com.example.springjpa.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    // Create a new employee
    public EmployeeDTO createEmployee(Employee employee) {
        Employee employee_save = employeeRepository.save(employee);
        EmployeeDTO empDto = new EmployeeDTO(employee_save);
        return empDto;
    }
    // Get an employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Get all employees
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> list = employeeRepository.findAll().stream().map(e -> new EmployeeDTO(e)).toList();
        return list;
    }
    // Update an existing employee
    public EmployeeDTO updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setSalary(employeeDetails.getSalary());
        Employee employeeupdate= employeeRepository.save(employee);
        EmployeeDTO employeeDTO=new EmployeeDTO(employeeupdate);
        return employeeDTO;
    }
    // Delete an employee
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}

