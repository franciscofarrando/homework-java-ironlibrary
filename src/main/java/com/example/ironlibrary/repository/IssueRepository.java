package com.example.ironlibrary.repository;

import com.example.ironlibrary.models.Issue;

import com.example.ironlibrary.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {
    List<Issue> findIssueByStudent(Student student);

    Issue findByBookIsbn(String isbn);
}