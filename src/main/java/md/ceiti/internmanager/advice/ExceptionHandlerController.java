package md.ceiti.internmanager.advice;

import md.ceiti.internmanager.exception.AlreadyExistsException;
import md.ceiti.internmanager.exception.NotFoundException;
import md.ceiti.internmanager.util.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ExceptionResponse> handleException(RuntimeException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), new Date().getTime()),
                HttpStatus.BAD_REQUEST
        );
    }


    @ExceptionHandler(AlreadyExistsException.class)
    private ResponseEntity<ExceptionResponse> handleException(AlreadyExistsException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), new Date().getTime()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionResponse> handleException(NotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), new Date().getTime()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    private ResponseEntity<ExceptionResponse> handleException(MissingServletRequestParameterException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(e.getMessage(), new Date().getTime()),
                HttpStatus.BAD_REQUEST
        );
    }
}
