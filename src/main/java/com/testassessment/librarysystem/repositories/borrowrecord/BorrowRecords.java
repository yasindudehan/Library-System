package com.testassessment.librarysystem.repositories.borrowrecord;


import com.testassessment.librarysystem.repositories.book.Book;
import com.testassessment.librarysystem.repositories.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Table(name = "borrow_records")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BorrowRecords {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private Member member;
    @Column(name = "borrow_date", nullable = false)
    private Timestamp borrowDate;
    @Column(name = "return_date")
    private Timestamp returnDate;
}
