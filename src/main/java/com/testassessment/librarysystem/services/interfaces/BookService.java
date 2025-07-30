package com.testassessment.librarysystem.services.interfaces;

import com.testassessment.librarysystem.dto.BookRegistrationRequest;
import com.testassessment.librarysystem.dto.BookRegistrationResponse;
import com.testassessment.librarysystem.dto.BorrowedBookResponse;
import com.testassessment.librarysystem.dto.Response;
import com.testassessment.librarysystem.utils.exceptions.APIException;

import java.util.List;

public interface BookService {

    Response<BookRegistrationResponse> bookRegistration(BookRegistrationRequest bookRegistrationRequest) throws APIException;
    Response<List<BookRegistrationResponse>> getAllBooks(int page, int size) throws APIException;
}
