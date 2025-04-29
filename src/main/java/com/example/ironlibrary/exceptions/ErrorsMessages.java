package com.example.ironlibrary.exceptions;

public class ErrorsMessages {

        // Error messages BOOK
        //ISBN
        public static final String ISBN_EMPTY  = "ISBN cannot be empty";
        public static final String ISBN_INVALID  = "The ISBN must have a length of 13 numbers";
        public static final String ISBN_NOT_CHAR  = "The ISBN must contain only numbers";
        //TITLE
        public static final String TITLE_EMPTY = "Title cannot be empty";
        //CATEGORY
        public static final String CATEGORY_EMPTY = "Category cannot be empty";
        //QUANTITY
        public static final String INVALID_QUANTITY = "Quantity must be a number";
        //AUTHOR BOOK
        public static final String AUTHOR_EMPTY = "Author cannot be empty";
        //Student USN
        public static final String USN_EMPTY  = "USN cannot be empty";

        // Constructor
        public ErrorsMessages() {
        }
    }


