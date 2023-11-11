package com.springframework.userservice.exception;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
        super("User does not exist");
    }

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
