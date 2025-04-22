package com.example.ironlibrary;

import com.example.ironlibrary.models.Author;
import com.example.ironlibrary.models.Book;
import com.example.ironlibrary.repository.AuthorRepository;
import com.example.ironlibrary.repository.BookRepository;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class Main {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public void printCommands() {
        System.out.println("*********************************************");
        System.out.println("1. Add a book");
        System.out.println("2. Search book by title");
        System.out.println("3. Search book by category");
        System.out.println("4. Search book by Author");
        System.out.println("5. List all books along with author");
        System.out.println("6. Issue book to student");
        System.out.println("7. List books by usn");
        System.out.println("8. Exit");
        System.out.println("*********************************************");
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

        Book book = new Book(isbn, title, category, numberOfBooks);
        bookRepository.save(book);
        Author author = new Author();

        authorRepository.save(author);

    }

    public void scanCommands(){
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n########################################################################");
            System.out.println("# INTRODUCE UN COMANDO VÁLIDO DE LA SIGUIENTE LISTA (EXIT PARA SALIR): #");
            System.out.println("########################################################################");
            printCommands();
            System.out.print("Enter your choice: ");

            int commandInt = scanner.nextInt();

            switch (commandInt){
                case 1:// 1. Add a book
                    addBook();
                    break;
                case 2:// 2. Search book by title
                    break;
                case 3:// 3. Search book by category
                    break;
                case 4:// 4. Search book by Author
                    break;
                case 5://5. List all books along with author
                    break;
                case 6:// 6. Issue book to student
                    break;
                case 7:// 7. List books by usn
                    break;
                case 8:// 8. Exit
                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {

        Main main = new Main();
        main.scanCommands();
    }
}
