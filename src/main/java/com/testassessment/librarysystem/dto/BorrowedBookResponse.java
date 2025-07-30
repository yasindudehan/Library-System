package com.testassessment.librarysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BorrowedBookResponse {
    private int id;
    private BookRegistrationResponse book;
    private UserRegistrationResponse borrower;
    private Timestamp borrowDate;
    private Timestamp returnDate;
}
