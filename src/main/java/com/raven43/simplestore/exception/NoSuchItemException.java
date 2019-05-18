package com.raven43.simplestore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class NoSuchItemException extends NoSuchElementException {
    public NoSuchItemException() {
    }

    public NoSuchItemException(String s) {
        super(s);
    }

    public NoSuchItemException(Long id) {
        super("Can not find item with id="+id);
    }


}
