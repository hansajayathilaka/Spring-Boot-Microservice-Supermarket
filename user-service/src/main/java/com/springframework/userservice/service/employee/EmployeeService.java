package com.springframework.userservice.service.employee;

import com.springframework.userservice.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(String id);

    List<Employee> getAllEmployees();

    Employee addEmployee(Employee employee);

    Employee updateEmployee(String employeeId, Employee employee);

    Employee deleteEmployee(String employeeId);
}
