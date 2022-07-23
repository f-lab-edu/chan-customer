package com.chan.customer.handler;

import com.chan.customer.common.Message;
import com.chan.customer.common.StatusEnum;
import com.chan.customer.exception.CustomerFindFailedException;
import com.chan.customer.exception.OrderRequestValidationFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerFindFailedException.class)
    public ResponseEntity<Message> handleCustomerFindFailException(CustomerFindFailedException ex){
        return responseBadRequest(ex.getMessage());
    }

    @ExceptionHandler(OrderRequestValidationFailedException.class)
    public ResponseEntity<Message> handleOrderRequestValidationFailException(OrderRequestValidationFailedException ex){
        return responseBadRequest(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Message> handleException(Exception ex){
        return responseBadRequest(ex.getMessage());
    }

    private ResponseEntity responseBadRequest(String messages) {
        Message message = new Message();
        message.setStatus(StatusEnum.BAD_REQUEST);
        message.setMessage(messages);
        return ResponseEntity.badRequest().body(message);
    }

}
