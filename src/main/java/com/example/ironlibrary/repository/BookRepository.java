package com.example.ironlibrary.repository;

import com.example.ironlibrary.models.Author;
import com.example.ironlibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    Book findByTitle(String title);

    List<Book> findAllByCategory(String category);
    List<Book> findAllByAuthor(Author author);
}
