package com.testassessment.librarysystem.repositories.book;


import com.testassessment.librarysystem.repositories.borrowrecord.BorrowRecords;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @OneToMany(mappedBy = "book")
    private List<BorrowRecords> borrowRecords;
    @Column(name = "is_borrowed", nullable = false)
    private boolean isborrowed;
    @Column(name = "isbn", nullable = false)
    private String isbn;
    @Column(name = "created_at")
    private Timestamp createdAt;

}
