package com.example.ironlibrary;


import com.example.ironlibrary.exceptions.BookExceptions;
import com.example.ironlibrary.exceptions.ErrorsMessages;
import com.example.ironlibrary.models.Author;
import com.example.ironlibrary.models.Book;
import com.example.ironlibrary.models.Issue;
import com.example.ironlibrary.models.Student;
import com.example.ironlibrary.repository.AuthorRepository;
import com.example.ironlibrary.repository.BookRepository;
import com.example.ironlibrary.repository.IssueRepository;
import com.example.ironlibrary.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class BookHandler {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DataTransferToBBDD dataTransferToBBDD;


    static Scanner scanner = new Scanner(System.in);

    public void addBook() throws BookExceptions {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        // Check ISBN
        String IsbnOk = checkISBN(isbn);
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        checkEmpty(title, 1);
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        checkEmpty(category, 2);
        System.out.print("Enter Quantity: ");
        String quantityInput = scanner.nextLine();
        int quantity;
        try {
            quantity = Integer.parseInt(quantityInput);
        } catch (NumberFormatException e) {
            throw new BookExceptions(ErrorsMessages.INVALID_QUANTITY);
        }

        // Author
        System.out.print("Enter Author Name: ");
        String authorName = scanner.nextLine();
        System.out.print("Enter Author Email: ");
        String authorEmail = scanner.nextLine();

        dataTransferToBBDD.dataAddBook(authorName, authorEmail, IsbnOk, title, category, quantity);
        // Show the book
        System.out.println("Book added successfully!");


    }

    public void findByCategory() throws BookExceptions {
        System.out.print("Enter the category of the book: ");
        String category = scanner.nextLine();
        checkEmpty(category, 2);
        // Find books by category
        dataTransferToBBDD.findByCategory(category);

    }

    public void findByTitle() throws BookExceptions {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        checkEmpty(title, 1);
        dataTransferToBBDD.findByTitle(title);

    }

    public void findByAuthor() throws BookExceptions {
        System.out.print("Enter the author name: ");
        String authorName = scanner.nextLine();
        checkEmpty(authorName, 3);
        dataTransferToBBDD.findByAuthor(authorName);

    }

    public void findByAlongAuthor() throws BookExceptions {
        System.out.println("Enter the author name: ");
        String authorName = scanner.nextLine();
        checkEmpty(authorName, 3);
        dataTransferToBBDD.findByAlongAuthor(authorName);

    }

    public void issueBookToStudent() {
        System.out.println("Enter a usn of Student: ");
        String usn = scanner.nextLine();
        System.out.println("Enter a name of Student: ");
        String name = scanner.nextLine();
        System.out.println("Enter a book ISBN: ");
        String isbn = scanner.nextLine();
        dataTransferToBBDD.issueBookToStudent(usn,name,isbn);

    }

    public void findBooksByUsbn() throws BookExceptions {
        System.out.println("Enter a usn of Student: ");
        String usn = scanner.nextLine();
        checkEmpty(usn, 4);
        dataTransferToBBDD.findBooksByUsbn(usn);
    }

    // VALIDATES ADD BOOKS
    private void checkEmpty(String input, int num) throws BookExceptions {
        if (num == 1 && input.isEmpty()) {
            throw new BookExceptions(ErrorsMessages.TITLE_EMPTY);
        }
        if (num == 2 && input.isEmpty()) {
            throw new BookExceptions(ErrorsMessages.CATEGORY_EMPTY);
        }
        if (num == 3 && input.isEmpty()) {
            throw new BookExceptions(ErrorsMessages.AUTHOR_EMPTY);
        }
        if (num == 4 && input.isEmpty()) {
            throw new BookExceptions(ErrorsMessages.USN_EMPTY);
        }
    }

    private String checkISBN(String isbn) throws BookExceptions {
        String isbnTrim = isbn.trim();
        if (isbnTrim.length() != 13) {
            throw new BookExceptions(ErrorsMessages.ISBN_INVALID);
        } else if (isbn.isEmpty()) {
            throw new BookExceptions(ErrorsMessages.ISBN_EMPTY);
        } else {
            for (int i = 0; i < isbnTrim.length(); i++) {
                if (!Character.isDigit(isbnTrim.charAt(i))) {
                    throw new BookExceptions(ErrorsMessages.ISBN_NOT_CHAR);
                }
            }
        }
        // format the ISBN
        return isbn.substring(0, 3) + "-" + isbn.substring(3, 4) + "-" + isbn.substring(4, 7) + "-" + isbn.substring(7,
                12) + "-" + isbn.charAt(12);
    }


}