package com.ead.userservice.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    public String street;
    public String city;
    public String state;
    public String postalCode;
    public String country;
}
