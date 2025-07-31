package com.testassessment.librarysystem.utils.exceptions;


import com.testassessment.librarysystem.utils.messages.ErrorResponse;
import com.testassessment.librarysystem.utils.messages.ResponseCode;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {



         @ExceptionHandler(APIException.class)
        public ResponseEntity<ErrorResponse> handleAPIException(APIException ex) {
             System.out.println(ex);
              ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getCode());
              return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
         }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleHttpStatusCodeException(NoResourceFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse( ex.getResourcePath()+ ResponseCode.PATH_NOT_FOUND,ResponseCode.LSE_404);
        return ResponseEntity.status(404).body(errorResponse);
    }
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleHttpStatusCodeException(ResourceAccessException ex) {
        ErrorResponse errorResponse = new ErrorResponse( ex.getMessage(),ResponseCode.LSE_503);
        return ResponseEntity.status(503).body(errorResponse);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpStatusCodeException(HttpRequestMethodNotSupportedException ex) {
        ErrorResponse errorResponse = new ErrorResponse( ex.getMessage(),ResponseCode.LSE_405);
        return ResponseEntity.status(405).body(errorResponse);
    }

         @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleException(Exception ex) {
              ErrorResponse errorResponse = new ErrorResponse(ResponseCode.INTERNAL_SERVER_ERROR, ResponseCode.LSE_500);
              return ResponseEntity.status(500).body(errorResponse);
         }
}
