package com.we2seek.restful_web_services.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(getErrorDetails(status.value(), ex.getMessage(), request), status);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(getErrorDetails(status.value(), ex.getMessage(), request), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode httpStatusCode,
            WebRequest request
    ) {
        String text = ex.getFieldError() != null ? ex.getFieldError().getDefaultMessage() : ex.getMessage();
        int errorCount = ex.getErrorCount();
        String message;
        if (errorCount > 1) {
            message = String.format("Total errors: %d First error: %s", errorCount, text);
        } else {
            message = text;
        }
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity(getErrorDetails(httpStatusCode.value(), message, request), status);
    }

    private ErrorDetails getErrorDetails(int status, String message, WebRequest request) {
        return new ErrorDetails(LocalDateTime.now(), status, message, request.getDescription(false));
    }
}
