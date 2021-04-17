package br.com.microservice.customer.exception;

import lombok.Data;

@Data
public class CustomerBusinessException extends RuntimeException {
    String msg;
    int status;

    public CustomerBusinessException(String msg, int status) {
        this.msg = msg;
        this.status = status;
    }
}