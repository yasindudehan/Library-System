package com.testassessment.librarysystem.repositories.member;


import com.testassessment.librarysystem.repositories.borrowrecord.BorrowRecords;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @OneToMany(mappedBy = "member")
    private List<BorrowRecords> borrowRecords;
    @Column(name = "created_at")
    private Timestamp createdAt;


}
