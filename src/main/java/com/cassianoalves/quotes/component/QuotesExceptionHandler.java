package com.cassianoalves.quotes.component;

import com.cassianoalves.quotes.exception.ComponentException;
import com.cassianoalves.quotes.model.QuotesError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class QuotesExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ComponentException.class})
    public ResponseEntity<QuotesError> componentException(HttpServletRequest req, Exception exception) {
        ComponentException componentException = (ComponentException) exception;
        QuotesError quotesError = componentException.getError();
        String message = quotesError.getMessage();
        if(componentException.getCause() != null) {
            message += " - " + componentException.getCause().getMessage();
        }
        return new ResponseEntity(new QuotesError(quotesError.getCode(), message), HttpStatus.NOT_ACCEPTABLE);
    }
}
