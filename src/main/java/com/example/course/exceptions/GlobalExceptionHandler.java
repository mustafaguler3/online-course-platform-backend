package com.example.course.exceptions;

import com.example.course.dto.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Access Denied");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex,WebRequest request) {

        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse .setTimestamp(new Timestamp(System.currentTimeMillis() *10 *60).toLocalDateTime());
        errorResponse .setPath(request.getDescription(false));
        errorResponse .setMessage(ex.getMessage());
        errorResponse .setTrace(ex.getStackTrace().toString());

        if (ex.getStackTrace().length > 0){
            StackTraceElement element = ex.getStackTrace()[0];
            log.error("Exception occurred in class: {}, method: {}, line: {}",
                    element.getClassName(), element.getMethodName(), element.getLineNumber());
        }

        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex,WebRequest request) {

        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse .setTimestamp(new Timestamp(System.currentTimeMillis() *10 *60).toLocalDateTime());
        errorResponse .setPath(request.getDescription(false));
        errorResponse .setMessage(ex.getMessage());
        errorResponse .setTrace(ex.getStackTrace().toString());
        if (ex.getStackTrace().length > 0){
            StackTraceElement element = ex.getStackTrace()[0];
            log.error("Exception occurred in class: {}, method: {}, line: {}",
                    element.getClassName(), element.getMethodName(), element.getLineNumber());
        }

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<?> handleUserDisabledException(UserDisabledException ex,WebRequest request) {

        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse .setTimestamp(new Timestamp(System.currentTimeMillis() *10 *60).toLocalDateTime());
        errorResponse .setPath(request.getDescription(false));
        errorResponse .setMessage(ex.getMessage());
        errorResponse .setTrace(ex.getStackTrace().toString());
        if (ex.getStackTrace().length > 0){
            StackTraceElement element = ex.getStackTrace()[0];
            log.error("Exception occurred in class: {}, method: {}, line: {}",
                    element.getClassName(), element.getMethodName(), element.getLineNumber());
        }

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StackOverflowError.class)
    public ResponseEntity<ErrorResponseDto> handleStackOverflowError(StackOverflowError ex, WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse .setTimestamp(new Timestamp(System.currentTimeMillis() *10 *60).toLocalDateTime());
        errorResponse .setPath(request.getDescription(false));
        errorResponse .setMessage(ex.getMessage());
        errorResponse .setTrace(ex.getStackTrace().toString());
        if (ex.getStackTrace().length > 0){
            StackTraceElement element = ex.getStackTrace()[0];
            log.error("Exception occurred in class: {}, method: {}, line: {}",
                    element.getClassName(), element.getMethodName(), element.getLineNumber());
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    // Validations Error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", errors);

        if (ex.getStackTrace().length > 0){
            StackTraceElement element = ex.getStackTrace()[0];
            log.error("Exception occurred in class: {}, method: {}, line: {}",
                    element.getClassName(), element.getMethodName(), element.getLineNumber());
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound(ResourceNotFoundException ex,WebRequest request) {
        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse .setTimestamp(new Timestamp(System.currentTimeMillis() *10 *60).toLocalDateTime());
        errorResponse .setPath(request.getDescription(false));
        errorResponse .setMessage(ex.getMessage());
        errorResponse .setTrace(ex.getStackTrace().toString());

        if (ex.getStackTrace().length > 0){
            StackTraceElement element = ex.getStackTrace()[0];
            log.error("Exception occurred in class: {}, method: {}, line: {}",
                    element.getClassName(), element.getMethodName(), element.getLineNumber());
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
