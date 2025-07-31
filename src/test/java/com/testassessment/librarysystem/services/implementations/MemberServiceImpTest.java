package com.testassessment.librarysystem.services.implementations;

import com.testassessment.librarysystem.dto.Response;
import com.testassessment.librarysystem.dto.UserRegistration;
import com.testassessment.librarysystem.dto.UserRegistrationResponse;
import com.testassessment.librarysystem.repositories.member.Member;
import com.testassessment.librarysystem.repositories.member.MemberRepository;
import com.testassessment.librarysystem.utils.exceptions.APIException;
import com.testassessment.librarysystem.utils.messages.ResponseCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MemberServiceImpTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @InjectMocks
    private MemberServiceImp memberService;

    @Mock
    private MemberRepository memberRepository;
    @Test
    void userRegistration() throws APIException {
        UserRegistration registration = new UserRegistration();
        registration.setName("Alice");
        registration.setEmail("alice@example.com");

        when(memberRepository.findByEmail("alice@example.com")).thenReturn(Optional.empty());

        Member savedMember = new Member();
        savedMember.setId(1);
        savedMember.setName("Alice");
        savedMember.setEmail("alice@example.com");

        when(memberRepository.save(any(Member.class))).thenReturn(savedMember);

        Response<UserRegistrationResponse> response = memberService.userRegistration(registration);

        assertNotNull(response);
        assertEquals("Alice", response.getData().getName());
        assertEquals("alice@example.com", response.getData().getEmail());

    }

    @Test
    void testUserRegistration_EmailAlreadyExists() {
        UserRegistration registration = new UserRegistration();
        registration.setName("Bob");
        registration.setEmail("bob@example.com");

        Member existingMember = new Member();
        existingMember.setId(2);
        existingMember.setName("Bob");
        existingMember.setEmail("bob@example.com");

        when(memberRepository.findByEmail("bob@example.com")).thenReturn(Optional.of(existingMember));

        APIException exception = assertThrows(APIException.class, () ->
                memberService.userRegistration(registration));

        assertEquals(ResponseCode.LSU_400, exception.getCode());

    }
    @Test
    void testUserRegistration_RepositoryException() {
        UserRegistration registration = new UserRegistration();
        registration.setName("Charlie");
        registration.setEmail("charlie@example.com");

        when(memberRepository.findByEmail("charlie@example.com")).thenThrow(new RuntimeException("DB Error"));

        assertThrows(RuntimeException.class, () -> memberService.userRegistration(registration));
    }
}