package com.example.TodoListApi.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class RegistrationException {

    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;

    public RegistrationException(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }
}

