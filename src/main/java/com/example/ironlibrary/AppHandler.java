package com.example.ironlibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AppHandler {
    @Autowired
    private BookHandler bookHandler;

    Scanner scanner = new Scanner(System.in);

    public void menu() {

        String[] optionMenu = {"Add Book",
                "Search book by title",
                "Search book by category",
                "Search book by Author",
                "List all books along with Author",
                "Issue book to Student",
                "List books by usbn",
                "Exit"};
        int option = 0;
        while (option!=8) {
            for (int i = 0; i < optionMenu.length; i++) {
                System.out.println((i + 1) + ". " + optionMenu[i]);
            }

            System.out.println("Please select an option: ");
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Add Book");
                        bookHandler.addBook();
                        System.out.println("Please press enter to continue");
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("Search book by title");
                        bookHandler.findByTitle();
                        System.out.println("Please press enter to continue");
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.println("Search book by category");
                        bookHandler.findByCategory();
                        System.out.println("Please press enter to continue");
                        scanner.nextLine();
                        break;
                    case 4:
                        System.out.println("Search book by Author");
                        bookHandler.findByAuthor();
                        System.out.println("Please press enter to continue");
                        scanner.nextLine();
                        break;
                    case 5:
                        System.out.println("List all books along with Author");
                        bookHandler.findByAlongAuthor();
                        System.out.println("Please press enter to continue");
                        scanner.nextLine();
                        break;
                    case 6:
                        System.out.println("Issue book to Student");
                        bookHandler.issueBookToStudent();
                        System.out.println("Please press enter to continue");
                        scanner.nextLine();
                        break;
                    case 7:
                        System.out.println("List books by usbn");
                        bookHandler.findBooksByUsbn();
                        System.out.println("Please press enter to continue");
                        scanner.nextLine();
                        break;
                    case 8:
                        System.out.println("Exit");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        System.out.println("Please press enter to continue");
                        scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
            } finally {
                scanner.nextLine(); // Clear the buffer
            }

        }


    }


}