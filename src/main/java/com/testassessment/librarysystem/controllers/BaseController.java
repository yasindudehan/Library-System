package com.testassessment.librarysystem.controllers;

import com.testassessment.librarysystem.dto.Response;

import org.springframework.http.ResponseEntity;

public class BaseController {

    public <T> ResponseEntity<Response<T>> responseHandler(Response<T> response) {

        return ResponseEntity.status(200).body(response);

    }
}
