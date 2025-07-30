package com.testassessment.librarysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRegistrationResponse {
    private int id;
    private String title;
    private String author;
    private String isbn;
}
