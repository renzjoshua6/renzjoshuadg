package com.renz.hw1.service;

public class InvalidUserFieldRegistrationException extends Exception{
    public InvalidUserFieldRegistrationException() {
    }

    public InvalidUserFieldRegistrationException(String message) {
        super(message);
    }

    public InvalidUserFieldRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserFieldRegistrationException(Throwable cause) {
        super(cause);
    }

    public InvalidUserFieldRegistrationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
