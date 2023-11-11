package com.springframework.userservice.exception;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException() {
        super("Invalid quantity");
    }

    public InvalidQuantityException(String message) {
        super(message);
    }
}
