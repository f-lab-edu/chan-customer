package com.chan.customer.exception;

public class OrderFindFailedException extends RuntimeException{
    public OrderFindFailedException() {
    }

    public OrderFindFailedException(String message) {
        super(message);
    }

    public OrderFindFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
