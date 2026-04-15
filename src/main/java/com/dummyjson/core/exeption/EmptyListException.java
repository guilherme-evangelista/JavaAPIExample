package com.dummyjson.core.exeption;

public class EmptyListException extends RuntimeException {

    public EmptyListException() {
        super();
    }

    public EmptyListException(String message) {
        super(message);
    }

    public EmptyListException(Throwable throwable) {
        super(throwable);
    }

    public EmptyListException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
