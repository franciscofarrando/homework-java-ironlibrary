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
        assertEquals(name, authors.get(0).getName());

        assertEquals(1, books.size());
        assertEquals(title, books.getFirst().getTitle());
        assertEquals(isbn, books.getFirst().getIsbn());
        assertEquals(authors.getFirst().getId(), books.getFirst().getAuthor().getId());
        //        Book book = new Book();
//        Author author = new Author();
//        book.setIsbn("1234567890123");
//        book.setTitle("Test Book");
//        book.setCategory("Test Category");
//        book.setQuantity(5);
//        author.setName("Test Author");
//        author.setEmail("author@email.es");
//        book.setAuthor(author);

    }
    @Test
    void testAddBook() {

        Author author = new Author("Gabriel García Márquez", "Colombiano");

        Book book = new Book("123456789", "Cien Años de Soledad", author);
        assertEquals("123456789", book.getIsbn());
        assertEquals("Cien Años de Soledad", book.getTitle());
        assertEquals("Gabriel García Márquez", book.getAuthor().getName());
    }
}