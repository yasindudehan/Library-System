package com.testassessment.librarysystem.utils.exceptions;


import com.testassessment.librarysystem.utils.messages.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

         @ExceptionHandler(APIException.class)
        public ResponseEntity<ErrorResponse> handleAPIException(APIException ex) {
              ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getCode());
              return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
         }

         @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleException(Exception ex) {
              ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", "500");
              return ResponseEntity.status(500).body(errorResponse);
         }
}
