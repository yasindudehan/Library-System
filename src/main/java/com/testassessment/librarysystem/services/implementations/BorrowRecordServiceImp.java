package com.testassessment.librarysystem.services.implementations;


import com.testassessment.librarysystem.dto.BookRegistrationResponse;
import com.testassessment.librarysystem.dto.BorrowedBookResponse;
import com.testassessment.librarysystem.dto.Response;
import com.testassessment.librarysystem.dto.UserRegistrationResponse;
import com.testassessment.librarysystem.repositories.book.Book;
import com.testassessment.librarysystem.repositories.book.BookRepository;
import com.testassessment.librarysystem.repositories.member.Member;
import com.testassessment.librarysystem.repositories.member.MemberRepository;
import com.testassessment.librarysystem.repositories.borrowrecord.BorrowRecords;
import com.testassessment.librarysystem.repositories.borrowrecord.BorrowRecordsRepository;
import com.testassessment.librarysystem.services.interfaces.BorrowRecordService;
import com.testassessment.librarysystem.utils.exceptions.APIException;
import com.testassessment.librarysystem.utils.messages.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class BorrowRecordServiceImp implements BorrowRecordService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BorrowRecordsRepository borrowRecordsRepository;

    @Override
    public Response<BorrowedBookResponse> borrowBook(int bookId, int borrowerId) throws APIException {
        try{
            Optional<Book> book = bookRepository.findById(bookId);
            Optional<Member> member = memberRepository.findById(borrowerId);
            if(book.isPresent() && member.isPresent()){
                Book borrowedBook = book.get();
                if(borrowedBook.isIsborrowed()){
                    throw new APIException(ResponseCode.BOOK_IS_ALREADY_BORROWED, ResponseCode.LSB_400, 400);
                }else{
                    borrowedBook.setIsborrowed(true);
                    BorrowRecords borrowRecords = new BorrowRecords();
                    borrowRecords.setBook(borrowedBook);
                    borrowRecords.setMember(member.get());
                    borrowRecords.setBorrowDate(new Timestamp(System.currentTimeMillis()));
                    borrowRecords= borrowRecordsRepository.save(borrowRecords);
                    bookRepository.save(borrowedBook);
                    BorrowedBookResponse borrowedBookResponse = getBorrowedBookResponseResponse(borrowRecords);
                    Response<BorrowedBookResponse> response = new Response<>(borrowedBookResponse, ResponseCode.BOOK_IS_BORROWED_SUCCESSFULLY, ResponseCode.LSS_200);
                    return response;
                }
            }else if(member.isPresent()){
                throw new APIException(ResponseCode.BOOK_NOT_FOUND, ResponseCode.LSB_400, 400);
            }else{
                throw new APIException(ResponseCode.USER_NOT_FOUND, ResponseCode.LSU_400, 400);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Response<BorrowedBookResponse> returnBook(int id) throws APIException {
        try{
            Optional<BorrowRecords> borrowRecord = borrowRecordsRepository.findById(id);
            if(borrowRecord.isPresent()){
                if(borrowRecord.get().getBook().isIsborrowed()){
                    Book borrowedBook = borrowRecord.get().getBook();
                    borrowedBook.setIsborrowed(false);
                    borrowRecord.get().setReturnDate(new Timestamp(System.currentTimeMillis()));
                    BorrowRecords updateRecord = borrowRecordsRepository.save(borrowRecord.get());
                    bookRepository.save(borrowedBook);
                    BorrowedBookResponse borrowedBookResponse = getBorrowedBookResponseResponse(updateRecord);
                    Response<BorrowedBookResponse> response = new Response<>(borrowedBookResponse, ResponseCode.BOOK_IS_RETURNED_SUCCESSFULLY, ResponseCode.LSS_200);
                    return response;
                }else{
                    throw new APIException(ResponseCode.BOOK_IS_ALREADY_RETURNED, ResponseCode.LSB_400, 400);
                }


            }else{
                throw new APIException(ResponseCode.BORROW_RECORD_IS_NOT_FOUND,  ResponseCode.LSB_400, 400);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private static BorrowedBookResponse getBorrowedBookResponseResponse(BorrowRecords borrowRecords) {
        Book savedBook = borrowRecords.getBook();
        Member member= borrowRecords.getMember();
        BookRegistrationResponse bookRegistrationResponse = new BookRegistrationResponse(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor(), savedBook.getIsbn());
        UserRegistrationResponse userRegistrationResponse = new UserRegistrationResponse(member.getId(), member.getName(), member.getEmail());
        return new BorrowedBookResponse(borrowRecords.getId(), bookRegistrationResponse, userRegistrationResponse, borrowRecords.getBorrowDate(), borrowRecords.getReturnDate());

    }
}
