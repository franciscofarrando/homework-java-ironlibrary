package com.example.ironlibrary.repository;

import com.example.ironlibrary.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Integer> {
}
