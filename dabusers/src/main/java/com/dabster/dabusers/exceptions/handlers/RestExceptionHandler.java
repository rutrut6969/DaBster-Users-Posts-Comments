package com.dabster.dabusers.exceptions.handlers;


import com.dabster.dabusers.exceptions.customexceptions.ResourceFoundException;
import com.dabster.dabusers.exceptions.customexceptions.ResourceNotFoundException;
import com.dabster.dabusers.exceptions.errordetailsmodels.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    HelperFunctions helper;

    public RestExceptionHandler() {
        super();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe) {

        ErrorDetails errorDetail = new ErrorDetails();
        errorDetail.setTimestamp(new Date());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDevelopermessage(rnfe.getClass().getName());
        errorDetail.setErrors(helper.getConstraintViolation(rnfe));

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(ResourceFoundException.class)
    public ResponseEntity<?> handleResourceFoundException(ResourceFoundException rfe) {

        ErrorDetails errorDetail = new ErrorDetails();
        errorDetail.setTimestamp(new Date());
        errorDetail.setStatus(HttpStatus.FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(rfe.getMessage());
        errorDetail.setDevelopermessage(rfe.getClass().getName());
        errorDetail.setErrors(helper.getConstraintViolation(rfe));

        return new ResponseEntity<>(errorDetail, null, HttpStatus.FOUND);

    }


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetails errorDetail = new ErrorDetails();
        errorDetail.setTimestamp(new Date());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Rest Internal Exception");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDevelopermessage(ex.getClass().getName());
        errorDetail.setErrors(helper.getConstraintViolation(ex));


        return new ResponseEntity<>(errorDetail, null, status);
    }
}
