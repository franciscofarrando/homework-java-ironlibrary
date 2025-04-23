package com.example.ironlibrary.repository;

import com.example.ironlibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByIsbn(String isbn);

    Book findByTitle(String title);

    Book findByCategory(String category);

    Book findByAuthorName(String authorName);

    Book findByAuthorMail(String authorMail);
}
