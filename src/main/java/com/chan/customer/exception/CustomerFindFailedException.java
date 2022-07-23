package com.chan.customer.exception;

public class CustomerFindFailedException extends RuntimeException{
    public CustomerFindFailedException() {
    }

    public CustomerFindFailedException(String message) {
        super(message);
    }

    public CustomerFindFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
