package com.example.ironlibrary;


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


    static Scanner scanner = new Scanner(System.in);

    public void addBook() throws BookExceptions {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        // Check ISBN
        String IsbnOk = checkISBN(isbn);
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Category: ");
        checkEmpty(title, 1);
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

        // Create a new Author object
        Author author = new Author();
        author.setName(authorName);
        author.setEmail(authorEmail);
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

        // Show the book
        System.out.println("Book added successfully!");


    }

    public void findByCategory() throws BookExceptions {
        System.out.print("Enter the category of the book: ");
        String category = scanner.nextLine();
        checkEmpty(category, 2);
        // Find books by category
        var books = bookRepository.findBookByCategory(category);
        if (books != null) {
            System.out.printf("%-20s %-50s %-30s %-12s%n",
                    "Book ISBN", "Book Title", "Category", "No of Books");
            for (Book book : books) {
                System.out.printf("%-20s %-50s %-30s %-12d%n",
                        book.getIsbn(),
                        book.getTitle(),
                        book.getCategory(),
                        book.getQuantity());
            }
        } else {
            System.out.println("No books found.");
        }
    }

    public void findByTitle() throws BookExceptions {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        checkEmpty(title, 1);
        Book book = bookRepository.findByTitleContaining(title);
        if (book != null) {
            System.out.printf("%-20s %-50s %-30s %-12s%n",
                    "Book ISBN", "Book Title", "Category", "No of Books");
            System.out.printf("%-20s %-50s %-30s %-12s%n",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getCategory(),
                    book.getQuantity());
        } else {
            System.out.println("No books found.");
        }
    }

    public void findByAuthor() throws BookExceptions {
        System.out.print("Enter the author name: ");
        String authorName = scanner.nextLine();
        checkEmpty(authorName, 3);
        List<Book> books = bookRepository.findBookByAuthorName(authorName);
        if (books != null) {
            System.out.printf("%-20s %-50s %-30s %-12s%n",
                    "Book ISBN", "Book Title", "Category", "No of Books");
            for (Book book : books) {
                System.out.printf("%-20s %-50s %-30s %-12s%n",
                        book.getIsbn(),
                        book.getTitle(),
                        book.getCategory(),
                        book.getQuantity());
            }
        } else {
            System.out.println("No books found.");
        }
    }

    public void findByAlongAuthor() throws BookExceptions {
        System.out.println("Enter the author name: ");
        String authorName = scanner.nextLine();
        checkEmpty(authorName, 3);
        List<Book> books = bookRepository.findBookByAuthorName(authorName);
        if (books != null) {
            Book book = books.getFirst();
            System.out.printf("%-20s %-50s %-30s %-12s%n",
                    "Book ISBN", "Book Title", "Category", "No of Books");

            System.out.printf("%-20s %-50s %-30s %-12s%n",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getCategory(),
                    book.getQuantity());

        } else {
            System.out.println("No books found.");
        }
    }

    public void issueBookToStudent() {
        System.out.println("Enter a usn of Student: ");
        String usn = scanner.nextLine();
        System.out.println("Enter a name of Student: ");
        String name = scanner.nextLine();
        System.out.println("Enter a book ISBN: ");
        String isbn = scanner.nextLine();
        // Find Student By USN and Name
        Optional<Student> student = studentRepository.findStudentByUsnAndName(usn, name);
        //Find Book By ISBN
        Optional<Book> book = bookRepository.findBookByIsbn(isbn);

        if (student.isPresent() && book.isPresent()) {
            // ISSUE BOOK
            Issue issue = new Issue();
            issue.setIssueDate(new Date().toString());
            issue.setReturnDate(new Date().toString() + 15);
            issue.setStudent(student.get());
            issue.setBook(book.get());
            System.out.println("Issue Book");
        } else {
            System.out.println("Student or Book not found");
        }


    }
    public void findBooksByUsbn() throws BookExceptions {
        System.out.println("Enter a usn of Student: ");
        String usn = scanner.nextLine();
        checkEmpty(usn, 4);
        // Find id of Student by Student  USN
        Optional<Student>student = studentRepository.findStudentByUsn(usn);
        if (student.isPresent()) {
            // find iisued by student
            List<Issue> issues = issueRepository.findIssueByStudent(student.get());
            if (issues != null) {
                System.out.printf("%-50s %-30s %-12s%n",
                        "Book Title", "Student Name", "Return Date");
                for (Issue issue : issues) {
                    Book book = issue.getBook();
                    System.out.printf("%-50s %-30s %-12s%n",
                            issue.getBook().getTitle(),
                            issue.getStudent().getName(),
                            issue.getReturnDate());
                }
            } else {
                System.out.println("No books found.");
            }
        }


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