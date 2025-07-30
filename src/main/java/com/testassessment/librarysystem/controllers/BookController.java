package com.testassessment.librarysystem.controllers;

import com.testassessment.librarysystem.dto.BookRegistrationRequest;
import com.testassessment.librarysystem.dto.BookRegistrationResponse;

import com.testassessment.librarysystem.dto.Response;
import com.testassessment.librarysystem.services.interfaces.BookService;
import com.testassessment.librarysystem.utils.exceptions.APIException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController extends BaseController {


    @Autowired
    private BookService bookService;

    @PostMapping("/register")
    public ResponseEntity<Response<BookRegistrationResponse>> bookRegistration(@Valid @RequestBody BookRegistrationRequest bookRegistrationRequest) throws APIException {
        Response<BookRegistrationResponse> response = bookService.bookRegistration(bookRegistrationRequest);
        return responseHandler(response);
    }
    @GetMapping("/all-available-books")
    public ResponseEntity<Response<List<BookRegistrationResponse>>> getAllBooks(@RequestParam("page") int page, @RequestParam("size") int size) throws APIException {
        Response<List<BookRegistrationResponse>> response = bookService.getAllBooks(page, size);
        return responseHandler(response);
    }

}
