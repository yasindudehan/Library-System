package com.testassessment.librarysystem.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response <T>{
    private T data;
    private String message;
    private String code;
    private String numberofRecords;
    private String totalPage;
    public Response(T data, String message, String code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
}
