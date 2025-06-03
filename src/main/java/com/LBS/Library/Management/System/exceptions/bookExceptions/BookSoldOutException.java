package com.LBS.Library.Management.System.exceptions.bookExceptions;

public class BookSoldOutException extends RuntimeException {
    public BookSoldOutException(String message) {
        super(message);
    }
}
