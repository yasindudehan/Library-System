package com.testassessment.librarysystem.controllers;

import com.testassessment.librarysystem.dto.Response;
import org.springframework.http.ResponseEntity;

public class BaseController {

      public <T> ResponseEntity<T> responseHandler(Response<T> response) {
             switch (response.getCode()) {
                 case 200:
                     return ResponseEntity.ok(response.getData());
                 case 400:
                     return ResponseEntity.badRequest().body(response.getData());
                 case 404:
                     return ResponseEntity.notFound().build();
                 default:
                     return ResponseEntity.internalServerError().build();
             }
      }
}
