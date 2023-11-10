package com.springframework.userservice.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {
    public Date created;
    public Date updated;
    public boolean deleted;
}
