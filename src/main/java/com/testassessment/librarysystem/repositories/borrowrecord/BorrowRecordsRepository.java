package com.testassessment.librarysystem.repositories.borrowrecord;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRecordsRepository extends JpaRepository<BorrowRecords, Integer> {
}
