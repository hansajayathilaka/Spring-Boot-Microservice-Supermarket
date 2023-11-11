package com.ead.userservice.model.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class Customer extends User {
    public Date dateOfBirth;
    public Address address;
    public List<CartProduct> cart;
    public String profilePicture;

    public List<CartProduct> getCart() {
        return cart == null ? List.of() : cart;
    }

    public void setCart(List<CartProduct> cart) {
        this.cart = cart;
    }
}
