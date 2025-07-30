package com.testassessment.librarysystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistration {

    @NotBlank(message = "name is required")
    private String name;
    @Email
    @NotBlank(message = "email is required")
    private String email;

}
