package com.testassessment.librarysystem.services.implementations;

import com.testassessment.librarysystem.dto.*;
import com.testassessment.librarysystem.repositories.book.Book;
import com.testassessment.librarysystem.repositories.book.BookRepository;
import com.testassessment.librarysystem.services.interfaces.BookService;
import com.testassessment.librarysystem.utils.exceptions.APIException;
import com.testassessment.librarysystem.utils.messages.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    BookRepository bookRepository;


    @Override
    public Response<BookRegistrationResponse> bookRegistration(BookRegistrationRequest bookRegistrationRequest) throws APIException {

        try {
            Book book = new Book();
            book.setIsbn(bookRegistrationRequest.getIsbn());
            book.setAuthor(bookRegistrationRequest.getAuthor());
            book.setTitle(bookRegistrationRequest.getTitle());
            book.setIsborrowed(false);
            Book savedBook = bookRepository.save(book);
            BookRegistrationResponse bookRegistrationResponse = new BookRegistrationResponse(savedBook.getId(), savedBook.getTitle(), savedBook.getAuthor(), savedBook.getIsbn());
            return new Response<>(bookRegistrationResponse, ResponseCode.BOOK_IS_REGISTERED_SUCCESSFULLY, ResponseCode.LSS_200);


         }
        catch (Exception e) {
            throw new APIException(e.getMessage(), ResponseCode.LSE_500, 500);
        }
    }

    @Override
    public Response<List<BookRegistrationResponse>> getAllBooks(int page, int size) throws APIException {
        try {
            int offset = (page - 1) * size;
            Optional<List<Book>> books = bookRepository.getBooks(size, offset);
            int count =bookRepository.getCount();
            int totalPage = (int) Math.ceil((double) count / size);
            if (books.isPresent()) {
                List<BookRegistrationResponse> bookRegistrationResponses = books.get().stream().map(book -> new BookRegistrationResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn())).toList();
                return  new Response<>(bookRegistrationResponses, ResponseCode.BOOKS_ARE_RETRIEVED_SUCCESSFULLY, ResponseCode.LSS_200, String.valueOf(count), String.valueOf(totalPage));

            } else {
                throw new APIException(ResponseCode.NO_ANY_AVAILABLE_BOOKS, ResponseCode.LSB_400, 400);
            }

        } catch (APIException e) {
            throw e;
        } catch (Exception e) {
               throw new APIException(ResponseCode.INTERNAL_SERVER_ERROR, ResponseCode.LSE_500, 500);
        }

    }


}
