package com.testassessment.librarysystem.services.implementations;

import com.testassessment.librarysystem.dto.BookRegistrationRequest;
import com.testassessment.librarysystem.dto.BookRegistrationResponse;
import com.testassessment.librarysystem.dto.Response;
import com.testassessment.librarysystem.repositories.book.Book;
import com.testassessment.librarysystem.repositories.book.BookRepository;
import com.testassessment.librarysystem.utils.exceptions.APIException;
import com.testassessment.librarysystem.utils.messages.ResponseCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class BookServiceImpTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private BookServiceImp bookService;
    @Mock
    private BookRepository bookRepository;
    @Test
    void bookRegistration() throws APIException {
        BookRegistrationRequest request = new BookRegistrationRequest();
        request.setTitle("Test Book");
        request.setAuthor("John Doe");
        request.setIsbn("1234567890");

        Book book = new Book();
        book.setId(1);
        book.setTitle("Test Book");
        book.setAuthor("John Doe");
        book.setIsbn("1234567890");

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Response<BookRegistrationResponse> response = bookService.bookRegistration(request);

        assertNotNull(response);
        assertEquals("Test Book", response.getData().getTitle());

    }

    @Test
    void testBookRegistration_Exception() {
        BookRegistrationRequest request = new BookRegistrationRequest();
        request.setTitle("Test Book");
        request.setAuthor("John Doe");
        request.setIsbn("1234567890");

        when(bookRepository.save(any(Book.class))).thenThrow(new RuntimeException("DB error"));

        APIException exception = assertThrows(APIException.class, () -> bookService.bookRegistration(request));

        assertEquals(ResponseCode.LSE_500, exception.getCode());
    }


    @Test
    void getAllBooks() throws APIException {
        List<Book> books = List.of(new Book());
        when(bookRepository.getBooks(10, 0)).thenReturn(Optional.of(books));
        when(bookRepository.getCount()).thenReturn(1);

        Response<List<BookRegistrationResponse>> response = bookService.getAllBooks(1, 10);

        assertNotNull(response);
        assertEquals(1, response.getData().size());

    }
    @Test
    void testGetAllBooks_NoBooks() {
        when(bookRepository.getBooks(10, 0)).thenReturn(Optional.empty());

        APIException exception = assertThrows(APIException.class, () -> bookService.getAllBooks(1, 10));

        assertEquals(ResponseCode.LSB_400, exception.getCode());
    }

    @Test
    void testGetAllBooks_RepositoryException() {
        when(bookRepository.getBooks(anyInt(), anyInt())).thenThrow(new RuntimeException());

        APIException exception = assertThrows(APIException.class, () -> bookService.getAllBooks(1, 10));

        assertEquals(ResponseCode.LSE_500, exception.getCode());
    }
}