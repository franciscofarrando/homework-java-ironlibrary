package com.example.ironlibrary;

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

@Component
public class DataTransferToBBDD {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private IssueRepository issueRepository;



    public void dataAddBook(String name, String email, String IsbnOk, String title, String category, int quantity) {
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

    public void findByCategory(String category) {
        // repository
        List<Book> books = bookRepository.findBookByCategory(category);
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

    public void findByTitle(String title) {
        Book book = bookRepository.findByTitleContaining(title);
        if (book != null) {
            System.out.printf("%-20s %-50s %-30s %-12s%n",
                    "Book ISBN", "Book Title", "Category", "No of Books");
            System.out.printf("%-20s %-50s %-30s %-12d%n",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getCategory(),
                    book.getQuantity());
        } else {
            System.out.println("No books found.");
        }
    }


    public void findByAuthor(String name) {
        List<Book> books = bookRepository.findBookByAuthorName(name);
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

    public void findByAlongAuthor(String name){
        List<Book> books = bookRepository.findBookByAuthorName(name);
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

    public void issueBookToStudent(String usn,String name, String isbn){
        // Find Student By USN and Name

        Optional<Student> student = studentRepository.findStudentByUsnAndName(usn, name);
        //Find Book By ISBN
        Optional<Book> book = bookRepository.findBookByIsbn(isbn);

        if (student.isPresent() && book.isPresent()) {
            // ISSUE BOOK
            Issue issue = new Issue();
            //TODO: mirar el dia del prestamo y el retorno del mismo
            issue.setIssueDate(new Date().toString());
            issue.setReturnDate(new Date().toString() + 15);
            issue.setStudent(student.get());
            issue.setBook(book.get());
            // update quantity of book
            book.get().setQuantity(book.get().getQuantity() - 1);
            // save book
            bookRepository.save(book.get());
            // save issue
            issueRepository.save(issue);
            System.out.println("Issue Book");
        } else {
            System.out.println("Student or Book not found");
        }

    }


    public void findBooksByUsbn(String usn){

        // Find id of Student by Student  USN
        Optional<Student> student = studentRepository.findStudentByUsn(usn);
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

}
