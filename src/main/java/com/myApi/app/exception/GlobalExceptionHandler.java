package com.myApi.app.exception;

import java.util.List;
import java.util.ListIterator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.myApi.app.dto.ExceptionDTO;

@ControllerAdvice // Essa classe controla as excessos que ocerrem no programa...
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionDTO> handleThrowable(Throwable throwable) {

        String message = throwable.getMessage();
        throwable.printStackTrace();

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if(throwable instanceof BadCredentialsException) {
            httpStatus = HttpStatus.FORBIDDEN;
        }
        if(throwable instanceof BusinessException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        if(throwable instanceof MethodArgumentNotValidException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            message = buildMessageBeanValidation(throwable);
        }

        ExceptionDTO exceptionDTO = new ExceptionDTO(message);
        return new ResponseEntity<ExceptionDTO>(exceptionDTO, httpStatus);
    }

    private String buildMessageBeanValidation(Throwable throwable) {
        String message = "";
        MethodArgumentNotValidException argumentNotValidException = (MethodArgumentNotValidException)throwable;

        List<ObjectError> allErrors = argumentNotValidException.getAllErrors();
        ListIterator<ObjectError> errorIterator = allErrors.listIterator();

        while(errorIterator.hasNext()) {
            ObjectError error = errorIterator.next();
            message += error.getDefaultMessage();
            if(errorIterator.hasNext()) {
                message += " | ";
            }
        }
        return message;
    }

}
