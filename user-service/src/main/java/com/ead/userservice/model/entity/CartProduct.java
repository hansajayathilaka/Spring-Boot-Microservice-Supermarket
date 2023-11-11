package com.ead.userservice.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CartProduct {
    public String id;
    @Setter
    public int quantity;
}
