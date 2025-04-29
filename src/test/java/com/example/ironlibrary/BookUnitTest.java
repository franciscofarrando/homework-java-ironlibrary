package com.example.ironlibrary;

import com.example.ironlibrary.models.Author;
import com.example.ironlibrary.models.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class  BookUnitTest {
    @Test
    void testAddBook() {
        Author author = new Author("Gabriel García Márquez", "Colombiano");
        Book book = new Book("123456789", "Cien Años de Soledad", author);

        assertEquals("123456789", book.getIsbn());
        assertEquals("Cien Años de Soledad", book.getTitle());
        assertEquals("Gabriel García Márquez", book.getAuthor().getName());
    }
}
