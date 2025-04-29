package com.example.ironlibrary.repository;

import com.example.ironlibrary.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findStudentByUsnAndName(String usn, String name);
    Optional<Student> findStudentByUsn(String usn);

}