package com.testassessment.librarysystem.controllers;

import com.testassessment.librarysystem.dto.BorrowedBookResponse;
import com.testassessment.librarysystem.dto.Response;
import com.testassessment.librarysystem.services.interfaces.BorrowRecordService;
import com.testassessment.librarysystem.utils.exceptions.APIException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/borrowedRecord")
@Tag(name = "Borrow and Return Book", description = "Borrowed Record API in Library System")
public class BorrowedRecordController extends BaseController {


    @Autowired
    private BorrowRecordService borrowedRecordService;

    @Operation(summary = "Borrow Book", description = "Borrow Book API in Library System")
    @PostMapping("/borrowBook")
    public ResponseEntity<Response<BorrowedBookResponse>> borrowBook(@RequestParam("bookId") int bookId, @RequestParam("borrowerId") int borrowerId) throws APIException {
        Response<BorrowedBookResponse> response = borrowedRecordService.borrowBook(bookId, borrowerId);
        return responseHandler(response);

    }
    @Operation(summary = "Return Book", description = "Return Book API in Library System")
    @PutMapping("/returnBook/{id}")
    public ResponseEntity<Response<BorrowedBookResponse>> returnBook(@PathVariable("id") int id) throws APIException {
        Response<BorrowedBookResponse> response = borrowedRecordService.returnBook(id);
        return responseHandler(response);
    }
}