package com.project.spring.exception;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Generic exception for business related errors in api class.
 */
// * Will resolve to the {@link HttpStatus#NOT_FOUND} if handled by the Spring's exception handler.
//@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Skąd mam wiedzieć!!!")
public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}
