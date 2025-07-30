package com.testassessment.librarysystem.services.interfaces;

import com.testassessment.librarysystem.dto.Response;
import com.testassessment.librarysystem.dto.UserRegistration;
import com.testassessment.librarysystem.dto.UserRegistrationResponse;
import com.testassessment.librarysystem.utils.exceptions.APIException;

public interface MemberService {
    Response<UserRegistrationResponse> userRegistration(UserRegistration userRegistration) throws APIException;
}
