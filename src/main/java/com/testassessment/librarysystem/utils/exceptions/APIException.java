package com.testassessment.librarysystem.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class APIException extends Exception {

    private String code;
    private String message;
    private int statusCode;
    public APIException(String message, String code, int statusCode) {
        super(message);
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

}
