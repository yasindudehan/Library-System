package com.testassessment.librarysystem.controllers;


import com.testassessment.librarysystem.dto.Response;
import com.testassessment.librarysystem.dto.UserRegistration;
import com.testassessment.librarysystem.dto.UserRegistrationResponse;
import com.testassessment.librarysystem.services.interfaces.MemberService;
import com.testassessment.librarysystem.utils.exceptions.APIException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@Tag(name = "User", description = "Member Registration APIs in Library System")
public class MemberController extends BaseController {

    @Autowired
    private MemberService borrowerService;

    @Operation(summary = "Member Registration", description = "Member Registration API in Library System")
    @PostMapping("/register")
    public ResponseEntity<Response<UserRegistrationResponse>> userRegistration(@Valid @RequestBody UserRegistration userRegistration) throws APIException {
        Response<UserRegistrationResponse> response = borrowerService.userRegistration(userRegistration);
        return responseHandler(response);


    }
}
