package com.raven43.simplestore.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
