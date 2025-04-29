package com.example.ironlibrary;

import com.example.ironlibrary.models.Author;
import com.example.ironlibrary.models.Book;
import com.example.ironlibrary.repository.AuthorRepository;
import com.example.ironlibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class IronlibraryApplication implements CommandLineRunner {


    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public static void main(String[] args) {

        SpringApplication.run(IronlibraryApplication.class, args);
    }


    public void addBook() {
        System.out.println("Añadiendo libro");
        /**
         * -Enter your choice: 1
         * -Enter isbn : 978-3-16-148410-0
         * -Enter title : The Notebook
         * -Enter category : Romance
         * -Enter Author name : Nicholas Sparks
         * -Enter Author mail : nicholassparks@gmail.com
         * -Enter number of books : 4
         * */
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter isbn: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Author name: ");
        String authorName = scanner.nextLine();
        System.out.print("Enter Author mail: ");
        String authorMail = scanner.nextLine();
        System.out.print("Enter number of books:");
        int numberOfBooks = scanner.nextInt();

        Book book = new Book(isbn, title, category, numberOfBooks, null);
        bookRepository.save(book);
//        Author author = new Author();
//
//        authorRepository.save(author);

    }

    //Inicia la app
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String[] menu = {"Add a book", "Search book by title", "Search book by" +
                " category", "Search book by Author", "List all books along " +
                "with author", "Isuue book to student", "List books by usn", "Exit"};
        boolean userChoice = true;

        while (userChoice) {

            for (int i = 0; i < menu.length; i++) {
                System.out.println((i + 1) + " " + menu[i] + ":");
            }
            try {
                System.out.print("Enter your choice: ");
                int userOption = scanner.nextInt();
                if (userOption < 0 || userOption > menu.length) {
                    throw new InputMismatchException("Enter an option between 1 and " + menu.length + 1);
                }
                switch (userOption) {
                    case 1:
                        //Add a book
                        System.out.println(menu[0]);
                        addBook();
                        continue;
                    case 2:
                        //Search book by title
                        System.out.println(menu[1]);
                        findBooksByTitle();

                        continue;
                    case 3:
                        System.out.println(menu[2]);
                        // find book by category
                        findBooksByCategory();
                        continue;
                    case 4:
                        System.out.println(menu[3]);
                        findByAuthor();
                        continue;
                    case 5:
                        System.out.println(menu[4]);
                        //BookHandler.findAllBooks();
                        break;
                    case 6:
                        System.out.println(menu[5]);
                        continue;
                    case 7:
                        System.out.println(menu[6]);
                        userChoice = false;
                        break;
                }
            } catch (Exception e) {
                throw new Exception("Please enter a integer value");

            }


        }

    }

    private void findBooksByTitle() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter book title: ");
            String bookTitle = scanner.nextLine().trim();
            Book book = bookRepository.findByTitle(bookTitle);
            if (book != null) {
                System.out.println("Book ISBN                Book Title          Category           Nº of Books");
                System.out.printf("%-25s %-20s %-20s %d%n",
                        book.getIsbn(), book.getTitle(), book.getCategory(), book.getQuantity());
                System.out.println("------------------------------------------------------");
            } else {
                System.out.println("Book not found for title: " + bookTitle);
            }
            System.out.println("To exit press Enter");
            scanner.nextLine();

        } catch (Exception e) {
            throw new RuntimeException("Error when entering book data", e);
        }
    }

    private void findByAuthor() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter author name: ");
            String authorName = scanner.nextLine().trim();
            //find author by name
            Author author = authorRepository.findByName(authorName);
            // author isn't null?
            if (author != null) {
                //find all books by author
                List<Book> booksByAuthor = bookRepository.findAllByAuthor(author.get().getAuthorId());
                // booksByAuthor isn't null and empty?
                if (booksByAuthor != null && !booksByAuthor.isEmpty()) {
                    System.out.println("Book ISBN                Book Title          Category           Nº of Books");
                    for (Book book : booksByAuthor) {
                        System.out.printf("%-25s %-20s %-20s %d%n",
                                book.getIsbn(), book.getTitle(), book.getCategory(), book.getQuantity());
                    }
                    System.out.println("------------------------------------------------------");
                } else {
                    System.out.println("No books found for author: " + authorName);
                }
            } else {
                System.out.println("Author not found for name: " + authorName);
            }

            System.out.println("To exit press Enter");
            scanner.nextLine();

        } catch (Exception e) {
            throw new RuntimeException("Error when entering author data", e);
        }
    }


    public void findBooksByCategory() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter book category: ");
            String categoryBook = scanner.nextLine().trim();
            List<Book> book = bookRepository.findAllByCategory(categoryBook);
            System.out.println("Book ISBN                Book Title          Category           Nº of Books");
            if (!categoryBook.isEmpty()) {
                for (Book book1 : book) {
                    System.out.printf("%-25s %-20s %-20s %d%n",
                            book1.getIsbn(), book1.getTitle(), book1.getCategory(), book1.getQuantity());

                }
                System.out.println("------------------------------------------------------");

            } else {
                System.out.println("Book not found for this category: " + categoryBook);
            }
            System.out.println("To exit press a Enter");
            scanner.nextLine();

        } catch (Exception e) {
            throw new RuntimeException("error when entering category data");
        }
    }
}
