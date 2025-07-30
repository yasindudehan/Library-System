package com.testassessment.librarysystem.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRegistrationRequest {

    @NotBlank(message = "title is required")
    private String title;
    @NotNull(message = "author is required")
    private String author;
    @NotBlank(message = "isbn is required")
    private String isbn;
}
