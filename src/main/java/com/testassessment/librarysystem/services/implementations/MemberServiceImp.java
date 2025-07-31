package com.testassessment.librarysystem.services.implementations;


import com.testassessment.librarysystem.dto.Response;
import com.testassessment.librarysystem.dto.UserRegistration;
import com.testassessment.librarysystem.dto.UserRegistrationResponse;
import com.testassessment.librarysystem.repositories.member.Member;
import com.testassessment.librarysystem.repositories.member.MemberRepository;
import com.testassessment.librarysystem.services.interfaces.MemberService;
import com.testassessment.librarysystem.utils.exceptions.APIException;
import com.testassessment.librarysystem.utils.messages.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    MemberRepository memberRepository;
    @Override
    public Response<UserRegistrationResponse> userRegistration(UserRegistration userRegistration) throws APIException {
        try{
            /**
             * assume email is unique and name can be duplicated as well.
             * step 1:firstly we check the email is already registered or not
             * step 2:if email is already registered then throw a Custom Exception
             * step 3:else we save the book object and return the response.
             * */
            Optional<Member> existingMember = memberRepository.findByEmail(userRegistration.getEmail());
            if(existingMember.isPresent()){
                throw new APIException(ResponseCode.EMAIL_IS_ALREADY_REGISTERED,ResponseCode.LSU_400,400);
            }else {
                Member member = new Member();
                member.setName(userRegistration.getName());
                member.setEmail(userRegistration.getEmail());
                member.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                member = memberRepository.save(member);
                UserRegistrationResponse response = new UserRegistrationResponse();
                response.setId(member.getId());
                response.setName(member.getName());
                response.setEmail(member.getEmail());
                return new Response<>(response, ResponseCode.USER_IS_REGISTERED_SUCCESSFULLY, ResponseCode.LSS_200);
            }
        } catch (Exception e) {
            throw e;
        }

    }
}
