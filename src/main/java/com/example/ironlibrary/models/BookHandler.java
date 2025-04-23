package com.example.ironlibrary.models;

import java.util.Scanner;

public  class BookHandler {

    public static void addBook() {
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


    }
}
