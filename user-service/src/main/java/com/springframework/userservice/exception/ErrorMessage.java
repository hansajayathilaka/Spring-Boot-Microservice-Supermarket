package com.springframework.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private String status;
    private Date timestamp;
    private String message;
    private String path;
}
