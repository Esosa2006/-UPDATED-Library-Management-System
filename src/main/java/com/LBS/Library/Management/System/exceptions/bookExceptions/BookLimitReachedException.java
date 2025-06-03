package com.LBS.Library.Management.System.exceptions.bookExceptions;

public class BookLimitReachedException extends RuntimeException {
    public BookLimitReachedException(String message) {
        super(message);
    }
}
