package com.cassianoalves.quotes.exception;

public class ComponentException extends RuntimeException {
    public ComponentException(String s) {
        super(s);
    }

    public ComponentException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
