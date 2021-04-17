package br.com.microservice.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerBusinessException.class)
    public ResponseEntity<CustomErrorResponse> customHandleNotFound(CustomerBusinessException ex, WebRequest request) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setError(ex.getMsg());
        errors.setStatus(ex.getStatus());

        return new ResponseEntity<>(errors, HttpStatus.valueOf(errors.getStatus()));

    }
}
