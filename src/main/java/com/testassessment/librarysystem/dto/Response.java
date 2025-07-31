package com.testassessment.librarysystem.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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


    @JsonProperty("ResponseBody")
    private T data;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("NumberOfRecords")
    private String numberOfRecords;
    @JsonProperty("TotalPages")
    private String totalPage;
    public Response(T data, String message, String code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
}
