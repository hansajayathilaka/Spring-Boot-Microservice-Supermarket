package com.springframework.userservice.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@NoArgsConstructor
@Document(collection = "users")
public class User extends BaseEntity {
    @Id
    public String id;
    @Setter
    public String email;
    @Setter
    public String firstName;
    @Setter
    public String lastName;
    @Setter
    public String phoneNumber;

    public void updateFields(User newUser) {
        this.setEmail(newUser.getEmail() != null ? newUser.getEmail() : this.getEmail());
        this.setFirstName(newUser.getFirstName() != null ? newUser.getFirstName() : this.getFirstName());
        this.setLastName(newUser.getLastName() != null ? newUser.getLastName() : this.getLastName());
        this.setPhoneNumber(newUser.getPhoneNumber() != null ? newUser.getPhoneNumber() : this.getPhoneNumber());
        this.setUpdated(new Date());
    }
}
