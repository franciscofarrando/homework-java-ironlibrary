package com.example.ironlibrary;

import com.example.ironlibrary.models.Author;
import com.example.ironlibrary.models.Book;
import com.example.ironlibrary.models.Issue;
import com.example.ironlibrary.models.Student;
import com.example.ironlibrary.repository.AuthorRepository;
import com.example.ironlibrary.repository.BookRepository;
import com.example.ironlibrary.repository.IssueRepository;
import com.example.ironlibrary.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataTransferToBBDDTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private DataTransferToBBDD dataTransferToBBDD;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private StudentRepository studentRepository;

    private Author author;
    private Book book;
    private Student student;

    private final String name = "J.K. Rowling";
    private final String email = "jkrowling@example.com";
    private final String isbn = "9783161484100";
    private final String title = "Harry Potter and the Philosopher's Stone";
    private final String category = "Fantasy";
    private final int quantity = 5;
    private final String usn = "USN001";
    private final String nameStudent = "John Doe";



    @Test
    @DisplayName("Test find book by category")
    void findBookByCategory() {
        List<Book> bookList = bookRepository.findBookByCategory(category);
        assertNotNull(bookList);
        assertFalse(bookList.isEmpty());
        Book found = bookList.getFirst();
        assertEquals(isbn, found.getIsbn());
        assertEquals(title, found.getTitle());
        assertEquals(name, found.getAuthor().getName());
        assertEquals(email, found.getAuthor().getEmail());
        assertEquals(quantity, found.getQuantity());
    }
    @Test
    @DisplayName("Test find book by title")
    void findBookByTitle() {
        Book found = bookRepository.findByTitle(title);
        assertNotNull(found);
        assertEquals(title, found.getTitle());
        assertEquals(isbn, found.getIsbn());
        assertEquals(category, found.getCategory());
        assertEquals(quantity, found.getQuantity());
    }

    @Test
    @DisplayName("Test find book by author")
    void findBookByAuthor() {
        List<Book> bookList = bookRepository.findBookByAuthorName(name);
        assertNotNull(bookList);
        assertFalse(bookList.isEmpty());
        Book found = bookList.getFirst();
        assertEquals(name, found.getAuthor().getName());
        assertEquals(email, found.getAuthor().getEmail());
        assertEquals(isbn, found.getIsbn());
        assertEquals(title, found.getTitle());
        assertEquals(category, found.getCategory());
        assertEquals(quantity, found.getQuantity());
    }

    @Test
    @DisplayName("Test issue book to student")
    void issueBookToStudent() {
        Optional<Student> optionalStudent = studentRepository.findStudentByUsn(usn);
        assertTrue(optionalStudent.isPresent());
        Student foundStudent = optionalStudent.get();
        List<Issue> issues = issueRepository.findIssueByStudent(foundStudent);
        assertFalse(issues.isEmpty());
        Issue foundIssue = issues.get(0);

        assertEquals(usn, foundStudent.getUsn());
        assertEquals(nameStudent, foundStudent.getName());
        assertEquals(isbn, foundIssue.getBook().getIsbn());
        assertEquals(title, foundIssue.getBook().getTitle());
        assertEquals(category, foundIssue.getBook().getCategory());
        assertEquals(quantity, foundIssue.getBook().getQuantity());
        assertEquals(name, foundIssue.getBook().getAuthor().getName());
    }

    @Test
    @DisplayName("Test find books by USN")
    void findBooksByUsn() {
        Optional<Student> studentOpt = studentRepository.findStudentByUsn(usn);
        assertTrue(studentOpt.isPresent());
        Student foundStudent = studentOpt.get();

        List<Issue> issues = issueRepository.findIssueByStudent(foundStudent);
        assertNotNull(issues);
        assertFalse(issues.isEmpty());

        Issue issue = issues.get(0);
        assertEquals(isbn, issue.getBook().getIsbn());
        assertEquals(title, issue.getBook().getTitle());
        assertEquals(category, issue.getBook().getCategory());
        assertEquals(name, issue.getBook().getAuthor().getName());
    }


}
