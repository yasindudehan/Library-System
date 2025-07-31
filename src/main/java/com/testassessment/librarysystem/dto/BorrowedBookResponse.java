package com.testassessment.librarysystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorrowedBookResponse {
    private int id;
    @JsonProperty("BorrowedBooks")
    private BookRegistrationResponse book;
    @JsonProperty("BorrowerDetails")
    private UserRegistrationResponse borrower;
    @JsonProperty("BorrowDate")
    private Timestamp borrowDate;
    @JsonProperty("returnDate")
    private Timestamp returnDate;
}
