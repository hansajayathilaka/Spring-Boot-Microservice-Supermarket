package com.springframework.userservice.controller;

import com.springframework.userservice.model.entity.Employee;
import com.springframework.userservice.service.employee.EmployeeService;
import com.springframework.userservice.service.employee.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable String employeeId, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employeeId, employee);
    }

    @DeleteMapping("/{employeeId}")
    public Employee deleteEmployee(@PathVariable String employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }
}
