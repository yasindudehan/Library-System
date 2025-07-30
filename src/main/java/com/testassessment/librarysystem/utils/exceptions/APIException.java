package com.testassessment.librarysystem.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIException extends Exception {

    private String code;
    private String message;
    private int statusCode;

}
