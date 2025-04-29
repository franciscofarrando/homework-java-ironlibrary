package com.example.ironlibrary;

import com.example.ironlibrary.models.Author;
import com.example.ironlibrary.models.Book;
import com.example.ironlibrary.repository.AuthorRepository;
import com.example.ironlibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataTransferToBBDD {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public void dataAddBook(String name,String email,String IsbnOk, String title, String category, int quantity) {
        Author author = new Author();
        author.setName(name);
        author.setEmail(email);

        // Save the author to the database
        authorRepository.save(author);
        // Create a new Book object
        Book book = new Book();
        book.setIsbn(IsbnOk);
        book.setTitle(title);
        book.setCategory(category);
        book.setQuantity(quantity);
        book.setAuthor(author);
        // Save the book to the database
        bookRepository.save(book);

    }



}
