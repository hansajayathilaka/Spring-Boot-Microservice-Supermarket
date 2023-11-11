package com.ead.userservice.service.employee;

import com.ead.userservice.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(String id);

    List<Employee> getAllEmployees();

    Employee addEmployee(Employee employee);

    Employee updateEmployee(String employeeId, Employee employee);

    Employee deleteEmployee(String employeeId);
}
