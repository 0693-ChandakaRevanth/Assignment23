package com.example.springjpa.controller;
import com.example.springjpa.dto.EmployeeDTO;
import com.example.springjpa.model.Employee;
import com.example.springjpa.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    // Create a new employee
    @PostMapping("/")
    public EmployeeDTO createEmployee(@RequestBody Employee employee) {
        //log.info("Controller");
        return employeeService.createEmployee(employee);
    }
    // Get an employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id).orElseGet(()-> new Employee());
        EmployeeDTO employeeDTO = new EmployeeDTO(employee);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }
    @GetMapping("/")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        try {
            EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
            return ResponseEntity.ok(updatedEmployee);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
