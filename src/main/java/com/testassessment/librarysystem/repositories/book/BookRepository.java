package com.testassessment.librarysystem.repositories.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {


    @Query(value = "SELECT * FROM books WHERE is_borrowed = false LIMIT :limit OFFSET :offset", nativeQuery = true)
   Optional< List<Book> >getBooks(@Param("limit") int limit, @Param("offset") int offset);
    @Query(value = "SELECT COUNT(*) FROM books WHERE is_borrowed = false", nativeQuery = true)
    int getCount();
}
