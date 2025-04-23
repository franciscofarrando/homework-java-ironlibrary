package com.example.ironlibrary;

import com.example.ironlibrary.models.Book;
import com.example.ironlibrary.repository.AuthorRepository;
import com.example.ironlibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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



    public void addBook(){
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
            int userOption = scanner.nextInt();
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

                    //BookHandler.findBooksByCategory();
                    continue;
                case 4:
                    System.out.println(menu[3]);
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


        }

    }

    private void findBooksByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        Book book = bookRepository.findByTitle(title);
        if (book != null) {
            System.out.println("Book ISBN                Book Title          Category           Nº of Books");
            System.out.println(book.getIsbn()+"                "+book.getTitle()+"          "+book.getCategory()+"   " +
                    "        "+book.getQuantity());
        } else {
            System.out.println("Book not found");
        }

    }
}
