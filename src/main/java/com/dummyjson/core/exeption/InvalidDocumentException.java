package com.dummyjson.core.exeption;

public class InvalidDocumentException extends RuntimeException {

    public InvalidDocumentException() {

    }

    public InvalidDocumentException(String message) {
        super(message);
    }

    public InvalidDocumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDocumentException(Throwable cause) {
        super(cause);
    }
}
