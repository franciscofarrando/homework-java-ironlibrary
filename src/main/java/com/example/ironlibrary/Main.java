package com.example.ironlibrary;

import com.example.ironlibrary.models.Book;
import com.example.ironlibrary.models.BookHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Scanner;

public class Main {

    Scanner scanner = new Scanner(System.in);

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
//        bookRepository.save(book);
//        Author author = new Author();
//
//        authorRepository.save(author);

    }

    public void menu() {
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
                    System.out.println(menu[0]);
                    BookHandler.addBook();
                    continue;
                case 2:
                    System.out.println(menu[1]);
                    continue;
                case 3:
                    System.out.println(menu[2]);
                    continue;
                case 4:
                    System.out.println(menu[3]);
                    continue;
                case 5:
                    System.out.println(menu[4]);
                    continue;
                case 6:
                    System.out.println(menu[5]);
                    continue;
                case 7:
                    System.out.println(menu[6]);
                    break;
                case 8:
                    System.out.println(menu[6]);
                    userChoice = false;
                    break;
            }
        }

    }
}


