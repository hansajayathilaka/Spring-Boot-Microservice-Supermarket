package com.ead.userservice.exception;

public class IdMismatchException extends RuntimeException {
    public IdMismatchException() {
        super("Path id and request body id does not match");
    }

    public IdMismatchException(String message) {
        super(message);
    }
}
