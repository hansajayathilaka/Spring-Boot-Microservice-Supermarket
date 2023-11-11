package com.springframework.userservice.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "employees")
public class Employee extends User {
    public EmployeeRole role;
}
