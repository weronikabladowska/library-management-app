package pl.sda.librarymanagementapp.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler (BadBoundaryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void badBoundryException(RuntimeException exception){
        log.error(exception.getMessage());
    }
}
