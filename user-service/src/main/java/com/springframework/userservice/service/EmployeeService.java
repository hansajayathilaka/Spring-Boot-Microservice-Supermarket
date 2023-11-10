package com.springframework.userservice.service;

import com.springframework.userservice.exception.DeletedUserException;
import com.springframework.userservice.exception.IdMismatchException;
import com.springframework.userservice.exception.UserDoesNotExistException;
import com.springframework.userservice.model.entity.Employee;
import com.springframework.userservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee getEmployeeById(String id) {
        Employee employee = repository.findById(id).orElseThrow(UserDoesNotExistException::new);

        if (employee.isDeleted()) {
            throw new DeletedUserException();
        }

        return employee;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = repository.findAll();

        return employees.stream().filter(employee -> !employee.isDeleted()).toList();
    }

    public Employee addEmployee(Employee employee) {
        employee.setCreated(new Date());
        return repository.save(employee);
    }

    public Employee updateEmployee(String employeeId, Employee employee) {
        if (!employeeId.equals(employee.getId())) {
            throw new IdMismatchException();
        }

        Employee oldEmployee = getEmployeeById(employeeId);
        oldEmployee.updateFields(employee);

        oldEmployee.setRole(employee.getRole() != null ? employee.getRole() : oldEmployee.getRole());

        return repository.save(oldEmployee);
    }

    public Employee deleteEmployee(String employeeId) {
        Employee employee = getEmployeeById(employeeId);

        if(!employee.isDeleted()) {
            employee.setDeleted(true);
            repository.save(employee);
        }

        return employee;
    }
}
