package com.ead.userservice.exception;

public class DeletedUserException extends RuntimeException {
    public DeletedUserException() {
        super("User is deleted");
    }

    public DeletedUserException(String message) {
        super(message);
    }
}
