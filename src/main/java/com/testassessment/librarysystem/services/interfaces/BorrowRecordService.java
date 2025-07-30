package com.testassessment.librarysystem.services.interfaces;

import com.testassessment.librarysystem.dto.BorrowedBookResponse;
import com.testassessment.librarysystem.dto.Response;
import com.testassessment.librarysystem.utils.exceptions.APIException;

public interface BorrowRecordService {
    Response<BorrowedBookResponse> borrowBook(int bookId, int borrowerId) throws APIException;
    Response<BorrowedBookResponse> returnBook(int id) throws APIException;
}
