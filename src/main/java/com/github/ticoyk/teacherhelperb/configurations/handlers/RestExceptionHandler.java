package com.github.ticoyk.teacherhelperb.configurations.handlers;

import javax.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;

import com.github.ticoyk.teacherhelperb.models.RequestError;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new RequestError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(RequestError requestError) {
        return new ResponseEntity<>(requestError, requestError.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        RequestError requestError = new RequestError(HttpStatus.NOT_FOUND);
        requestError.setMessage(ex.getMessage());
        return buildResponseEntity(requestError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintsException(ConstraintViolationException ex){
        RequestError requestError = new RequestError(HttpStatus.BAD_REQUEST);
        String message = ex.getSQLException().getMessage();
        message = message.substring(message.indexOf("Detail:"));
        requestError.setMessage(message);
        requestError.setDebugMessage(message);
        return buildResponseEntity(requestError);
    }

}

