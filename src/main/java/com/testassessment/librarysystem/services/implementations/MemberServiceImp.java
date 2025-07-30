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

import java.util.Optional;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    MemberRepository memberRepository;
    @Override
    public Response<UserRegistrationResponse> userRegistration(UserRegistration userRegistration) throws APIException {
        try{
            Optional<Member> existingMember = memberRepository.findByEmail(userRegistration.getEmail());
            if(existingMember.isPresent()){
                throw new APIException(ResponseCode.EMAIL_IS_ALREADY_REGISTERED,ResponseCode.LSU_400,400);
            }else {
                Member member = new Member();
                member.setName(userRegistration.getName());
                member.setEmail(userRegistration.getEmail());
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
