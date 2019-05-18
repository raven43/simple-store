package com.raven43.simplestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchUserException extends NoSuchElementException {
    public NoSuchUserException() {
    }

    public NoSuchUserException(String s) {
        super(s);
    }

    public NoSuchUserException(Long id) {
        super("Can not find user with id=" + id);
    }


}
