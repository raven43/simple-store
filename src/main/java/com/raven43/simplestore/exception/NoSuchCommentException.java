package com.raven43.simplestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchCommentException extends NoSuchElementException {

    public NoSuchCommentException() {
        super();
    }

    public NoSuchCommentException(String s) {
        super(s);
    }

    public NoSuchCommentException(Long id) {
        super("Can not find comment "+id);
    }
}
