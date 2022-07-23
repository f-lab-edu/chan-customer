package com.chan.customer.exception;

public class OrderRequestValidationFailedException extends RuntimeException{
    public OrderRequestValidationFailedException() {
    }

    public OrderRequestValidationFailedException(String message) {
        super(message);
    }

    public OrderRequestValidationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
