package com.github.ticoyk.teacherhelperb.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.http.HttpStatus;

// Could be a Entity, but I don't want. 
// Would be unnecessary for the app purpose
public class RequestError {

    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;

    private String debugMessage;

    private RequestError() {
        timestamp = LocalDateTime.now();
    }

    public RequestError(HttpStatus status) {
        this();
        this.status = status;
    }

    public RequestError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public RequestError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnore
    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }
   
}