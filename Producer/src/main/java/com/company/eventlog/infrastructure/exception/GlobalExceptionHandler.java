package com.company.eventlog.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import com.company.eventlog.domain.exception.EventLogTypeNotFoundException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("An unexpected error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Invalid input: " + ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("A runtime error occurred: " + ex.getMessage());
    }
    /* si queremos un mensaje por bad request 400 sin mayor detalle
    @ExceptionHandler(EventLogTypeNotFoundException.class)
    public ResponseEntity<String> handleEventLogTypeNotFoundException(EventLogTypeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    */
    // Custom error response for EventLogTypeNotFoundException
    @ExceptionHandler(EventLogTypeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEventLogTypeNotFoundException(EventLogTypeNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("EVENT_LOG_TYPE_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}