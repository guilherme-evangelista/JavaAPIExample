package com.dummyjson.core.exeption;

public class EmptyStringException extends RuntimeException {

    public EmptyStringException() {
        super();
    }

    public EmptyStringException(String message) {
        super(message);
    }

    public EmptyStringException(Throwable throwable) {
        super(throwable);
    }

    public EmptyStringException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
