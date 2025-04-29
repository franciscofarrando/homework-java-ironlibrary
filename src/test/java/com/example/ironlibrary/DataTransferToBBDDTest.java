package com.example.ironlibrary;

import com.example.ironlibrary.models.Author;
import com.example.ironlibrary.models.Book;
import com.example.ironlibrary.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DataTransferToBBDDTest {
    @Autowired
    private  BookRepository bookRepository;
    private  DataTransferToBBDD dataTransferToBBDD;



    @Test
    @DisplayName("Test add book")
    void dataAddBookTest(){
        Book book = new Book();
        Author author = new Author();
        book.setIsbn("1234567890123");
        book.setTitle("Test Book");
        book.setCategory("Test Category");
        book.setQuantity(5);
        author.setName("Test Author");
        author.setEmail("author@email.es");
        book.setAuthor(author);
        // Save the book to the database
        dataTransferToBBDD.dataAddBook(author.getName(), author.getEmail(), book.getIsbn(), book.getTitle(), book.getCategory(), book.getQuantity());

    }

}