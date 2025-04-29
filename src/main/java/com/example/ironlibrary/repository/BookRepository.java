package com.example.ironlibrary.repository;

import com.example.ironlibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitleContaining(String title);
    List<Book> findBookByCategory(String category);
    List<Book> findBookByAuthorName(String authorName);

    Optional<Book> findBookByIsbn(String isbn);
    List<Book> findAllByIsbn(String isbn);

    Book findByIsbn(String isbn);

    Book findByTitle(String titleBook);
}
