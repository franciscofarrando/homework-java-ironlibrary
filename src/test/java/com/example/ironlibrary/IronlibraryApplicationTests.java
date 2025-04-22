package com.example.ironlibrary;


import com.example.ironlibrary.models.Book;
import com.example.ironlibrary.repository.AuthorRepository;
import com.example.ironlibrary.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IronlibraryApplicationTests {
	//TODO: hacer pruebas unitarias de:
	//Add a book
	//Search book by title
	//Search book by category
	//Search book by Author
	//List all books along with author
	//Issue book to student
	//List books by usn

	// repository book and student
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	@Autowired
	public IronlibraryApplicationTests(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}



	@Test
	@DisplayName("add book in database")
	void addBook() {
		// Create a new book object
		Book book = new Book();
		// Save the book to the database
		bookRepository.save(book);
		// Retrieve the book from the database
		Book retrievedBook = bookRepository.findById(book.getId()).orElse(null);
		// Assert that the retrieved book is not null and matches the original book
		assertNotNull(retrievedBook);
	}

}
