package com.chan.customer.common;

public enum StatusEnum {
    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST");

    int statusCode;
    String code;

    StatusEnum(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
