package com.testassessment.librarysystem.services.implementations;

import com.testassessment.librarysystem.dto.BorrowedBookResponse;
import com.testassessment.librarysystem.dto.Response;
import com.testassessment.librarysystem.repositories.book.Book;
import com.testassessment.librarysystem.repositories.book.BookRepository;
import com.testassessment.librarysystem.repositories.borrowrecord.BorrowRecords;
import com.testassessment.librarysystem.repositories.borrowrecord.BorrowRecordsRepository;
import com.testassessment.librarysystem.repositories.member.Member;
import com.testassessment.librarysystem.repositories.member.MemberRepository;
import com.testassessment.librarysystem.utils.exceptions.APIException;
import com.testassessment.librarysystem.utils.messages.ResponseCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BorrowRecordServiceImpTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @InjectMocks
    private BorrowRecordServiceImp borrowRecordService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private BorrowRecordsRepository borrowRecordsRepository;
    @Test
    void borrowBook() throws APIException {
        Book book = new Book();
        book.setIsborrowed(false);
        book.setTitle("Book A");
        book.setAuthor("Author A");
        book.setIsbn("1234567890");
        book.setId(1);
        Member member = new Member();
        member.setId(1);
        member.setName("John Doe");
        member.setEmail("johndoe@example");
        BorrowRecords record = new BorrowRecords();
        record.setId(11);
        record.setBook(book);
        record.setMember(member);
        record.setBorrowDate(new Timestamp(System.currentTimeMillis()));

        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(memberRepository.findById(1)).thenReturn(Optional.of(member));
        when(borrowRecordsRepository.save(any(BorrowRecords.class))).thenReturn(record);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Response<BorrowedBookResponse> response = borrowRecordService.borrowBook(1, 1);

        assertNotNull(response);
        assertEquals("Book A", response.getData().getBook().getTitle());

    }
    @Test
    void testBorrowBook_AlreadyBorrowed() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book A");
        book.setAuthor("Author A");
        book.setIsbn("1234567890");
        book.setIsborrowed(true);
        Member member = new Member();
        member.setId(1);
        member.setName("John Doe");
        member.setEmail("johndoe@example");

        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(memberRepository.findById(1)).thenReturn(Optional.of(member));

        APIException ex = assertThrows(APIException.class, () -> borrowRecordService.borrowBook(1, 1));
        assertEquals(ResponseCode.LSB_400, ex.getCode());
    }

    @Test
    void testBorrowBook_BookNotFound() {
        Member member = new Member();
        member.setId(1);
        member.setName("John Doe");
        member.setEmail("johndoe@example");

        when(bookRepository.findById(1)).thenReturn(Optional.empty());
        when(memberRepository.findById(1)).thenReturn(Optional.of(member));

        APIException ex = assertThrows(APIException.class, () -> borrowRecordService.borrowBook(1, 1));
        assertEquals(ResponseCode.LSB_400, ex.getCode());
    }

    @Test
    void testBorrowBook_MemberNotFound() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book A");
        book.setAuthor("Author A");
        book.setIsbn("1234567890");
        book.setIsborrowed(false);

        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(memberRepository.findById(1)).thenReturn(Optional.empty());

        APIException ex = assertThrows(APIException.class, () -> borrowRecordService.borrowBook(1, 1));
        assertEquals(ResponseCode.LSU_400, ex.getCode());
    }
    @Test
    void returnBook() throws APIException {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book A");
        book.setAuthor("Author A");
        book.setIsbn("1234567890");
        book.setIsborrowed(true);
        Member member = new Member();
        member.setId(1);
        member.setName("John Doe");
        member.setEmail("johndoe@example");

        BorrowRecords record = new BorrowRecords();
        record.setId(1);
        record.setBook(book);
        record.setMember(member);
        record.setBorrowDate(new Timestamp(System.currentTimeMillis()));

        when(borrowRecordsRepository.findById(1)).thenReturn(Optional.of(record));
        when(borrowRecordsRepository.save(any())).thenReturn(record);
        when(bookRepository.save(any())).thenReturn(book);

        Response<BorrowedBookResponse> response = borrowRecordService.returnBook(1);
        assertNotNull(response);
        assertEquals("Book A", response.getData().getBook().getTitle());

    }
    @Test
    void testReturnBook_AlreadyReturned() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book A");
        book.setAuthor("Author A");
        book.setIsbn("1234567890");
        book.setIsborrowed(false);
        Member member = new Member();
        member.setId(1);
        member.setName("John Doe");
        member.setEmail("johndoe@example");

        BorrowRecords record = new BorrowRecords();
        record.setId(1);
        record.setBook(book);
        record.setMember(member);

        when(borrowRecordsRepository.findById(1)).thenReturn(Optional.of(record));

        APIException ex = assertThrows(APIException.class, () -> borrowRecordService.returnBook(1));
        assertEquals(ResponseCode.LSB_400, ex.getCode());
    }

    @Test
    void testReturnBook_RecordNotFound() {
        when(borrowRecordsRepository.findById(1)).thenReturn(Optional.empty());

        APIException ex = assertThrows(APIException.class, () -> borrowRecordService.returnBook(1));
        assertEquals(ResponseCode.LSB_400, ex.getCode());
    }
}