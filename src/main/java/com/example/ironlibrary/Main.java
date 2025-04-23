package com.example.ironlibrary;

import com.example.ironlibrary.models.BookHandler;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main {

    Scanner scanner = new Scanner(System.in);

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
                    userChoice = false;
                    break;
            }


        }

    }



}