package com.testassessment.librarysystem.controllers;

import com.testassessment.librarysystem.dto.BookRegistrationRequest;
import com.testassessment.librarysystem.dto.BookRegistrationResponse;

import com.testassessment.librarysystem.dto.Response;
import com.testassessment.librarysystem.services.interfaces.BookService;
import com.testassessment.librarysystem.utils.exceptions.APIException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "Book", description = "Book Management API in Library System")
public class BookController extends BaseController {


    @Autowired
    private BookService bookService;

    @Operation(summary = "Book Registration", description = "Book Registration API in Library System")
    @PostMapping("/register")
    public ResponseEntity<Response<BookRegistrationResponse>> bookRegistration(@Valid @RequestBody BookRegistrationRequest bookRegistrationRequest) throws APIException {
        Response<BookRegistrationResponse> response = bookService.bookRegistration(bookRegistrationRequest);
        return responseHandler(response);
    }
    @Operation(summary = "Retrieve all available books", description = "Retrieve all available books API in Library System")
    @GetMapping("/all-available-books")
    public ResponseEntity<Response<List<BookRegistrationResponse>>> getAllBooks(@RequestParam("page") int page, @RequestParam("size") int size) throws APIException {
        Response<List<BookRegistrationResponse>> response = bookService.getAllBooks(page, size);
        return responseHandler(response);
    }

}
