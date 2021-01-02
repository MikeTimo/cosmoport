package com.space.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErrorShipParams extends RuntimeException{
    public ErrorShipParams() {}

    public ErrorShipParams(String message) {
        super(message);
    }

    public ErrorShipParams(Throwable cause) {
        super(cause);
    }

    public ErrorShipParams(String message, Throwable cause) {
        super(message, cause);
    }
}
