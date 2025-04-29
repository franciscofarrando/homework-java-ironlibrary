package com.example.ironlibrary;

import com.example.ironlibrary.models.Author;
import com.example.ironlibrary.models.Book;
import com.example.ironlibrary.repository.AuthorRepository;
import com.example.ironlibrary.repository.BookRepository;
import com.mysql.cj.jdbc.Blob;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataTransferToBBDDTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private DataTransferToBBDD dataTransferToBBDD;
    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void cleanDatabase() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    @DisplayName("Test add book")
    void dataAddBookTest() {
        // Datos de entrada
        String name = "Gabriel García Márquez";
        String email = "gabriel@example.com";
        String isbn = "9781234567890";
        String title = "Cien años de soledad";
        String category = "Novela";
        int quantity = 5;

        // Save the book to the database
        dataTransferToBBDD.dataAddBook(name, email, isbn, title, category, quantity);
        List<Author> authors = authorRepository.findAll();
        List<Book> books = bookRepository.findAll();

        assertEquals(1, authors.size());
        assertEquals(name, authors.getFirst().getName());

        assertEquals(1, books.size());
        assertEquals(title, books.getFirst().getTitle());
        assertEquals(isbn, books.getFirst().getIsbn());
        assertEquals(authors.getFirst().getId(), books.getFirst().getAuthor().getId());

    }

    @Test
    @DisplayName("Test find book by category")
    void findBookByCategory(){


    }

}